package com.example.testbts.model.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterResponse(
    @SerializedName("is_creator")
    var isGroupCreator: Int = 0
): Parcelable {
}