package com.example.mygithubtask.domain.repository

import com.example.mygithubtask.data.model.Repository
import com.example.mygithubtask.data.model.User
import com.example.mygithubtask.data.model.Users
import retrofit2.Response

interface UserRepository {
    suspend fun getUserRepository():Response<List<Repository>>
    suspend fun getUsers():Response<List<Users>>
    suspend fun getUserProfile():Response<User>

}