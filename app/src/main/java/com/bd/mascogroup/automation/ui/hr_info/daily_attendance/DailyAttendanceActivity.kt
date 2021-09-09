package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceCardData
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceStatusCardData
import com.bd.mascogroup.automation.databinding.ActivityDailyAttendanceBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.activity_daily_attendance.*
import kotlinx.android.synthetic.main.activity_hr_info.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
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
    var fYEarSpId:Int = 0
    var fYEarSpName:String=""

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

        var month = monthList.get(currentDate)
        var nextMonth = monthList.get(currentDate + 1)
        var backMonth = monthList.get(currentDate - 1)

        activity_daily_attendance_back_month_tv.text = backMonth.subSequence(0, 3)
        activity_daily_attendance_month_tv.text = month
        activity_daily_attendance_next_month_tv.text = nextMonth.subSequence(0, 3)

        viewModel.dailyAttendance(this)
        setUp()
        subscribeToLiveDataDailyAttendance()

        setUpStatus()
        subscribeToLiveDataDailyAttendanceStatus()

        viewModel.getFinancialYear(this, financial_year_spinner)

        val CurrenePosition = (mActivityDailyAttendanceBinding.dailyAttendanceStatusListParentRv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        activity_daily_attendance_next_status_im.setBackgroundResource(R.drawable.plan_next_show)
        activity_daily_attendance_back_status_im.setBackgroundResource(R.drawable.plan_back_show)
        activity_daily_attendance_bottom_next_cl.setOnClickListener {
                daily_attendance_status_list_parent_rv.scrollToPosition(CurrenePosition + 5)
            activity_daily_attendance_next_status_im.setBackgroundResource(R.drawable.plan_next)
            activity_daily_attendance_back_status_im.setBackgroundResource(R.drawable.plan_back_show)
        }

        activity_daily_attendance_bottom_back_cl.setOnClickListener {
            daily_attendance_status_list_parent_rv.scrollToPosition(CurrenePosition + 1)
            activity_daily_attendance_back_status_im.setBackgroundResource(R.drawable.plan_back)
            activity_daily_attendance_next_status_im.setBackgroundResource(R.drawable.plan_next_show)
        }

        var index:Int=0
        var totalMonth:Int = 0
        activity_daily_attendance_back_month_im.setOnClickListener {

            index--
            totalMonth = currentDate+index

            if (totalMonth>-2){

                if (totalMonth==-1){

                    var month = monthList.get(0)
                    var nextMonth = monthList.get(1)
//                    var backMonth = monthList.get(totalMonth-1)

                    activity_daily_attendance_back_month_tv.text = ""
                    activity_daily_attendance_month_tv.text = month
                    activity_daily_attendance_next_month_tv.text =nextMonth.subSequence(0, 3)

                    activity_daily_attendance_back_month_im.isInvisible = true
                    activity_daily_attendance_next_month_im.isVisible = true
                }else{
                    var nextMonth:String=""
                    var month:String=""
                    var backMonth:String = ""
                    if (totalMonth==10){
                         month = monthList.get(totalMonth)
                        nextMonth  = monthList.get(totalMonth + 1)
                        backMonth = monthList.get(totalMonth - 1)

                    }else{
                         month = monthList.get(totalMonth)
                         nextMonth = monthList.get(totalMonth + 1)

                        if (totalMonth==0){
                            activity_daily_attendance_back_month_im.isInvisible = true
                            activity_daily_attendance_next_month_im.isVisible = true
                        }else{
                            backMonth = monthList.get(totalMonth - 1)
                        }
                        activity_daily_attendance_next_month_im.isVisible = true
                    }

                    if (totalMonth==0)
                    activity_daily_attendance_back_month_tv.text = ""
                    else
                        activity_daily_attendance_back_month_tv.text = backMonth.subSequence(0, 3)

                    activity_daily_attendance_month_tv.text = month
                    activity_daily_attendance_next_month_tv.text = nextMonth.subSequence(0, 3)
                }
            }
        }

        activity_daily_attendance_next_month_im.setOnClickListener {
            index++
            totalMonth = currentDate+index

            if (totalMonth<12){

                if (totalMonth==11){

                    var month = monthList.get(totalMonth)
//                    var nextMonth = monthList.get(totalMonth+1)
                    var backMonth = monthList.get(totalMonth - 1)

                    activity_daily_attendance_back_month_tv.text = backMonth.subSequence(0, 3)
                    activity_daily_attendance_month_tv.text = month
                    activity_daily_attendance_next_month_tv.text =""

                    totalMonth = totalMonth-1
                    activity_daily_attendance_next_month_im.isInvisible = true
                }else{
                    var month = monthList.get(totalMonth)
                    var nextMonth = monthList.get(totalMonth + 1)
                    var backMonth = monthList.get(totalMonth - 1)

                    activity_daily_attendance_back_month_tv.text = backMonth.subSequence(0, 3)
                    activity_daily_attendance_month_tv.text = month
                    activity_daily_attendance_next_month_tv.text = nextMonth.subSequence(0, 3)
                    activity_daily_attendance_back_month_im.isVisible = true

                }
            }
        }

        layout_header_back_im.setOnClickListener {
            val intent = HRInfoActivity.newIntent(this@DailyAttendanceActivity)
            startActivity(intent)
            finish()
        }

        layout_footer_home_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this)
            startActivity(intent)
            finish()
        }


        financial_year_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {

                (parent!!.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                (parent!!.getChildAt(0) as TextView).setTextSize(13F)
                Log.e("-------", "------" + parent?.getItemAtPosition(position))
                val map: HashMap<String, String> = AppConstants.HasYearList.get(position)
               // fYEarSpId = map.get("id")!!.toInt()
                fYEarSpName = map.get("yearName")!!
                Log.e("-------", "---fYEarSpName---" + fYEarSpName)
            }
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
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = HRInfoActivity.newIntent(this)
            startActivity(intent)
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
}