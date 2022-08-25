package com.example.mygithubtask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity(tableName = "users")
data class Users(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("login")
    val login: String?,
) : Serializable

