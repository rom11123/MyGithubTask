package com.example.mygithubtask.presentation.settings_screen

import com.example.mygithubtask.MyApplication

class ThemeSettings:MyApplication() {
    companion object{
        var   PREFERENCES = "preferences"

        var CUSTOM_THEME = "customTheme"
        var LIGHT_THEME = "lightTheme"
        var DARK_THEME = "darkTheme"


    }
    private var customTheme: String? = null

    fun getCustomTheme(): String? {
        return customTheme
    }

    fun setCustomTheme(customTheme: String?) {
        this.customTheme = customTheme
    }
}