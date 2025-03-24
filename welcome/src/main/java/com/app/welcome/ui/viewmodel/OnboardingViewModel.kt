package com.app.welcome.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.app.core.domain.repository.IPreferencesManager
import com.app.core.utils.AppConstants
import com.app.welcome.R
import com.app.welcome.data.model.OnboardingPage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val prefs : IPreferencesManager
) : ViewModel() {
    private val _onboardingPages = listOf(
        OnboardingPage(
            "Welcome to NoteTaker",
            "Your personal note-taking companion for capturing ideas and staying organized",
            R.drawable.ic_welcome
        ),
        OnboardingPage(
            "Create & Organize",
            "Create notes easily and organize them with tags and categories",
            R.drawable.ic_organize
        ),
        OnboardingPage(
            "Sync Everywhere",
            "Access your notes from anywhere with cloud synchronization",
            R.drawable.ic_sync
        )
    )
    val onboardingItems = _onboardingPages

    fun saveWelcomeState(){
        prefs.putBoolean(AppConstants.IS_WELCOME_SEEN,true)
    }
}