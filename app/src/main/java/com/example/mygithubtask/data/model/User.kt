package com.example.mygithubtask.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("login")
    var username: String,
    var name: String,
    var email: String,

    ) : Parcelable
