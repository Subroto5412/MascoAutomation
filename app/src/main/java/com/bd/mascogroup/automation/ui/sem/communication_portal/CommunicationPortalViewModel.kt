package com.bd.mascogroup.automation.ui.sem.communication_portal

import android.R
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.ListEmployeeNameCardData
import com.bd.mascogroup.automation.data.model.domainModel.UnitCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.SearchEmpNameRequest
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap
import kotlin.collections.forEach


class CommunicationPortalViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ICommunicationPortalNavigator>(dataManager, ISchedulerProvider) {
    private var unitCardData = ArrayList<UnitCardData>()
    private var unitName = ArrayList<String>()

    private var SearchEmpNames = ArrayList<String>()
    private var SearchEmpNamesList = ArrayList<ListEmployeeNameCardData>()


    fun getUnitName(
            context: Context,
            unitNameSp: Spinner
    ) {
        unitCardData.clear()
        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getUnitName()

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ unitResponse ->

                        unitResponse._listUnitName.forEach {
                            unitCardData.add(UnitCardData(it))
                        }
                        unitName.clear()
                        for (i in 0 until unitCardData.size) {
                            val unit = HashMap<String, String>()
                            unit.put("unitNo", unitCardData.get(i).unitNo.toString())
                            unit.put("unitEName", "" + unitCardData.get(i).unitEName)
                            AppConstants.HasUnitNameList.add(unit)
                            unitName.add(unitCardData.get(i).unitEName)
                        }
                        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                                context,
                                R.layout.simple_spinner_item,
                                unitName
                        )
                        spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item) // The drop down view
                        unitNameSp.setAdapter(spinnerArrayAdapter)

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

    fun getSearchName(
            context: Context,
            search_name_actv: AutoCompleteTextView,
            unitNo: Int
    ) {
        SearchEmpNamesList.clear()
        SearchEmpNames.clear()

        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getEmpName(SearchEmpNameRequest(search_name_actv.text.toString(), 0))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ unitResponse ->

                        unitResponse._listEmployee.forEach {
                            SearchEmpNamesList.add(ListEmployeeNameCardData(it))
                        }

                        for (i in 0 until SearchEmpNamesList.size) {
                            val activityNames = HashMap<String, String>()
                            activityNames.put("emP_CODE", SearchEmpNamesList.get(i).emP_CODE)
                            activityNames.put("emp_full", "" + SearchEmpNamesList.get(i).emp_full)

                            SearchEmpNames.add(SearchEmpNamesList.get(i).emp_full)
                        }

                        val adapter = ArrayAdapter(context,
                                android.R.layout.simple_list_item_1, SearchEmpNames)
                        search_name_actv.setAdapter(adapter)

                        /* search_name_actv.setOnDismissListener {
                            Log.e("------","----------"+SearchEmpNames.get())
                        }*/
                        search_name_actv.setOnItemSelectedListener(object : OnItemSelectedListener {
                            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
//                                val selectedCustomer: Customer = view.getTag() as Customer
//                                startSearch(selectedCustomer)
                                Log.e("------","---emP_CODE-------"+SearchEmpNamesList.get(i).emP_CODE)
                                Log.e("------","-----emp_full-----"+SearchEmpNamesList.get(i).emp_full)
                            }

                            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
                        })

                        UtilMethods.hideLoading()
                    }, { error ->
                        UtilMethods.hideLoading()
                        //  UtilMethods.showLongToast(context, error.message.toString())
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }

        /*compositeDisposable.add(
                dataManager
                        .getEmpName()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            response.forEach {
                                activityNamesList.add(listEmployee(it))
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

                        }, {
                            //navigator?.handleError(throwable)
                        })
        )*/
    }
}