package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class OrderPostData (
    @SerializedName("deliveryman_id") var deliveryman_id: Int
)