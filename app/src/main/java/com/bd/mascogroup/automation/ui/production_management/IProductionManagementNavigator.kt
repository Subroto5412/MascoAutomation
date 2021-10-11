package com.bd.mascogroup.automation.ui.production_management

import com.bd.mascogroup.automation.ui.base.IBaseNavigator

interface IProductionManagementNavigator: IBaseNavigator {
    fun openAttendanceActivity()
    fun openLeaveActivity()
    fun openTaxActivity()
    fun openLWPActivity()
    fun openHPDActivity()
    fun openHPDetailsActivity()
    fun openBWPDActivity()
}