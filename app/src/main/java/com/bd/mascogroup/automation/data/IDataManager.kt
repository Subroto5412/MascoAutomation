package com.bd.mascogroup.automation.data

import com.bd.mascogroup.automation.data.local.db.IDbHelper
import com.bd.mascogroup.automation.data.local.prefs.IPreferencesHelper
import com.bd.mascogroup.automation.data.remote.ApiService

interface IDataManager:IPreferencesHelper, IDbHelper {
}