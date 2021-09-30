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
    @POST("v1/LoginAccess/set-fcm-token")
    fun doFCMToken(
            @Body tokenRequest: TokenRequest
    ): Observable<TokenResponse>

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

    @Headers("Content-Type: application/json")
    @GET("v1/Attendance/getFinalYear")
    fun getFinancialYear(): Observable<FinancialYearResponse> // body data

    @Headers("Content-Type: application/json")
    @POST("v1/Attendance/details")
    fun getAllAttendance(
            @Body dailyAttendanceRequest: DailyAttendanceRequest
    ): Observable<AttendanceHistoryResponse>


    @Headers("Content-Type: application/json")
    @POST("v1/Attendance/summary")
    fun getAttendanceSummary(
            @Body dailyAttendanceRequest: DailyAttendanceRequest
    ): Observable<DailyAttendanceSummaryResponse>

    @Headers("Content-Type: application/json")
    @POST("v1/leave/leave-history")
    fun getLeaveHistory(
            @Body leaveSummaryRequest: LeaveSummaryRequest
    ): Observable<LeaveSummaryResponse>

    @Headers("Content-Type: application/json")
    @POST("v1/leave/avail-history")
    fun getAvailHistory(
            @Body leaveSummaryRequest: AvailSummaryRequest
    ): Observable<AvailSummaryResponse>

    @Headers("Content-Type: application/json")
    @POST("v1/tax/tax-deduct")
    fun getTaxDeduct(
            @Body taxDeductionRequest: TaxDeductionRequest
    ): Observable<TaxDeductionResponse>

    @Headers("Content-Type: application/json")
    @GET("v1/tax/tax-year")
    fun getTaxYear(): Observable<TaxYearResponse> // body data

    @Headers("Content-Type: application/json")
    @POST("v1/LoginAccess/refresh-token")
    fun getRefreshToken(
            @Body registerRequest: RefreshTokenRequest
    ): Observable<RefreshTokenResponse>

    @Headers("Content-Type: application/json")
    @GET("v1/leave/form-data")
    fun getLeaveList(): Observable<LeavelListResponse> // body data

    @Headers("Content-Type: application/json")
    @POST("v1/leave/submit")
    fun leaveApply(
            @Body leaveApplyRequest: LeaveApplyRequest
    ): Observable<LeaveApplyResponse>

}