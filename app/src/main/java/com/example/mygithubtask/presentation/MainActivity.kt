package com.example.mygithubtask.presentation

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mygithubtask.R

import com.example.mygithubtask.databinding.ActivityMainBinding
import com.example.mygithubtask.presentation.settings_screen.ThemeSettings

class MainActivity : AppCompatActivity() {
    val settings = ThemeSettings()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigation.setupWithNavController(navController)
        installSplashScreen()
        loadSharedPreferences()
//        supportActionBar?.hide()
    }

    private fun loadSharedPreferences() {
        val sharedPreferences: SharedPreferences? =
        getSharedPreferences(ThemeSettings.PREFERENCES, MODE_PRIVATE)
        val theme = sharedPreferences?.getString(ThemeSettings.CUSTOM_THEME, ThemeSettings.LIGHT_THEME)
        settings!!.setCustomTheme(theme)
        updateView()

    }
    private fun updateView() {
        val black = ContextCompat.getColor(this, android.R.color.black)
        val white = ContextCompat.getColor(this, android.R.color.white)
        if (settings!!.getCustomTheme() == ThemeSettings.DARK_THEME) {

          binding.mainView!!.setBackgroundColor(black)

        } else {

      binding.mainView!!.setBackgroundColor(white)

        }
    }
}