package com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.leave_approval_form

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.TextView
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.db.Leaveapprovallist
import com.bd.mascogroup.automation.data.model.domainModel.IncomeTaxDeductionCardData
import com.bd.mascogroup.automation.data.model.domainModel.LeaveApprovalListCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.*
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class LeaveApprovalFormViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILeaveApprovalFormNavigator>(dataManager, ISchedulerProvider) {

    var leaveApprovalListObserverArrayList: ObservableList<LeaveApprovalListCardData> = ObservableArrayList()
    var leaveApprovalListLiveData: MutableLiveData<List<LeaveApprovalListCardData>> = MutableLiveData()
    var PRIVATE_MODE = 0


    fun getLeaveApproval(context: Context) {
       // deleteAllLeaveApprovalData()
        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getLeavePendingApproval(LeavePendingApprovalRequest(dataManager.empId.toInt(), dataManager.empId.toInt()))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ pendingApprovalResponse ->
                        pendingApprovalResponse._leavePendingList.forEach {
                            var leaveapprovallist = Leaveapprovallist()
                            leaveapprovallist.applyNo = it.applyNo
                            leaveapprovallist.empCode = it.emP_CODE
                            leaveapprovallist.empName = it.emP_ENAME
                            leaveapprovallist.designation = it.desigEName
                            leaveapprovallist.applyFromDate = it.applyFromDate
                            leaveapprovallist.applyToDate = it.applyToDate
                            leaveapprovallist.applyDays = it.applyDays
                            leaveapprovallist.leaveNo = it.leaveNo
                            leaveapprovallist.leaveType = it.leaveType
                            leaveapprovallist.leaveMax = it.leaveMax
                            leaveapprovallist.leaveAvail = it.leaveAvail
                            leaveapprovallist.status = "false"

                            val sharedPref: SharedPreferences = context.getSharedPreferences( leaveapprovallist.empCode, PRIVATE_MODE)
                            val editor = sharedPref.edit()
                            editor.putString( leaveapprovallist.empCode, "false")
                            editor.apply()

                            insertOrderData(leaveapprovallist)
                        }

                        UtilMethods.hideLoading()
                    }, { error ->
                        UtilMethods.hideLoading()
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun insertOrderData(leaveapprovallist: Leaveapprovallist) {

        compositeDisposable.add(
                dataManager
                        .insertLeaveApproval(leaveapprovallist)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            getAllLeaveApprovalData()
                            Log.e("---------","-------response------"+response)
                        }, {
                            //navigator?.handleError(throwable)
                        })
        )
    }

    fun getAllLeaveApprovalData() {
         var leaveApprovalListItems = ArrayList<LeaveApprovalListCardData>()
        compositeDisposable.add(
                dataManager
                        .loadAllLeaveApproval()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            response.forEach {
                                leaveApprovalListItems.add(LeaveApprovalListCardData(it))
                            }
                            leaveApprovalListLiveData.value = leaveApprovalListItems

                        }, {
                            //navigator?.handleError(throwable)
                        })
        )
    }

    fun deleteAllLeaveApprovalData(context:Context) {
        compositeDisposable.add(
                dataManager
                        .deleteAllLeaveApprovalData()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            getLeaveApproval(context)
                            Log.e("---------","----response---hh--"+response)
                        }, {
                            //navigator?.handleError(throwable)
                        })
        )
    }

    fun getUpdateOrderNoListData(context: Context, empCode: String, ischeck: String) {

        compositeDisposable.add(
                dataManager
                        .updateLeaveApprovalStatus(ischeck, empCode)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->

                            val sharedPref: SharedPreferences = context.getSharedPreferences(empCode, PRIVATE_MODE)
                            val editor = sharedPref.edit()
                            editor.putString(empCode, ischeck)
                            editor.apply()

                            getAllLeaveApprovalData()

                        }, {
                            //navigator?.handleError(throwable)
                        })
        )
    }

    fun getAllLeaveApprovals(context:Context,ischeck: String) {
        compositeDisposable.add(
                dataManager
                        .loadAllLeaveApproval()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            response.forEach {
                                getUpdateOrderNoListData(context, it.empCode, ischeck)
                            }
                        }, {
                            //navigator?.handleError(throwable)
                        })
        )
    }


    fun getleaveApprovalListLiveData(): MutableLiveData<List<LeaveApprovalListCardData>> {
        return leaveApprovalListLiveData
    }

    fun addLeaveApprovalListItemToList(Service: List<LeaveApprovalListCardData>) {
        leaveApprovalListObserverArrayList.clear()
        leaveApprovalListObserverArrayList.addAll(Service)
    }
}