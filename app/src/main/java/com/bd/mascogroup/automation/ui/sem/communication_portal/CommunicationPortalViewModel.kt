package com.bd.mascogroup.automation.ui.sem.communication_portal

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.db.Empname
import com.bd.mascogroup.automation.data.model.db.Unitlist
import com.bd.mascogroup.automation.data.model.domainModel.ListEmployeeNameCardData
import com.bd.mascogroup.automation.data.model.domainModel.UnitlistCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.SearchEmpDetailRequest
import com.bd.mascogroup.automation.data.remote.domainModel.SearchEmpNameRequest
import com.bd.mascogroup.automation.data.remote.domainModel.listUnitName
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
import java.util.*
import javax.inject.Inject


class CommunicationPortalViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
) : BaseViewModel<ICommunicationPortalNavigator>(dataManager, ISchedulerProvider) {
    private var unitNameList = ArrayList<UnitlistCardData>()
    private var unitName = ArrayList<String>()

    private var SearchEmpNames = ArrayList<String>()
    private var SearchEmpNamesList = ArrayList<ListEmployeeNameCardData>()

    var EmpNames = ArrayList<String>()
    var EmpId = ArrayList<Int>()

    fun getUnitName(context: Context, unit_actv: AutoCompleteTextView, search_name_actv: AutoCompleteTextView, acp_name_value_tv: TextView, acp_designation_value_tv: TextView, acp_section_value_tv: TextView,
                    acp_email_value_tv: TextView, acp_mobile_personal_value_tv: TextView, acp_mobile_office_value_tv: TextView, acp_office_ip_value_tv: TextView, acp_unit_value_tv: TextView, acp_pic_im: ImageView) {
        deleteAllUnit()
        deleteAllEmpName()
        unitNameList.clear()
        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getAllUnitName()

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ unitResponse ->

                        unitResponse._listUnitName.forEach {
                            var listUnitName = Unitlist()
                            listUnitName.unitName = it.unitEName
                            listUnitName.unitNo = it.unitNo
                            unitInsert(listUnitName)
                        }

                        UtilMethods.hideLoading()

                        getSearchUnit(context, unit_actv, search_name_actv)

                        saveEmpName(context, search_name_actv, unit_actv, acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv,
                                acp_email_value_tv, acp_mobile_personal_value_tv, acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)

                    }, { error ->
                        UtilMethods.hideLoading()
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }


    fun getOnlineUnitName(context: Context, unit_actv: AutoCompleteTextView, search_name_actv: AutoCompleteTextView, acp_name_value_tv: TextView, acp_designation_value_tv: TextView, acp_section_value_tv: TextView,
                          acp_email_value_tv: TextView, acp_mobile_personal_value_tv: TextView, acp_mobile_office_value_tv: TextView, acp_office_ip_value_tv: TextView, acp_unit_value_tv: TextView, acp_pic_im: ImageView) {

        unitNameList.clear()
        unitName.clear()
        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getAllUnitName()

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ unitResponse ->

                        unitResponse._listUnitName.forEach {
                            unitNameList.add(UnitlistCardData(it))
                        }

                        for (i in 0 until unitNameList.size) {
                            unitName.add(unitNameList.get(i).unitEName)
                        }

                        val adapter = ArrayAdapter(context,
                                android.R.layout.simple_list_item_1, unitName)
                        unit_actv.setAdapter(adapter)

                        UtilMethods.hideLoading()

                        getEmpName(context, search_name_actv, unit_actv, acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv,
                                acp_email_value_tv, acp_mobile_personal_value_tv, acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)

                    }, { error ->
                        UtilMethods.hideLoading()
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun getEmpName(
            context: Context, search_name_actv: AutoCompleteTextView, unit_actv: AutoCompleteTextView, acp_name_value_tv: TextView, acp_designation_value_tv: TextView, acp_section_value_tv: TextView,
            acp_email_value_tv: TextView, acp_mobile_personal_value_tv: TextView, acp_mobile_office_value_tv: TextView, acp_office_ip_value_tv: TextView, acp_unit_value_tv: TextView, acp_pic_im: ImageView) {
        SearchEmpNamesList.clear()
        SearchEmpNames.clear()

        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getEmpName(SearchEmpNameRequest(search_name_actv.text.toString(), AppConstants.unit))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->

                        response._listEmployee.forEach {
                            SearchEmpNamesList.add(ListEmployeeNameCardData(it))
                        }

                        for (i in 0 until SearchEmpNamesList.size) {
                            SearchEmpNames.add(SearchEmpNamesList.get(i).emp_full)
                        }

                        val adapter = ArrayAdapter(context,
                                android.R.layout.simple_list_item_1, SearchEmpNames)
                        search_name_actv.setAdapter(adapter)

                        UtilMethods.hideLoading()

                        getEmpDetails(context, "0", acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv,
                                acp_email_value_tv, acp_mobile_personal_value_tv, acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)


                    }, { error ->
                        UtilMethods.hideLoading()
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun saveEmpName(context: Context, search_name_actv: AutoCompleteTextView, unit_actv: AutoCompleteTextView, acp_name_value_tv: TextView, acp_designation_value_tv: TextView, acp_section_value_tv: TextView,
                    acp_email_value_tv: TextView, acp_mobile_personal_value_tv: TextView, acp_mobile_office_value_tv: TextView, acp_office_ip_value_tv: TextView, acp_unit_value_tv: TextView, acp_pic_im: ImageView) {
        SearchEmpNamesList.clear()
        SearchEmpNames.clear()

        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getEmpName(SearchEmpNameRequest("", 0))

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
                        getEmpName(context, search_name_actv, unit_actv, acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv,
                                acp_email_value_tv, acp_mobile_personal_value_tv, acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)

                    }, { error ->
                        UtilMethods.hideLoading()
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun empNameInsert(empname: Empname) {
        compositeDisposable.add(
                dataManager
                        .insertEmpNameItem(empname)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                        }, {}))

    }

    fun deleteAllEmpName() {
        compositeDisposable.add(
                dataManager
                        .deleteAllEmpNameLists()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                        }, {}))
    }


    fun getSearchCodeByName(context: Context, search_name: String, acp_name_value_tv: TextView, acp_designation_value_tv: TextView, acp_section_value_tv: TextView, acp_email_value_tv: TextView,
                            acp_mobile_personal_value_tv: TextView, acp_mobile_office_value_tv: TextView, acp_office_ip_value_tv: TextView, acp_unit_value_tv: TextView, acp_pic_im: ImageView) {

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
                        })
        )
    }

    fun getEmpDetails(
            context: Context,
            empCode: String, acp_name_value_tv: TextView, acp_designation_value_tv: TextView, acp_section_value_tv: TextView,
            acp_email_value_tv: TextView, acp_mobile_personal_value_tv: TextView, acp_mobile_office_value_tv: TextView, acp_office_ip_value_tv: TextView, acp_unit_value_tv: TextView, acp_pic_im: ImageView
    ) {

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

                        val photoCornerRadius = 100
                        Glide.with(context)
                                .load(url)
                                .transform(MultiTransformation(CenterCrop(), RoundedCorners(photoCornerRadius)))
                                .placeholder(com.bd.mascogroup.automation.R.drawable.user_picture)
                                .error(com.bd.mascogroup.automation.R.drawable.user_picture)
                                .into(acp_pic_im)

                        UtilMethods.hideLoading()

                    }, { error ->
                        UtilMethods.hideLoading()
                        acp_name_value_tv.setText("")
                        acp_designation_value_tv.setText("")
                        acp_section_value_tv.setText("")
                        acp_email_value_tv.setText("")
                        acp_mobile_personal_value_tv.setText("")
                        acp_mobile_office_value_tv.setText("")
                        acp_office_ip_value_tv.setText("")
                        acp_unit_value_tv.setText("")

                        val photoCornerRadius = 100
                        Glide.with(context)
                                .load("")
                                .transform(MultiTransformation(CenterCrop(), RoundedCorners(photoCornerRadius)))
                                .placeholder(com.bd.mascogroup.automation.R.drawable.user_picture)
                                .error(com.bd.mascogroup.automation.R.drawable.user_picture)
                                .into(acp_pic_im)
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun unitInsert(unitlist: Unitlist) {
        compositeDisposable.add(
                dataManager
                        .insertUnitItem(unitlist)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            Log.e("", "response : " + response)
                        }, {}))

    }

    fun deleteAllUnit() {
        compositeDisposable.add(
                dataManager
                        .deleteAllUnitLists()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            Log.e("", "response : " + response)
                        }, {}))
    }


    fun getSearchUnit(context: Context, unit_actv: AutoCompleteTextView, search_name_actv: AutoCompleteTextView) {

        SearchEmpNamesList.clear()
        SearchEmpNames.clear()

        compositeDisposable.add(
                dataManager
                        .getSearchByUnit(unit_actv.text.toString())
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            response.forEach {
                                var listUnitName = listUnitName()
                                listUnitName.unitNo = it.unitNo
                                listUnitName.unitEName = it.unitName
                                unitNameList.add(UnitlistCardData(listUnitName))
                            }

                            for (i in 0 until unitNameList.size) {
                                unitName.add(unitNameList.get(i).unitEName)
                            }

                            val adapter = ArrayAdapter(context,
                                    android.R.layout.simple_list_item_1, unitName)
                            unit_actv.setAdapter(adapter)

                        }, {
                        })
        )
    }


    fun getUnitCodeByName(context: Context, unit_name: String, search_name_actv: AutoCompleteTextView, acp_unit_name_value_actv: AutoCompleteTextView, acp_name_value_tv: TextView, acp_designation_value_tv: TextView, acp_section_value_tv: TextView,
                          acp_email_value_tv: TextView, acp_mobile_personal_value_tv: TextView, acp_mobile_office_value_tv: TextView, acp_office_ip_value_tv: TextView, acp_unit_value_tv: TextView, acp_pic_im: ImageView) {

        SearchEmpNamesList.clear()
        SearchEmpNames.clear()
        compositeDisposable.add(
                dataManager
                        .getSearchUnitNoByUnitName(unit_name)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe({ response ->
                            response.forEach {
                                AppConstants.unit = it.unitNo
                                getEmpName(context, search_name_actv, acp_unit_name_value_actv, acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv,
                                        acp_email_value_tv, acp_mobile_personal_value_tv, acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)
                            }

                        }, {
                        })
        )
    }
}