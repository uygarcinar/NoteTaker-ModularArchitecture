package com.app.notetaker.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.domain.repository.IPreferencesManager
import com.app.core.utils.AppConstants
import com.app.notetaker.domain.enums.SplashStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prefs : IPreferencesManager
) : ViewModel() {
    private val _splashCompleteStatus : MutableStateFlow<SplashStatus> = MutableStateFlow(SplashStatus.NONE)
    var splashCompleteStatus : StateFlow<SplashStatus> = _splashCompleteStatus
    fun checkAlreadyLoggedIn(){
        viewModelScope.launch(Dispatchers.IO) {
            if (prefs.getBoolean(AppConstants.IS_WELCOME_SEEN)){
                // is welcome screen seen once, redirect home screen
                _splashCompleteStatus.value = SplashStatus.REDIRECT_HOME
                Log.i("MainViewModel","User redirected to home.")
            }else {
                // redirect welcome
                _splashCompleteStatus.value = SplashStatus.REDIRECT_WELCOME
                Log.i("MainViewModel","User redirected to welcome.")
            }
        }
    }
}