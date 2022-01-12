package com.example.testbts.services

import com.example.testbts.model.RegisterResponse
import com.example.testbts.model.entities.GenericResponse


public interface IViewService {
    fun onSuccessRegister(data: GenericResponse<RegisterResponse>)
    fun onError(message: String?)
}