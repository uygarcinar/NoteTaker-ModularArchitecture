package com.app.welcome.ui.presentation

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.app.welcome.R
import com.app.welcome.databinding.ActivityOnboardingBinding
import com.app.welcome.data.adapter.OnboardingPagerAdapter
import com.app.welcome.ui.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    private val viewModel: OnboardingViewModel by viewModels()
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var onboardingPagerAdapter: OnboardingPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupOnboarding()
    }

    private fun setupOnboarding() {
        onboardingPagerAdapter = OnboardingPagerAdapter(viewModel.onboardingItems)
        binding.viewPager.adapter = onboardingPagerAdapter
        setupIndicators()
        setupButtons()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateIndicators(position)
                updateButtons(position)
            }
        })
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(onboardingPagerAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                it.layoutParams = layoutParams
                binding.indicatorsContainer.addView(it)
            }
        }
    }

    private fun updateIndicators(position: Int) {
        val childCount = binding.indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorsContainer[i] as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

    private fun updateButtons(position: Int) {
        val isLastPage = position == onboardingPagerAdapter.itemCount - 1
        binding.buttonNext.text = if (isLastPage) "Get Started" else "Next"
        binding.buttonSkip.visibility = if (isLastPage) android.view.View.GONE else android.view.View.VISIBLE
    }

    private fun setupButtons() {
        binding.buttonNext.setOnClickListener {
            if (binding.viewPager.currentItem == onboardingPagerAdapter.itemCount - 1) {
                finishOnboarding()
            } else {
                binding.viewPager.currentItem += 1
            }
        }

        binding.buttonSkip.setOnClickListener {
            finishOnboarding()
        }
    }

    private fun finishOnboarding() {
        // TODO: Navigate to main activity
        viewModel.saveWelcomeState()
        finishAffinity()
    }
}