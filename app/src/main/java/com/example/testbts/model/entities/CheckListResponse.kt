package com.example.testbts.model.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CheckListResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("items")
    var items: String = "",
    @SerializedName("checklistCompletionStatus")
    var checklistCompletionStatus: Boolean = false
): Parcelable {
}