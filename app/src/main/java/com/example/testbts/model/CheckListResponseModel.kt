package com.example.testbts.model

import com.example.testbts.model.entities.CheckListResponse

data class CheckListResponseModel(
    val statusCode: Int,
    val errorMessage: String,
    val message: String,
    val data: MutableList<CheckListResponse>
)