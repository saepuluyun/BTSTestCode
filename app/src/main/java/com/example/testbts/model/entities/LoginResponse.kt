package com.example.testbts.model.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(
    @SerializedName("token")
    var token: String = ""
): Parcelable {
}