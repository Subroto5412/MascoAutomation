package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.model.db.Searchlist

class SearchListCardData constructor(val searchlist: Searchlist){
    val activity_name:String
    get() = searchlist.activity_name

    val search_name:String
        get() = searchlist.search_name

    val module_name:String
        get() = searchlist.module_name
}