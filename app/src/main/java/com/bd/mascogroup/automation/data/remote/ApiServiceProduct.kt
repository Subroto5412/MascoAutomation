package com.bd.mascogroup.automation.data.remote

import com.bd.mascogroup.automation.data.remote.domainModel.OrderPostData
import com.bd.mascogroup.automation.data.remote.domainModel.OrderResponse
import com.bd.mascogroup.automation.data.remote.domainModel.ProductResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServiceProduct {

    @Headers("Content-Type: application/json")
    @POST("Deliveryitem/GetDeliveryItemDetailByDeliveryMan")
    fun doProduct(
            @Body orderPostData: OrderPostData
    ): Observable<ProductResponse> // body dataa
}