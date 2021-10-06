package com.bd.mascogroup.automation.ui.hr_info.leave.apply

import android.R
import android.content.Context
import android.widget.*
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.LeavelAvailListCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.LeaveApplyRequest
import com.bd.mascogroup.automation.data.remote.domainModel.LeavelAvailListResponse
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap


class LeaveApplyViewModel @Inject constructor(
    dataManager: IDataManager,
    ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILeaveApplyNavigator>(dataManager, ISchedulerProvider){
    private var leavelAvailListCardData = ArrayList<LeavelAvailListCardData>()
    private var LeavelName = ArrayList<String>()

    fun getLeaveList(
        context: Context,
        LeavelNameSp: Spinner,
        activity_leave_apply_designation_value: TextView
    ) {
        leavelAvailListCardData.clear()
        AppConstants.HasLeaveList.clear()

        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCallToken().getLeaveList()

            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ leaveResponse ->

                    var lalr = LeavelAvailListResponse()
                    lalr.leaveTypeNo = 0
                    lalr.abbreviation = "Select Leave"
                    lalr.maxBalance = 0
                    lalr.avail = 0
                    leavelAvailListCardData.add(LeavelAvailListCardData(lalr))

                    activity_leave_apply_designation_value.setText(leaveResponse.emp_details.desigEName)

                    leaveResponse._leaveAvailList.forEach {
                        leavelAvailListCardData.add(LeavelAvailListCardData(it))
                    }

                    LeavelName.clear()
                    for (i in 0 until leavelAvailListCardData.size) {
                        val leaveList = HashMap<String, String>()
                        leaveList.put(
                            "leaveTypeNo",
                            leavelAvailListCardData.get(i).leaveTypeNo.toString()
                        )
                        leaveList.put(
                            "abbreviation",
                            "" + leavelAvailListCardData.get(i).abbreviation
                        )
                        leaveList.put(
                            "maxBalance",
                            leavelAvailListCardData.get(i).maxBalance.toString()
                        )
                        leaveList.put(
                            "avail",
                            (leavelAvailListCardData.get(i).maxBalance - leavelAvailListCardData.get(
                                i
                            ).avail).toString()
                        )

                        AppConstants.HasLeaveList.add(leaveList)
                        LeavelName.add(leavelAvailListCardData.get(i).abbreviation)
                    }
                    val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                        context,
                        R.layout.simple_spinner_item,
                        LeavelName
                    )
                    spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item) // The drop down view
                    LeavelNameSp.setAdapter(spinnerArrayAdapter)

                    UtilMethods.hideLoading()
                }, { error ->
                    UtilMethods.hideLoading()
                    //  UtilMethods.showLongToast(context, error.message.toString())
                }
                )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }


    fun getLeaveApply(
            context: Context,
            leave_type:Int,
            leave_days:Int,
            apply_from:String,
            apply_to:String,
            reason:String

    ) {
             if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCallToken().leaveApply(LeaveApplyRequest(leave_type,leave_days,apply_from,apply_to,reason))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ leaveApplyResponse ->
                        UtilMethods.hideLoading()
                        if (leaveApplyResponse.response==true){
                            Toast.makeText(context,"Leave Successfully Submiited!",Toast.LENGTH_LONG).show()
                            navigator?.goToLeaveScreen()
                        }
                    }, { error ->
                        UtilMethods.hideLoading()
                        //  UtilMethods.showLongToast(context, error.message.toString())
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun dataSetting(
        context: Context,
        activity_leave_apply_id_value: TextView,
        activity_leave_apply_name_value: TextView,
        activity_leave_apply_designation_value: TextView,
        activity_leave_apply_from_value: TextView,
        activity_leave_apply_to_value: TextView,
        activity_leave_apply_total_day_value:TextView,
        activity_leave_apply_pic_im:ImageView
    ){
        activity_leave_apply_id_value.setText(dataManager.empCode)
        activity_leave_apply_name_value.setText(dataManager.customerName)
        activity_leave_apply_designation_value.setText("")
        activity_leave_apply_from_value.setText(getCurrentDate())
        activity_leave_apply_to_value.setText(getCurrentDate())

//        Glide.with(context).load(dataManager.customerPic).into(activity_leave_apply_pic_im)
        val photoCornerRadius = 60
        Glide.with(context)
            .load(dataManager.customerPic)
            .circleCrop()
            .into(activity_leave_apply_pic_im);

        getLeaveDays(activity_leave_apply_from_value.text.toString(), activity_leave_apply_to_value.text.toString(),activity_leave_apply_total_day_value)
    }

    fun getCurrentDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date())
    }

    fun getLeaveDays(activity_leave_apply_from_value:String, activity_leave_apply_to_value:String,activity_leave_apply_total_day_value:TextView){
        val myFormat = SimpleDateFormat("yyyy-MM-dd")
        try {
            val dateBefore = myFormat.parse(activity_leave_apply_from_value)
            val dateAfter = myFormat.parse(activity_leave_apply_to_value)
            val difference = dateAfter.time - dateBefore.time
            val daysBetween = (difference / (1000 * 60 * 60 * 24)).toFloat()
            activity_leave_apply_total_day_value.setText("${daysBetween.toInt()+1}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}