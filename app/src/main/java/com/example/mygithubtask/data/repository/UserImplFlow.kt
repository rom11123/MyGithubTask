package com.example.mygithubtask.data.repository

import androidx.room.withTransaction
import com.example.mygithubtask.common.networkBoundResource
import com.example.mygithubtask.data.local.room.UserDao
import com.example.mygithubtask.data.local.room.UserDataBase
import com.example.mygithubtask.data.remote.ApiService
import kotlinx.coroutines.delay

class UserImplFlow(private val api:ApiService, private val db:UserDataBase, private val dao:UserDao) {

//    private val dao = db.usersDao()

    fun getRestaurants() = networkBoundResource(
        query = {
            dao.readAllData()
        },
        fetch = {
            delay(2000)
            api.getUsers()
        },
        saveFetchResult = { user->
            db.withTransaction {
                dao.deleteAll()
                user.body()?.let { dao.insert(it) }
            }
        }
    )
}