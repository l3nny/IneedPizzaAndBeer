package com.example.ineedpizzabeer.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.example.ineedpizzabeer.R
import com.example.ineedpizzabeer.databinding.SplashLayoutBinding
import com.example.ineedpizzabeer.presentation.ui.view.MainActivity

class Splash : AppCompatActivity() {

    companion object {
        const val SPLASH_TIME_OUT = 3000
    }

    lateinit var binding: SplashLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
    }

    private fun setView() {
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@Splash, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
            finish()
        }, SPLASH_TIME_OUT.toLong())
        val fadeout: Animation = AlphaAnimation(1f, 1f)

        fadeout.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                binding.gif.setBackgroundResource(R.drawable.giphy)
            }
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {}
        })
        binding.gif.startAnimation(fadeout)
    }
}