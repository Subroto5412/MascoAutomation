package com.bd.mascogroup.automation.ui.hr_info

import android.R
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.FinancialYearCardData
import com.bd.mascogroup.automation.data.model.domainModel.SearchListCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.ui.hr_info.attendance.AttendanceActivity
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class HRInfoViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IHRInfoNavigator>(dataManager, ISchedulerProvider) {

    private var activitySearchNames = ArrayList<String>()
    private var activityNamesList = ArrayList<SearchListCardData>()

    fun accessToken(){
        AppConstants.acceessToken = ""
        AppConstants.acceessToken = dataManager.accessToken
        Log.e("----------------","-----------AppConstants.acceessToken---------"+AppConstants.acceessToken)
    }

    fun buttonPermission(context: Context, activity_hr_main_daily_attendance_cl:ConstraintLayout, activity_hr_main_leave_details_cl:ConstraintLayout, activity_hr_main_income_tax_cl:ConstraintLayout){
        if (!dataManager.dailyAttendance.equals("daily_attendance")){
            activity_hr_main_daily_attendance_cl.isGone = true
        }

        if (!dataManager.leaveHistory.equals("leave_history")){
            activity_hr_main_leave_details_cl.isGone = true
        }

        if (!dataManager.taxHistory.equals("tax_history")){
            activity_hr_main_income_tax_cl.isGone = true
        }
    }

    fun getSearchName(
            context: Context,
            search_name_actv:AutoCompleteTextView
    ) {
        activityNamesList.clear()
        activitySearchNames.clear()
        compositeDisposable.add(
                dataManager
                        .getSearchByActivityName(search_name_actv.text.toString())
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            response.forEach {
                                activityNamesList.add(SearchListCardData(it))
                            }

                            for (i in 0 until activityNamesList.size) {
                                val activityNames = HashMap<String, String>()
                                activityNames.put("activity_name", activityNamesList.get(i).activity_name.toString())
                                activityNames.put("search_name", "" + activityNamesList.get(i).search_name)
                                activityNames.put("module_name", activityNamesList.get(i).module_name)

                                activitySearchNames.add(activityNamesList.get(i).search_name)
                            }

                            val adapter = ArrayAdapter(context,
                                    android.R.layout.simple_list_item_1, activitySearchNames)
                            search_name_actv.setAdapter(adapter)

                            search_name_actv.setOnDismissListener {
                                if (search_name_actv.text.toString().trim().equals("daily attendance")){
                                    navigator?.openAttendanceActivity()
                                }else if (search_name_actv.text.toString().trim().equals("leave history")){
                                    navigator?.openLeaveActivity()
                                }else if (search_name_actv.text.toString().trim().equals("tax history")){
                                    navigator?.openTaxActivity()
                                } else if (search_name_actv.text.toString().trim().equals("buyer wise production data")){
                                    navigator?.openBWPDActivity()
                                } else if (search_name_actv.text.toString().trim().equals("hourly production data")){
                                    navigator?.openHPDActivity()
                                } else if (search_name_actv.text.toString().trim().equals("hourly production details")){
                                    navigator?.openHPDetailsActivity()
                                } else if (search_name_actv.text.toString().trim().equals("line wise production")){
                                    navigator?.openLWPActivity()
                                }
                            }

                        }, {
                            //navigator?.handleError(throwable)
                        })
        )
    }
}