package com.bd.mascogroup.automation.ui.home

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.ui.login.LoginActivity
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IHomeNavigator>(dataManager, ISchedulerProvider) {

    fun sideMenuDisplayInfo(context: Context, nameTv:TextView, empCodeTv:TextView, unitTv:TextView, phoneTv:TextView,photo:ImageView, activity_home_side_menu_logout_layout:View){
        nameTv.setText(dataManager.customerName)
        empCodeTv.setText(dataManager.empCode)
        unitTv.setText(dataManager.unitName)
        phoneTv.setText(dataManager.mobile)

        Glide.with(photo)
                .load(dataManager.customerPic)
                .into(photo)

        activity_home_side_menu_logout_layout.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Do You Want to Logout from App?")
            //  builder.setMessage("Are you want to set the app background color to RED?")
            builder.setPositiveButton("YES"){dialog, which ->
                dataManager.empCode = ""
                navigator?.openLoginScreen()
            }
            builder.setNegativeButton("No"){dialog,which ->
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}