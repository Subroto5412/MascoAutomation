package com.bd.mascogroup.automation.data.remote

import com.bd.mascogroup.automation.utils.AppConstants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

object ApiWorkerWithToken {
    private var mClient: OkHttpClient? = null
    private var mGsonConverter: GsonConverterFactory? = null



    /**
     * Don't forget to remove Interceptors (or change Logging Level to NONE)
     * in production! Otherwise people will be able to see your request and response on Log Cat.
     */
    val client: OkHttpClient
        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        get() {
            if (mClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(25, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)  /// show all JSON in logCat
                mClient = httpBuilder.addInterceptor(ApiServiceCalling.OAuthInterceptor("Bearer", AppConstants.acceessToken)).build()

            }
            return mClient!!
        }


    val gsonConverter: GsonConverterFactory
        get() {
            if(mGsonConverter == null){
                mGsonConverter = GsonConverterFactory
                        .create(GsonBuilder()
                                .setLenient()
                                .disableHtmlEscaping()
                                .create())
            }
            return mGsonConverter!!
        }
}