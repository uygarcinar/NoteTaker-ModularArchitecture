package com.app.notetaker.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.notetaker.databinding.ActivityMainBinding
import com.app.notetaker.domain.enums.SplashStatus.*
import com.app.welcome.ui.presentation.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startSplashInitialization()
        splashStateControl()
    }
    private fun splashStateControl(){
        lifecycleScope.launch {
            viewModel.splashCompleteStatus.collect{ state ->
                when(state){
                    NONE -> {}
                    REDIRECT_WELCOME -> {
                        // redirect to welcome onboard.
                        startActivity(Intent(this@MainActivity,OnboardingActivity::class.java))
                    }
                    REDIRECT_HOME -> {
                        // redirect to home screen.
                    }
                }
            }
        }
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
                            viewModel.checkAlreadyLoggedIn()
                        },1000)
                    }
                    .start()
            }
            .start()
    }
}