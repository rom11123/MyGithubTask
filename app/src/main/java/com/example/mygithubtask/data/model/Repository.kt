package com.example.mygithubtask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "repository")

data class Repository (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    @SerializedName("private")
    val isPrivate: Boolean,
    val language: String
        )
