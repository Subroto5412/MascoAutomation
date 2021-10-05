package com.bd.mascogroup.automation.data.remote



import com.bd.mascogroup.automation.BuildConfig
import com.bd.mascogroup.automation.utils.AppConstants
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ApiServiceCalling {

    private val TAG = "--ApiService"


    class OAuthInterceptor(private val tokenType: String, private val acceessToken: String): Interceptor {

        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request = chain.request()
            request = request.newBuilder().header("Authorization", "$tokenType ${AppConstants.acceessToken}").build()

            return chain.proceed(request)
        }
    }

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

    fun generalWebApiCall() = Retrofit.Builder()
            .baseUrl(BuildConfig.WEB_API_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ApiWorker.gsonConverter)
            .client(ApiWorker.client)
            .build()
            .create(ApiService::class.java)!!

    fun generalMisApiCall() = Retrofit.Builder()
        .baseUrl(BuildConfig.MIS_API_BASE)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ApiWorker.gsonConverter)
        .client(ApiWorker.client)
        .build()
        .create(ApiService::class.java)!!

    fun generalMisApiCallToken() = Retrofit.Builder()
            .baseUrl(BuildConfig.MIS_API_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ApiWorker.gsonConverter)
            .client(ApiWorkerWithToken.client)
            .build()
            .create(ApiService::class.java)!!

}