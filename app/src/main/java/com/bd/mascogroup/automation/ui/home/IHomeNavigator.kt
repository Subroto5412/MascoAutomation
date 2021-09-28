package com.bd.mascogroup.automation.ui.home

import com.bd.mascogroup.automation.ui.base.IBaseNavigator

interface IHomeNavigator: IBaseNavigator {
    fun openLoginScreen()
    fun openAttendanceActivity()
    fun openLeaveActivity()
    fun openTaxActivity()
}