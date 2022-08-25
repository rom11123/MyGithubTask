package com.example.mygithubtask.domain.use_cases

import com.example.mygithubtask.common.Resource
import com.example.mygithubtask.data.model.User
import com.example.mygithubtask.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetUserProfileUseCase(private val repository:UserRepository) {
    operator fun invoke(): Flow<Resource<User>> = flow {
        try {

            emit(Resource.Loading<User>())
            val response = repository.getUserProfile().body()
            emit(Resource.Success<User>(response!!))
        } catch(e: HttpException) {
            emit(Resource.Error<User>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<User>("Couldn't reach server. Check your internet connection."))
        }
    }
}