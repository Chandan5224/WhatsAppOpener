package com.example.whtsappopener

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.whtsappopener.databinding.ActivityMainBinding
import com.example.whtsappopener.databinding.ActivitySplashScreenBinding

class splash_screen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    lateinit var top :Animation
    lateinit var bottom : Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //// animation
        top=AnimationUtils.loadAnimation(this,R.anim.top_animation,)
        bottom=AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        binding.image.animation=top
        binding.text.animation=bottom

        // Handler().postDelayed({
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // 2000 is the delayed time in milliseconds.

    }
}