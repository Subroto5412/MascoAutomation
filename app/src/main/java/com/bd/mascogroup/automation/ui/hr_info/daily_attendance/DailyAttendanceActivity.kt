package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceCardData
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceStatusCardData
import com.bd.mascogroup.automation.databinding.ActivityDailyAttendanceBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_hr_info.*
import javax.inject.Inject

class DailyAttendanceActivity : BaseActivity<ActivityDailyAttendanceBinding, DailyAttendanceViewModel>(), IDailyAttendanceNavigator,DailyAttendanceAdapter.DailyAttendanceAdapterListener,
    DailyAttendanceStatusAdapter.DailyAttendanceStatusAdapterListener {



    lateinit var mActivityDailyAttendanceBinding: ActivityDailyAttendanceBinding

    @Inject
    lateinit var mDailyAttendanceViewModel: DailyAttendanceViewModel

    @Inject
    lateinit var mDailyAttendanceAdapter: DailyAttendanceAdapter

    @Inject
    lateinit var mDailyAttendanceStatusAdapter: DailyAttendanceStatusAdapter



    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_daily_attendance

    override val viewModel: DailyAttendanceViewModel
        get() {
            return mDailyAttendanceViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityDailyAttendanceBinding = viewDataBinding
        viewModel.navigator = this
        mDailyAttendanceAdapter.setListener(this)
        mDailyAttendanceStatusAdapter.setListener(this)

        viewModel.dailyAttendance(this)
        setUp()
        subscribeToLiveDataDailyAttendance()

        setUpStatus()
        subscribeToLiveDataDailyAttendanceStatus()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DailyAttendanceActivity::class.java)
        }
    }

    fun setUp() {
        mActivityDailyAttendanceBinding.dailyAttendanceListParentRv.itemAnimator = DefaultItemAnimator()
        mActivityDailyAttendanceBinding.dailyAttendanceListParentRv.adapter = mDailyAttendanceAdapter
    }

    fun updateDailyAttendanceList(dailyAttendanceCardData: List<DailyAttendanceCardData>?) {
        mDailyAttendanceAdapter.clearItems()
        if (!dailyAttendanceCardData.isNullOrEmpty()) {
            mDailyAttendanceAdapter.addItem(dailyAttendanceCardData)
        }
    }

    fun subscribeToLiveDataDailyAttendance() {
        mDailyAttendanceViewModel.getdailyAttendanceLiveData().observe(this, Observer { t ->
            mDailyAttendanceViewModel.addDailyAttendanceItemToList(t)
            updateDailyAttendanceList(t)
        })
    }

    fun setUpStatus() {
        mActivityDailyAttendanceBinding.dailyAttendanceStatusListParentRv.itemAnimator = DefaultItemAnimator()
        mActivityDailyAttendanceBinding.dailyAttendanceStatusListParentRv.adapter = mDailyAttendanceStatusAdapter
    }

    fun updateDailyAttendanceStatusList(dailyAttendanceStatusCardData: List<DailyAttendanceStatusCardData>?) {
        mDailyAttendanceStatusAdapter.clearItems()
        if (!dailyAttendanceStatusCardData.isNullOrEmpty()) {
            mDailyAttendanceStatusAdapter.addItem(dailyAttendanceStatusCardData)
        }
    }

    fun subscribeToLiveDataDailyAttendanceStatus() {
        mDailyAttendanceViewModel.getdailyAttendanceStatusLiveData().observe(this, Observer { t ->
            mDailyAttendanceViewModel.addDailyAttendanceStatusItemToList(t)
            updateDailyAttendanceStatusList(t)
        })
    }

}