package com.example.mygithubtask.data.local.room.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mygithubtask.data.model.Repository


@Database(entities = [Repository::class], version = 1)

abstract class RepoDatabase:RoomDatabase() {
    abstract fun repoDao(): RepoDao

}

