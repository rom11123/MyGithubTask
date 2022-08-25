package com.example.mygithubtask.presentation.my_profile_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygithubtask.common.Resource
import com.example.mygithubtask.domain.use_cases.GetUserProfileUseCase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MyProfileViewModel(private val useCase: GetUserProfileUseCase) : ViewModel() {
    private val _state = MutableStateFlow(MyProfileState())
    val state: Flow<MyProfileState> = _state

    init {
        getUserProfile()

    }

    private fun getUserProfile() {
        useCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MyProfileState(user = result.data)
                }
                is Resource.Error -> {
                    _state.value = MyProfileState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MyProfileState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}