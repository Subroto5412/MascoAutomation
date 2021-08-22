package com.bd.mascogroup.automation.di.module

import androidx.databinding.library.baseAdapters.BuildConfig
import com.bd.mascogroup.automation.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
object NetworkModule {

    private const val NETWORK_CALL_TIMEOUT = 60

    /**
     * Provides the Article service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Article service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideArticleApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
              //  .baseUrl(BuildConfig.API_BASE)
                .baseUrl("https://10.0.2.2:44319/api/v1.0/deliveryman/")
                .client(
                        OkHttpClient.Builder()

                                .addInterceptor(
                                        HttpLoggingInterceptor()
                                                .apply {
                                                    level = if (BuildConfig.DEBUG)
                                                        HttpLoggingInterceptor.Level.BODY
                                                    else
                                                        HttpLoggingInterceptor.Level.NONE
                                                })
                                .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                                .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                                .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))


                .build()
    }
}