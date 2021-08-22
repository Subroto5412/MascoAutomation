package com.bd.mascogroup.automation.data.remote



import com.bd.mascogroup.automation.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ApiServiceCalling {

    private val TAG = "--ApiService"

    // post request builder
    /*fun loginApiCall() = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ApiWorker.gsonConverter)
            .client(ApiWorker.client)
            .build()
            .create(ApiService::class.java)!!*/

   /* fun orderApiCall() = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ApiWorker.gsonConverter)
        .client(ApiWorker.client)
        .build()
        .create(ApiServiceOrder::class.java)!!*/

  /*  fun productApiCall() = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ApiWorker.gsonConverter)
            .client(ApiWorker.client)
            .build()
            .create(ApiServiceProduct::class.java)!!*/

    fun generalAPPSApiCall() = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ApiWorker.gsonConverter)
            .client(ApiWorker.client)
            .build()
            .create(ApiService::class.java)!!

}