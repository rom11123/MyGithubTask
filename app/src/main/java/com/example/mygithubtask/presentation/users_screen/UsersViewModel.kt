package com.example.mygithubtask.presentation.users_screen
//
import androidx.lifecycle.*
import com.example.mygithubtask.common.Resource
import com.example.mygithubtask.data.local.room.UserDao
import com.example.mygithubtask.data.model.Users
import com.example.mygithubtask.domain.use_cases.GetUsersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class UsersViewModel(
    private val usersUseCase: GetUsersUseCase,
    private val dao: UserDao,
) : ViewModel() {
    private val _state = MutableStateFlow(UsersState())
    val state: Flow<UsersState> = _state

  val allData = dao.readAllData().asLiveData()
    init {
        getUsers()
    }



    private fun getUsers() {
        usersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UsersState(users = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = UsersState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = UsersState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}