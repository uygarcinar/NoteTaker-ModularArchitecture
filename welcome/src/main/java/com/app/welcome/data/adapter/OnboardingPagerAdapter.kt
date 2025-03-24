package com.app.welcome.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.welcome.databinding.ItemOnboardingPageBinding
import com.app.welcome.data.model.OnboardingPage

class OnboardingPagerAdapter(private val onboardingPages: List<OnboardingPage>) :
    RecyclerView.Adapter<OnboardingPagerAdapter.OnboardingPageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingPageViewHolder {
        return OnboardingPageViewHolder(
            ItemOnboardingPageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = onboardingPages.size

    override fun onBindViewHolder(holder: OnboardingPageViewHolder, position: Int) {
        holder.bind(onboardingPages[position])
    }

    inner class OnboardingPageViewHolder(private val binding: ItemOnboardingPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onboardingPage: OnboardingPage) {
            binding.imageOnboarding.setImageResource(onboardingPage.image)
            binding.textTitle.text = onboardingPage.title
            binding.textDescription.text = onboardingPage.description
        }
    }
} 