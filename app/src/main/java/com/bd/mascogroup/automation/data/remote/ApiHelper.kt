package com.bd.mascogroup.automation.data.remote

import com.google.gson.Gson
import com.bd.mascogroup.automation.data.local.prefs.IPreferencesHelper
import com.bd.mascogroup.automation.data.remote.domainModel.LoginResponse
import com.bd.mascogroup.automation.utils.AppConstants
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHelper @Inject
constructor(
        private val mGson: Gson,
        private val mPreferencesHelper: IPreferencesHelper //,private val mRetrofitClient: RetrofitClient
) {



}