package com.example.mygithubtask.presentation.repository_screen

import com.example.mygithubtask.data.model.Repository

data class RepositoryState (
    val isLoading: Boolean = false,
    val data: List<Repository> = emptyList(),
    val error: String = ""
        )