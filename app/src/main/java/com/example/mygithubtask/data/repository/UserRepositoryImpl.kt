package com.example.mygithubtask.data.repository

import com.example.mygithubtask.data.model.Repository
import com.example.mygithubtask.data.model.User
import com.example.mygithubtask.data.model.Users
import com.example.mygithubtask.data.remote.ApiService
import com.example.mygithubtask.domain.repository.UserRepository
import retrofit2.Response

class UserRepositoryImpl(private val api:ApiService):UserRepository {
    override suspend fun getUserRepository(): Response<List<Repository>> {
        return    api.getUserRepository()

    }

    override suspend fun getUsers(): Response<List<Users>> {
        return api.getUsers()

    }

    override suspend fun getUserProfile(): Response<User> {
        return api.getUserProfile()
    }
}