package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityDailyAttendanceBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_hr_info.*
import javax.inject.Inject

class DailyAttendanceActivity : BaseActivity<ActivityDailyAttendanceBinding, DailyAttendanceViewModel>(), IDailyAttendanceNavigator {


    @Inject
    override lateinit var viewModel: DailyAttendanceViewModel

    private var mActivityDailyAttendanceBinding: ActivityDailyAttendanceBinding? = null

    @Inject
    lateinit var mDailyAttendanceViewModel: DailyAttendanceViewModel



    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_daily_attendance



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityDailyAttendanceBinding = viewDataBinding
        viewModel.navigator = this

       /* activity_hr_daily_attendance_cl.setOnClickListener {
            val intent = ProductionManagementActivity.newIntent(this@DailyAttendanceActivity)
            startActivity(intent)
        }*/
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DailyAttendanceActivity::class.java)
        }
    }
}