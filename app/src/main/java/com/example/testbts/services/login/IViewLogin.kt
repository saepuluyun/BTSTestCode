package com.example.testbts.services.login

import com.example.testbts.model.GenericResponse
import com.example.testbts.model.entities.LoginResponse


public interface IViewLogin {
    fun onSuccessLogin(data: GenericResponse<LoginResponse>)
    fun onError(message: String?)
}