package com.example.mygithubtask.presentation.my_profile_screen

import com.example.mygithubtask.data.model.User

data class MyProfileState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)
