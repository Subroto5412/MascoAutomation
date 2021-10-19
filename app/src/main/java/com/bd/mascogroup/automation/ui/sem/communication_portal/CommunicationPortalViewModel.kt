package com.bd.mascogroup.automation.ui.sem.communication_portal

import android.R
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.*
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.db.Empname
import com.bd.mascogroup.automation.data.model.domainModel.ListEmployeeNameCardData
import com.bd.mascogroup.automation.data.model.domainModel.UnitCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.SearchEmpDetailRequest
import com.bd.mascogroup.automation.data.remote.domainModel.SearchEmpNameRequest
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
import kotlinx.android.synthetic.main.activity_communication_portal.*
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

    var EmpNames = ArrayList<String>()
    var EmpId = ArrayList<Int>()


    fun getUnitName(
            context: Context, unitNameSp: Spinner, search_name_actv: AutoCompleteTextView, acp_name_value_tv:TextView, acp_designation_value_tv:TextView, acp_section_value_tv:TextView,
            acp_email_value_tv:TextView, acp_mobile_personal_value_tv:TextView, acp_mobile_office_value_tv:TextView, acp_office_ip_value_tv:TextView, acp_unit_value_tv:TextView, acp_pic_im:ImageView)
    {
        unitCardData.clear()
        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getAllUnitName()

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ unitResponse ->

                        unitResponse._listUnitName.forEach {
                            unitCardData.add(UnitCardData(it))
                        }
                        unitName.clear()
                        val unit = HashMap<String, String>()

                        unit.put("unitNo", "0")
                        unit.put("unitEName", "Select Unit")
                        AppConstants.HasUnitNameList.add(unit)
                        unitName.add("Select Unit")

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
                        insertEmpName(context, search_name_actv, 0,acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv, acp_email_value_tv, acp_mobile_personal_value_tv,
                                acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)
                    }, { error ->
                        UtilMethods.hideLoading()
                        //  UtilMethods.showLongToast(context, error.message.toString())
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun insertEmpName(
            context: Context, search_name_actv: AutoCompleteTextView, unitNo: Int, acp_name_value_tv:TextView, acp_designation_value_tv:TextView, acp_section_value_tv:TextView,
            acp_email_value_tv:TextView, acp_mobile_personal_value_tv:TextView, acp_mobile_office_value_tv:TextView, acp_office_ip_value_tv:TextView, acp_unit_value_tv:TextView,
            acp_pic_im:ImageView) {
        SearchEmpNamesList.clear()
        SearchEmpNames.clear()

        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getEmpName(SearchEmpNameRequest("", unitNo))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->

                        response._listEmployee.forEach {

                            var empname = Empname()
                           empname.emp_full = it.emp_full
                            empname.emP_CODE = it.emP_CODE
                            empname.unitNo = it.unitNo
                            empNameInsert(empname)
                        }
                        UtilMethods.hideLoading()

                        getSearchAllName(context, search_name_actv,acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv, acp_email_value_tv, acp_mobile_personal_value_tv,
                                acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)

                    }, { error ->
                        UtilMethods.hideLoading()
                        //  UtilMethods.showLongToast(context, error.message.toString())
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun empNameInsert(empname: Empname){
        compositeDisposable.add(
                dataManager
                        .insertEmpNameItem(empname)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            Log.e("", "response : " + response)
                        }, {}))

    }

    fun getSearchName(
            context: Context, search_name_actv:AutoCompleteTextView, Code:String, acp_name_value_tv:TextView, acp_designation_value_tv:TextView, acp_section_value_tv:TextView,
            acp_email_value_tv:TextView, acp_mobile_personal_value_tv:TextView, acp_mobile_office_value_tv:TextView, acp_office_ip_value_tv:TextView, acp_unit_value_tv:TextView, acp_pic_im:ImageView) {
        var HasEmpNameList = ArrayList<HashMap<String, String>>()

        SearchEmpNamesList.clear()
        SearchEmpNames.clear()
        compositeDisposable.add(
                dataManager
                        .getSearchByEmpName(search_name_actv.text.toString(),Code)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            response.forEach {
                                SearchEmpNamesList.add(ListEmployeeNameCardData(it))
                            }
                            HasEmpNameList.clear()
                            for (i in 0 until SearchEmpNamesList.size) {
                                val empNames = HashMap<String, String>()
                                empNames.put("emP_CODE", SearchEmpNamesList.get(i).emP_CODE)
                                empNames.put("emp_full", "" + SearchEmpNamesList.get(i).emp_full)
                                empNames.put("unitNo", "" + SearchEmpNamesList.get(i).unitNo)

                                HasEmpNameList.add(empNames)
                                SearchEmpNames.add(SearchEmpNamesList.get(i).emp_full)
                            }

                            val adapter = ArrayAdapter(context,
                                    android.R.layout.simple_list_item_1, SearchEmpNames)
                            search_name_actv.setAdapter(adapter)


                            search_name_actv.onItemClickListener = AdapterView.OnItemClickListener{
                                parent,view,position,id->
                                val selectedItem = parent.getItemAtPosition(position).toString()

                                getSearchCodeByName(context, selectedItem,acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv, acp_email_value_tv,
                                        acp_mobile_personal_value_tv, acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)
                            }

                        }, {
                            //navigator?.handleError(throwable)
                        })
        )
    }

    fun getSearchAllName(context: Context, search_name_actv:AutoCompleteTextView, acp_name_value_tv:TextView, acp_designation_value_tv:TextView, acp_section_value_tv:TextView,
            acp_email_value_tv:TextView, acp_mobile_personal_value_tv:TextView, acp_mobile_office_value_tv:TextView, acp_office_ip_value_tv:TextView, acp_unit_value_tv:TextView, acp_pic_im:ImageView) {

        SearchEmpNamesList.clear()
        SearchEmpNames.clear()
        compositeDisposable.add(
                dataManager
                        .getSearchByAllEmpName(search_name_actv.text.toString())
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            response.forEach {
                                SearchEmpNamesList.add(ListEmployeeNameCardData(it))
                            }

                            for (i in 0 until SearchEmpNamesList.size) {
                                val activityNames = HashMap<String, String>()
                                activityNames.put("emP_CODE", SearchEmpNamesList.get(i).emP_CODE)
                                activityNames.put("emp_full", "" + SearchEmpNamesList.get(i).emp_full)
                                activityNames.put("unitNo", "" + SearchEmpNamesList.get(i).unitNo)

                                SearchEmpNames.add(SearchEmpNamesList.get(i).emp_full)
                            }

                            val adapter = ArrayAdapter(context,
                                    android.R.layout.simple_list_item_1, SearchEmpNames)
                            search_name_actv.setAdapter(adapter)

                            search_name_actv.onItemClickListener = AdapterView.OnItemClickListener{
                                parent,view,position,id->
                                val selectedItem = parent.getItemAtPosition(position).toString()

                                getSearchCodeByName(context, selectedItem,acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv,
                                        acp_email_value_tv, acp_mobile_personal_value_tv, acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)
                            }

                        }, {
                            //navigator?.handleError(throwable)
                        })
        )
    }

    fun deleteAllEmpName(){
        compositeDisposable.add(
                dataManager
                        .deleteAllEmpNameLists()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            Log.e("", "response : " + response)
                        }, {}))
    }


    fun getSearchCodeByName(
            context: Context,
            search_name:String,
            acp_name_value_tv:TextView, acp_designation_value_tv:TextView, acp_section_value_tv:TextView,
            acp_email_value_tv:TextView, acp_mobile_personal_value_tv:TextView, acp_mobile_office_value_tv:TextView, acp_office_ip_value_tv:TextView, acp_unit_value_tv:TextView, acp_pic_im:ImageView
    ) {
        SearchEmpNamesList.clear()
        SearchEmpNames.clear()
        compositeDisposable.add(
                dataManager
                        .getSearchCodeByEmpName(search_name)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            response.forEach {

                                getEmpDetails(context, it.emP_CODE, acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv,
                                        acp_email_value_tv, acp_mobile_personal_value_tv, acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)
                            }

                        }, {
                            //navigator?.handleError(throwable)
                        })
        )
    }

    fun getEmpDetails(
            context: Context,
            empCode:String,acp_name_value_tv:TextView, acp_designation_value_tv:TextView, acp_section_value_tv:TextView,
            acp_email_value_tv:TextView, acp_mobile_personal_value_tv:TextView, acp_mobile_office_value_tv:TextView, acp_office_ip_value_tv:TextView, acp_unit_value_tv:TextView, acp_pic_im:ImageView
    ) {
        unitCardData.clear()
        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getEmpDetails(SearchEmpDetailRequest(empCode))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ it ->
                            acp_name_value_tv.setText(it.empDetails.emP_ENAME)
                            acp_designation_value_tv.setText(it.empDetails.deptEName)
                            acp_section_value_tv.setText(it.empDetails.sectEName)
                            acp_email_value_tv.setText(it.empDetails.email)
                            acp_mobile_personal_value_tv.setText(it.empDetails.personal_mobile)
                            acp_mobile_office_value_tv.setText(it.empDetails.office_mobile)
                            acp_office_ip_value_tv.setText(it.empDetails.ip)
                            acp_unit_value_tv.setText(it.empDetails.unitEName)

                        val parts = it.empDetails.img_url.split("\\")
                        val image = parts[1]

                        val url = "https://mis-api.mascoknit.com/EmpImages/" + image
                        dataManager.customerPic = url

                        val photoCornerRadius = 100
                        Glide.with(context)
                                .load(url)
                                .transform(MultiTransformation(CenterCrop(), RoundedCorners(photoCornerRadius)))
                                .placeholder(com.bd.mascogroup.automation.R.drawable.user)
                                .error(com.bd.mascogroup.automation.R.drawable.user)
                                .into(acp_pic_im)

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
}