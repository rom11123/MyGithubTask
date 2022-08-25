package com.example.mygithubtask.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mygithubtask.data.model.Users


@Database(entities = [Users::class], version = DB_VERSION)
abstract class UserDataBase:RoomDatabase() {

    abstract fun usersDao(): UserDao

}

private const val DB_VERSION = 1