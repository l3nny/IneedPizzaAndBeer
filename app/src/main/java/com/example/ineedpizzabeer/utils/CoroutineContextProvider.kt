package com.example.ineedpizzabeer.utils

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    val Main: CoroutineContext
    val IO: CoroutineContext
    val Default: CoroutineContext
}

class AppCoroutineContextProvider @Inject constructor() : CoroutineContextProvider {
    override val Main: CoroutineContext by lazy { Dispatchers.Main }
    override val IO: CoroutineContext by lazy { Dispatchers.IO }
    override val Default: CoroutineContext by lazy { Dispatchers.Default }
}
