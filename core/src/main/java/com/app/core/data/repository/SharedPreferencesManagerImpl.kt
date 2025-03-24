package com.app.core.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.app.core.domain.repository.IPreferencesManager
import com.app.core.utils.AppConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesManagerImpl @Inject constructor(
    @ApplicationContext private val context : Context
): IPreferencesManager {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(AppConstants.SECURE_PREF_CODE, Context.MODE_PRIVATE)
    override fun putString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override fun getString(key: String, defaultValue: String): String {
        return prefs.getString(key, defaultValue) ?: defaultValue
    }

    override fun putBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    override fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }

}