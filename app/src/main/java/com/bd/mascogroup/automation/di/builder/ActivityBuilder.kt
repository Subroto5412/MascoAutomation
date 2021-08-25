package com.bd.mascogroup.automation.di.builder

import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.home.HomeActivityModule
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivityModule
import com.bd.mascogroup.automation.ui.hr_info.daily_attendance.DailyAttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.daily_attendance.DailyAttendanceActivityModule
import com.bd.mascogroup.automation.ui.hr_info.daily_attendance.DailyAttendanceAdapterModule
import com.bd.mascogroup.automation.ui.hr_info.leave_details.LeaveDetailsActivity
import com.bd.mascogroup.automation.ui.hr_info.leave_details.LeaveDetailsActivityModule
import com.bd.mascogroup.automation.ui.hr_info.leave_details.LeaveDetailsLeaveSummaryAdapterModule
import com.bd.mascogroup.automation.ui.login.LoginActivity
import com.bd.mascogroup.automation.ui.login.LoginActivityModule
import com.bd.mascogroup.automation.ui.otp.OTPActivity
import com.bd.mascogroup.automation.ui.otp.OTPActivityModule
import com.bd.mascogroup.automation.ui.production_management.ProductionManagementActivity
import com.bd.mascogroup.automation.ui.production_management.ProductionManagementActivityModule
import com.bd.mascogroup.automation.ui.signup.SignupActivity
import com.bd.mascogroup.automation.ui.signup.SignupActivityModule
import com.bd.mascogroup.automation.ui.splash.SplashActivity
import com.bd.mascogroup.automation.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
        includes =
        [SplashActivityModule::class, LoginActivityModule::class, SignupActivityModule::class, OTPActivityModule::class, HomeActivityModule::class,ProductionManagementActivityModule::class,
            HRInfoActivityModule::class, DailyAttendanceActivityModule::class, LeaveDetailsActivityModule::class]
)

abstract class ActivityBuilder {

    @ContributesAndroidInjector(
            modules = [SplashActivityModule::class]
    )
    internal abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(
            modules = [LoginActivityModule::class]
    )
    internal abstract fun bindLoginActivity(): LoginActivity

 @ContributesAndroidInjector(
            modules = [SignupActivityModule::class]
    )
    internal abstract fun bindSignupActivity(): SignupActivity

  @ContributesAndroidInjector(
      modules = [OTPActivityModule::class]
  )
  internal abstract fun bindOTPActivity(): OTPActivity

  @ContributesAndroidInjector(
     modules = [HomeActivityModule::class]
 )
 internal abstract fun bindHomeActivity(): HomeActivity

  @ContributesAndroidInjector(
          modules = [ProductionManagementActivityModule::class]
  )
  internal abstract fun bindProductionManagementActivity(): ProductionManagementActivity

    @ContributesAndroidInjector(
            modules = [HRInfoActivityModule::class]
    )
    internal abstract fun bindHRInfoActivity(): HRInfoActivity

    @ContributesAndroidInjector(
            modules = [DailyAttendanceActivityModule::class, DailyAttendanceAdapterModule::class]
    )
    internal abstract fun bindDailyAttendanceActivity(): DailyAttendanceActivity


    @ContributesAndroidInjector(
            modules = [LeaveDetailsActivityModule::class, LeaveDetailsLeaveSummaryAdapterModule::class]
    )
    internal abstract fun bindLeaveDetailsActivity(): LeaveDetailsActivity
}
