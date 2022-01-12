package com.example.testbts.services

import com.example.testbts.model.*
import com.example.testbts.model.entities.CheckListResponse
import com.example.testbts.model.entities.LoginResponse
import com.example.testbts.model.entities.RegisterResponse
import io.reactivex.Observable
import retrofit2.http.*

public interface ApiService {
    @POST("register")
    fun register(
        @Body body: RegisterModel?
    ): Observable<GenericResponse<RegisterResponse>?>?

    @POST("login")
    fun login(
        @Body body: LoginModel?
    ): Observable<GenericResponse<LoginResponse>?>?

    @POST("checklist")
    fun addCheckList(
        @Header("Authorization") token: String,
        @Body body: CheckListModel?
    ): Observable<GenericResponse<CheckListResponse>>?

    @GET("checklist")
    fun getCheckList(
        @Header("Authorization") token: String,
    ): Observable<CheckListResponseModel>?

    @DELETE("checklist/{checklistId}")
    fun deleteCheckList(
        @Header("Authorization") token: String,
        @Path("checklistId") id: Int
    ): Observable<GenericResponse<CheckListResponse>>?
}