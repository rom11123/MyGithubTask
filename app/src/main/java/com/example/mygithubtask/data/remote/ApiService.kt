package com.example.mygithubtask.data.remote

import com.example.mygithubtask.common.Constants
import com.example.mygithubtask.data.model.Repository
import com.example.mygithubtask.data.model.User
import com.example.mygithubtask.data.model.Users
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.MY_REPOSITORY_ENDPOINT)
    suspend fun getUserRepository(): Response<List<Repository>>

    @GET(Constants.ALL_USERS_ENDPOINT)
    suspend fun getUsers(): Response<List<Users>>

    @GET(Constants.MY_PROFILE_ENDPOINT)
    suspend fun getUserProfile(): Response<User>


}