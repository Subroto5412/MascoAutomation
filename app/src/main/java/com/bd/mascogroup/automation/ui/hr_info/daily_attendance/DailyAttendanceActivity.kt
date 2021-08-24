package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceCardData
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceStatusCardData
import com.bd.mascogroup.automation.databinding.ActivityDailyAttendanceBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_daily_attendance.*
import kotlinx.android.synthetic.main.activity_hr_info.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

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

    var monthList=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityDailyAttendanceBinding = viewDataBinding
        viewModel.navigator = this
        mDailyAttendanceAdapter.setListener(this)
        mDailyAttendanceStatusAdapter.setListener(this)

        monthList.add("January")
        monthList.add("February")
        monthList.add("March")
        monthList.add("April")
        monthList.add("May")
        monthList.add("June")
        monthList.add("July")
        monthList.add("August")
        monthList.add("September")
        monthList.add("October")
        monthList.add("November")
        monthList.add("December")

        val sdf = SimpleDateFormat("MM")
        val currentDate_ = sdf.format(Date())
        var currentDate:Int = 0
        currentDate = currentDate_.toInt()-1


        Log.e("--------------","-------------"+currentDate)

        var month = monthList.get(currentDate)
        var nextMonth = monthList.get(currentDate+1)
        var backMonth = monthList.get(currentDate-1)

        activity_daily_attendance_back_month_tv.text = backMonth.subSequence(0,3)
        activity_daily_attendance_month_tv.text = month
        activity_daily_attendance_next_month_tv.text = nextMonth.subSequence(0,3)

        viewModel.dailyAttendance(this)
        setUp()
        subscribeToLiveDataDailyAttendance()

        setUpStatus()
        subscribeToLiveDataDailyAttendanceStatus()
        var index:Int=0
        var totalMonth:Int = 0
        activity_daily_attendance_back_month_im.setOnClickListener {

            index--
            totalMonth = currentDate+index
            Log.e("---------","----currentDate----"+totalMonth)

            if (totalMonth>-2){

                if (totalMonth==-1){

                    Log.e("---------","----currentDate-hhh---::"+totalMonth)
                    var month = monthList.get(0)
                    var nextMonth = monthList.get(1)
//                    var backMonth = monthList.get(totalMonth-1)

                    activity_daily_attendance_back_month_tv.text = ""
                    activity_daily_attendance_month_tv.text = month
                    activity_daily_attendance_next_month_tv.text =nextMonth.subSequence(0,3)

                    activity_daily_attendance_back_month_im.isInvisible = true
                    activity_daily_attendance_next_month_im.isVisible = true
                }else{
                    Log.e("---------","----currentDate-bb---:"+totalMonth)

                    Log.e("----------","------totalMonthtotalMonth--------)"+totalMonth)
                    var nextMonth:String=""
                    var month:String=""
                    var backMonth:String = ""
                    if (totalMonth==10){
                         month = monthList.get(totalMonth)
                        nextMonth  = monthList.get(totalMonth+1)
                        backMonth = monthList.get(totalMonth-1)

                    }else{
                         month = monthList.get(totalMonth)
                         nextMonth = monthList.get(totalMonth+1)

                        if (totalMonth==0){
                            activity_daily_attendance_back_month_im.isInvisible = true
                            activity_daily_attendance_next_month_im.isVisible = true
                        }else{
                            backMonth = monthList.get(totalMonth-1)
                        }
                        activity_daily_attendance_next_month_im.isVisible = true
                    }

                   //  = monthList.get(totalMonth)
                    if (totalMonth==0)
                    activity_daily_attendance_back_month_tv.text = ""
                    else
                        activity_daily_attendance_back_month_tv.text = backMonth.subSequence(0,3)

                    activity_daily_attendance_month_tv.text = month
                    activity_daily_attendance_next_month_tv.text = nextMonth.subSequence(0,3)
                }

            }

        }


        activity_daily_attendance_next_month_im.setOnClickListener {
            index++
            totalMonth = currentDate+index
            Log.e("---------","----currentDate----"+totalMonth)

            if (totalMonth<12){

                if (totalMonth==11){

                    Log.e("---------","----currentDate-sss---::"+totalMonth)
                    var month = monthList.get(totalMonth)
//                    var nextMonth = monthList.get(totalMonth+1)
                    var backMonth = monthList.get(totalMonth-1)

                    activity_daily_attendance_back_month_tv.text = backMonth.subSequence(0,3)
                    activity_daily_attendance_month_tv.text = month
                    activity_daily_attendance_next_month_tv.text =""

                    totalMonth = totalMonth-1
                    activity_daily_attendance_next_month_im.isInvisible = true
                    Log.e("---------","----currentDate-sss-- totalMonth-::"+totalMonth)
                }else{
                    Log.e("---------","----currentDate-tttt---::"+totalMonth)
                    var month = monthList.get(totalMonth)
                    var nextMonth = monthList.get(totalMonth+1)
                    var backMonth = monthList.get(totalMonth-1)

                    activity_daily_attendance_back_month_tv.text = backMonth.subSequence(0,3)
                    activity_daily_attendance_month_tv.text = month
                    activity_daily_attendance_next_month_tv.text = nextMonth.subSequence(0,3)
                    activity_daily_attendance_back_month_im.isVisible = true
                   /* if (totalMonth==12){
                        totalMonth = totalMonth-1
                        activity_daily_attendance_next_month_im.isInvisible = true
                    }*/

                }

            }

            /*if (totalMonth<13){
                activity_daily_attendance_next_month_im.isInvisible = true
            }*/

        }
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