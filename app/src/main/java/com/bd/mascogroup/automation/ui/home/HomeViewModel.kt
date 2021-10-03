package com.bd.mascogroup.automation.ui.home

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.SearchListCardData
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.ui.login.LoginActivity
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.activity_home.*
import java.util.ArrayList
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IHomeNavigator>(dataManager, ISchedulerProvider) {

    private var activitySearchNames = ArrayList<String>()
    private var activityNamesList = ArrayList<SearchListCardData>()

    fun sideMenuDisplayInfo(context: Context, nameTv:TextView, empCodeTv:TextView, unitTv:TextView, phoneTv:TextView,photo:ImageView, activity_home_side_menu_logout_layout:View){
        nameTv.setText(dataManager.customerName)
        empCodeTv.setText(dataManager.empCode)
        unitTv.setText(dataManager.unitName)
        phoneTv.setText(dataManager.mobile)

        /*Glide.with(photo)
                .load(dataManager.customerPic)
                .into(photo)*/
        val photoCornerRadius = 35
        Glide.with(context)
                .load(dataManager.customerPic)
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(photoCornerRadius)))
                .placeholder(R.drawable.user)
                .error(R.drawable.user)
                .into(photo)

        activity_home_side_menu_logout_layout.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Do You Want to Logout from App?")
            //  builder.setMessage("Are you want to set the app background color to RED?")
            builder.setPositiveButton("YES"){dialog, which ->
                emptyData()
                navigator?.openLoginScreen()
            }
            builder.setNegativeButton("No"){dialog,which ->
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    fun getSearchName(
            context: Context,
            search_name_actv: AutoCompleteTextView
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
                                if (search_name_actv.text.toString().equals("daily attendance")){
                                    navigator?.openAttendanceActivity()
                                }else if (search_name_actv.text.toString().equals("leave history")){
                                    navigator?.openLeaveActivity()
                                }else if (search_name_actv.text.toString().equals("tax history")){
                                    navigator?.openTaxActivity()
                                }
                            }

                        }, {
                            //navigator?.handleError(throwable)
                        })
        )
    }

    fun emptyData(){
        dataManager.empCode = ""
        dataManager.mobile = ""
        dataManager.empId = ""
        dataManager.accessToken =""
        dataManager.refreshToken = ""
        AppConstants.acceessToken = ""
        dataManager.HRModule = ""
        dataManager.dailyAttendance = ""
        dataManager.leaveHistory = ""
        dataManager.taxHistory = ""
    }
}