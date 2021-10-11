package com.bd.mascogroup.automation.ui.hr_info

import com.bd.mascogroup.automation.ui.base.IBaseNavigator

interface IHRInfoNavigator: IBaseNavigator {

    fun openAttendanceActivity()
    fun openLeaveActivity()
    fun openTaxActivity()

    fun openLWPActivity()
    fun openHPDActivity()
    fun openHPDetailsActivity()
    fun openBWPDActivity()
}