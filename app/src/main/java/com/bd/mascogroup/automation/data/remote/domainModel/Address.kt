package com.bd.mascogroup.automation.data.remote.domainModel

data class Address (
    val street : String,
    val suite : String,
    val city : String,
    val zipcode : String,
    val geo : Geo
)