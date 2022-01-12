package com.example.testbts.services

import com.example.testbts.model.LoginModel
import com.example.testbts.model.RegisterModel
import com.example.testbts.model.entities.GenericResponse
import com.example.testbts.model.RegisterResponse
import io.reactivex.Observable
import kotlinx.android.parcel.RawValue
import retrofit2.http.*

public interface ApiService {
    @POST("register")
    fun register(
        @Body body: RegisterModel?
    ): Observable<GenericResponse<RegisterResponse>?>?

    @POST("login")
    fun login(
        @Body body: LoginModel?
    ): Observable<GenericResponse<RegisterResponse>?>?
}