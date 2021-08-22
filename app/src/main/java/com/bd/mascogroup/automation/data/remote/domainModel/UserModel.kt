package com.bd.mascogroup.automation.data.remote.domainModel

data class UserModel (
    val id : Int,
    val name : String,
    val username : String,
    val email : String,
    val address : Address,
    val phone : String,
    val website : String,
    val company : Company
)