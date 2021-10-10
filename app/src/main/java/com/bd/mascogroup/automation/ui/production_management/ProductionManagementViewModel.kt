package com.bd.mascogroup.automation.ui.production_management

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.SearchListCardData
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import java.util.ArrayList
import javax.inject.Inject

class ProductionManagementViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IProductionManagementNavigator>(dataManager, ISchedulerProvider) {

        private var activitySearchNames = ArrayList<String>()
        private var activityNamesList = ArrayList<SearchListCardData>()

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
                                                R.layout.simple_list_item_1, activitySearchNames)
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

}