package com.example.mygithubtask.presentation.repository_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.example.mygithubtask.common.Resource
import com.example.mygithubtask.data.local.room.repo.RepoDao
import com.example.mygithubtask.data.local.room.repo.RepoDatabase
import com.example.mygithubtask.domain.use_cases.GetUserRepositoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RepositoryViewModel(private val repositoryUseCase: GetUserRepositoryUseCase,
                          private val dao: RepoDao,


) : ViewModel() {
    private val _state = MutableStateFlow(RepositoryState())
    val state: Flow<RepositoryState> = _state

    val readAllData = dao.readAllData().asLiveData()

    init {

        getData()
    }

    private fun getData() {
        repositoryUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = RepositoryState(data = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = RepositoryState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = RepositoryState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}