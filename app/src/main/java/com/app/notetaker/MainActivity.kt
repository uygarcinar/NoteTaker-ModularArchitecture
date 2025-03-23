package com.app.notetaker

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.notetaker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startSplashInitialization()
    }
    private fun startSplashInitialization() {
        binding.appLogo.alpha = 0f
        binding.loadingProgress.visibility = View.VISIBLE
        binding.appLogo.animate()
            .alpha(1f)
            .setDuration(1000)
            .withEndAction {
                binding.appLogo.animate()
                    .rotationBy(360f)
                    .setDuration(1500)
                    .withEndAction {
                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.loadingProgress.visibility = View.GONE
                        },1000)
                    }
                    .start()
            }
            .start()
    }
}