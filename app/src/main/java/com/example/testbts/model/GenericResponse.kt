package com.example.testbts.model

import android.os.Parcelable

data class GenericResponse<T : Parcelable>(
    val statusCode: Int,
    val errorMessage: String,
    val message: String,
    val data: T?
)