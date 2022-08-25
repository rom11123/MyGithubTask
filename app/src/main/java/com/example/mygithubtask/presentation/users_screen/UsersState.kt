package com.example.mygithubtask.presentation.users_screen

import com.example.mygithubtask.data.model.Users

data class UsersState  (
    val isLoading: Boolean = false,
    val users: List<Users> = emptyList(),
    val error: String = ""
)
