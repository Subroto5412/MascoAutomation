package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.model.db.User

class UserCardData constructor(val user: User){
    val email: String
        get() = user.email

    val userName: String
        get() = user.userName

    val userId: String
        get() = user.userId

}