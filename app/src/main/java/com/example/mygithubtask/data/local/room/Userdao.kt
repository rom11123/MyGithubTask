package com.example.mygithubtask.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mygithubtask.data.model.Users
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user:List<Users>)

    @Query("SELECT * FROM users ORDER BY id  ASC")
    fun readAllData(): Flow<List<Users>>


    @Query("DELETE FROM users" )
    suspend fun deleteAll()
}