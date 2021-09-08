package com.bd.mascogroup.automation.data.remote


import com.bd.mascogroup.automation.data.remote.domainModel.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {
    @GET("/users/2")
    fun getUserModel(): Single<UserModel>

    @Headers("Content-Type: application/json")
    @POST("Deliveryman/login")
    fun doLogin1(
            //@Query("Authorization") authorizationKey: String, // authentication header
            @Body loginPostData: LoginRequest): Observable<LoginResponse>


    @Headers("Content-Type: application/json")
    @POST("LogIn/GetUserImageById")
    fun doLoginUserId(
        @Body loginByUserIdRequest: LoginByUserIdRequest
    ): Observable<LoginByUserIdResponse>

    @Headers("Content-Type: application/json")
    @POST("v1/LoginAccess/login")
    fun doLogin(
        @Body loginRequest: LoginRequest
    ): Observable<LoginResponse>

    @Headers("Content-Type: application/json")
    @GET("v1/LoginAccess/getImageById")
    fun getLoginImage(@Query("empCode") empCode: String): Observable<LoginImageResponse> // body data

    @Headers("Content-Type: application/json")
    @POST("v1/LoginAccess/sendOTP")
    fun doSendOTP(
        @Body otpRequest: OtpRequest
    ): Observable<OtpResponse>

    @Headers("Content-Type: application/json")
    @POST("v1/LoginAccess/verifyOTP")
    fun doVerifyOTP(
        @Body verifyOTPRequest: VerifyOTPRequest
    ): Observable<VerifyOTPResponse>

    @Headers("Content-Type: application/json")
    @POST("v1/LoginAccess/register")
    fun doRegister(
            @Body registerRequest: RegisterRequest
    ): Observable<RegisterResponse>
}