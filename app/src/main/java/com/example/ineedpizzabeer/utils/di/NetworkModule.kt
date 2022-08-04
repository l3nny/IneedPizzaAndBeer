package com.example.ineedpizzabeer.utils.di

import androidx.databinding.ktx.BuildConfig
import com.example.ineedpizzabeer.data.Service
import com.example.ineedpizzabeer.data.repository.IRepositoryHelper
import com.example.ineedpizzabeer.data.repository.RepositoryHelper
import com.example.ineedpizzabeer.utils.Constants.BASE_URL
import com.example.ineedpizzabeer.utils.Keys
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val IO_TIMEOUT = 30L
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): Service = retrofit.create(Service::class.java)

    @Provides
    fun provideApiHelper(apiHelper: RepositoryHelper): IRepositoryHelper = apiHelper

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        moshi: Moshi,
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun provideOkHttpClient(headerInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level =
                            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            requestBuilder.addHeader("Authorization", "Bearer " + Keys.apiKeyYelp())
            it.proceed(requestBuilder.build())
        }
    }

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = BASE_URL
}