package com.bd.mascogroup.automation.data.remote

import com.bd.mascogroup.automation.data.remote.domainModel.*
import io.reactivex.Observable
import retrofit2.http.*

interface ApiServiceOrder {

    @Headers("Content-Type: application/json")
    @POST("Deliveryitem/GetDeliveryItemHeadByDeliveryMan")
    fun doOrder(
        @Body orderPostData: OrderPostData
    ): Observable<OrderResponse> // body dataa
}