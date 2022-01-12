package com.example.testbts.services.register

import com.example.testbts.model.entities.RegisterResponse
import com.example.testbts.model.GenericResponse


public interface IViewRegister {
    fun onSuccessRegister(data: GenericResponse<RegisterResponse>)
    fun onError(message: String?)
}