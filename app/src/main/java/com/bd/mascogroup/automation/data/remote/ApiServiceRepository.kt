package com.bd.mascogroup.automation.data.remote

import com.bd.mascogroup.automation.data.remote.domainModel.UserModel
import io.reactivex.Single
import javax.inject.Inject

class ApiServiceRepository @Inject constructor(private val apiService: ApiService) {

    fun getUserServiceRepository(): Single<UserModel>
    {
        return apiService.getUserModel()
    }
}