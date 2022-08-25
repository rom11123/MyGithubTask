package com.example.mygithubtask.presentation.settings_screen

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.mygithubtask.R
import com.example.mygithubtask.databinding.FragmentSettingsBinding
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsFragment : Fragment() {
//    private lateinit var viewModel:SettingViewModel
    private lateinit var binding:FragmentSettingsBinding
    private var parentView: View? = null
    private var themeSwitch: SwitchMaterial? = null
    private var themeTV: TextView? = null
    private  var titleTV: TextView? = null
    private val settings =  ThemeSettings()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(SettingViewModel::class.java)
        setToolbar()
        initWidgets()
        loadSharedPreferences()
        initSwitchListener()
        updateView()
    }
    private fun setToolbar() {
      binding.topAppBar.setNavigationOnClickListener {
          findNavController().navigate(R.id.action_settingsFragment_to_myProfileFragment)

//            parentFragmentManager.popBackStack()
        }
    }

    private fun initWidgets() {
        themeTV = binding.themeTV
        titleTV = binding.titleTV
        themeSwitch = binding.themeSwitch
        parentView = binding.parentView
    }

    private fun loadSharedPreferences() {
        val sharedPreferences: SharedPreferences? =
            parentFragment?.activity?.getSharedPreferences(ThemeSettings.PREFERENCES,
                Context.MODE_PRIVATE)
        val theme = sharedPreferences?.getString(ThemeSettings.CUSTOM_THEME, ThemeSettings.LIGHT_THEME)
        settings.setCustomTheme(theme)
        updateView()
    }

    private fun initSwitchListener() {
        themeSwitch!!.setOnCheckedChangeListener { compoundButton, checked ->
            if (checked) settings.setCustomTheme(ThemeSettings.DARK_THEME) else settings.setCustomTheme(
                ThemeSettings.LIGHT_THEME)
            val editor: SharedPreferences.Editor? =
                parentFragment?.activity?.getSharedPreferences(ThemeSettings.PREFERENCES,
                    Context.MODE_PRIVATE)?.edit()?.apply {
                    putString(ThemeSettings.CUSTOM_THEME, settings.getCustomTheme())
                    apply()
                }
            updateView()
        }
    }

    private fun updateView() {
        val black = ContextCompat.getColor(requireContext(), android.R.color.black)
        val white = ContextCompat.getColor(requireContext(), android.R.color.white)
        if (settings.getCustomTheme() == ThemeSettings.DARK_THEME) {
            titleTV!!.setTextColor(white)
            themeTV!!.setTextColor(white)
            themeTV!!.text = "Dark"
            parentView!!.setBackgroundColor(black)
            themeSwitch!!.isChecked = true
        } else {
            titleTV!!.setTextColor(black)
            themeTV!!.setTextColor(black)
            themeTV!!.text = "Light"
            parentView!!.setBackgroundColor(white)
            themeSwitch!!.isChecked = false
        }
    }




}