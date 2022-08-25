package com.example.mygithubtask.data.local.room.repo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mygithubtask.data.model.Repository
import com.example.mygithubtask.data.model.Users
import kotlinx.coroutines.flow.Flow


@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo:List<Repository>)

    @Query("SELECT * FROM repository ORDER BY id  ASC")
    fun readAllData(): Flow<List<Repository>>


    @Query("DELETE FROM repository" )
    suspend fun deleteAll()
}