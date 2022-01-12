package com.example.testbts.services.checklist

import com.example.testbts.model.CheckListResponseModel
import com.example.testbts.model.GenericResponse
import com.example.testbts.model.entities.CheckListResponse
import com.example.testbts.model.entities.LoginResponse


public interface IViewCheckList {
    fun onSuccessGetList(data: CheckListResponseModel)
    fun onSuccessAddList(data: GenericResponse<CheckListResponse>)
    fun onSuccessDeleteList(data: GenericResponse<CheckListResponse>)
    fun onError(message: String?)
}