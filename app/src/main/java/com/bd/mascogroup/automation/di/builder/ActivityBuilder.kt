package com.bd.mascogroup.automation.di.builder

import com.bd.mascogroup.automation.ui.atm.ATMActivity
import com.bd.mascogroup.automation.ui.atm.ATMActivityModule
import com.bd.mascogroup.automation.ui.atm.asset.AssetActivity
import com.bd.mascogroup.automation.ui.atm.asset.AssetActivityModule
import com.bd.mascogroup.automation.ui.sem.communication_portal.CommunicationPortalActivity
import com.bd.mascogroup.automation.ui.sem.communication_portal.CommunicationPortalActivityModule
import com.bd.mascogroup.automation.ui.gpms.GPMSActivity
import com.bd.mascogroup.automation.ui.gpms.GPMSActivityModule
import com.bd.mascogroup.automation.ui.gpms.bwpd.BWPDActivity
import com.bd.mascogroup.automation.ui.gpms.bwpd.BWPDActivityModule
import com.bd.mascogroup.automation.ui.gpms.bwpd.BWPDAdapterModule
import com.bd.mascogroup.automation.ui.gpms.hp_details.HPDetailsActivity
import com.bd.mascogroup.automation.ui.gpms.hp_details.HPDetailsActivityModule
import com.bd.mascogroup.automation.ui.gpms.hp_details.HPDetailsAdapterModule
import com.bd.mascogroup.automation.ui.gpms.hpd.HPDActivity
import com.bd.mascogroup.automation.ui.gpms.hpd.HPDActivityModule
import com.bd.mascogroup.automation.ui.gpms.hpd.HPDAdapterModule
import com.bd.mascogroup.automation.ui.gpms.lwp.LWPActivity
import com.bd.mascogroup.automation.ui.gpms.lwp.LWPActivityModule
import com.bd.mascogroup.automation.ui.gpms.lwp.LWPAdapterModule
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.home.HomeActivityModule
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivityModule
import com.bd.mascogroup.automation.ui.hr_info.attendance.AttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.attendance.AttendanceModule
import com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance.DailyAttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance.DailyAttendanceActivityModule
import com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance.DailyAttendanceAdapterModule
import com.bd.mascogroup.automation.ui.hr_info.leave.LeaveActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.LeaveModule
import com.bd.mascogroup.automation.ui.hr_info.leave.apply.LeaveApplyActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.apply.LeaveApplyModule
import com.bd.mascogroup.automation.ui.hr_info.tax.income_tax.IncomeTaxDeductionActivity
import com.bd.mascogroup.automation.ui.hr_info.tax.income_tax.IncomeTaxDeductionActivityModule
import com.bd.mascogroup.automation.ui.hr_info.tax.income_tax.IncomeTaxDeductionAdapterModule
import com.bd.mascogroup.automation.ui.hr_info.leave.leave_details.LeaveDetailsActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.leave_details.LeaveDetailsActivityModule
import com.bd.mascogroup.automation.ui.hr_info.leave.leave_details.LeaveDetailsLeaveSummaryAdapterModule
import com.bd.mascogroup.automation.ui.hr_info.tax.TaxActivity
import com.bd.mascogroup.automation.ui.hr_info.tax.TaxModule
import com.bd.mascogroup.automation.ui.hr_info_system.HrInfoSystemActivity
import com.bd.mascogroup.automation.ui.hr_info_system.HrInfoSystemActivityModule
import com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.LeaveApprovalActivity
import com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.LeaveApprovalActivityModule
import com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.leave_approval_form.*
import com.bd.mascogroup.automation.ui.login.LoginActivity
import com.bd.mascogroup.automation.ui.login.LoginActivityModule
import com.bd.mascogroup.automation.ui.otp.OTPActivity
import com.bd.mascogroup.automation.ui.otp.OTPActivityModule
import com.bd.mascogroup.automation.ui.production_management.ProductionManagementActivity
import com.bd.mascogroup.automation.ui.production_management.ProductionManagementActivityModule
import com.bd.mascogroup.automation.ui.sem.SEMActivity
import com.bd.mascogroup.automation.ui.sem.SEMActivityModule
import com.bd.mascogroup.automation.ui.signup.SignupActivity
import com.bd.mascogroup.automation.ui.signup.SignupActivityModule
import com.bd.mascogroup.automation.ui.splash.SplashActivity
import com.bd.mascogroup.automation.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
        includes =
        [SplashActivityModule::class, LoginActivityModule::class, SignupActivityModule::class, OTPActivityModule::class, HomeActivityModule::class,ProductionManagementActivityModule::class,
            HRInfoActivityModule::class, DailyAttendanceActivityModule::class, LeaveDetailsActivityModule::class, IncomeTaxDeductionActivityModule::class, AttendanceModule::class,
            LeaveModule::class, TaxModule::class, LeaveApplyModule::class, HrInfoSystemActivityModule::class, LeaveApprovalActivityModule::class, LeaveApprovalFormActivityModule::class,
        GPMSActivityModule::class, BWPDActivityModule::class, HPDActivityModule::class, LWPActivityModule::class, HPDetailsActivityModule::class, ATMActivityModule::class, SEMActivityModule::class,
        CommunicationPortalActivityModule::class, AssetActivityModule::class]
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


    @ContributesAndroidInjector(
            modules = [IncomeTaxDeductionActivityModule::class, IncomeTaxDeductionAdapterModule::class]
    )
    internal abstract fun bindIncomeTaxDeductionActivity(): IncomeTaxDeductionActivity

    @ContributesAndroidInjector(
            modules = [AttendanceModule::class]
    )
    internal abstract fun bindAttendanceActivity(): AttendanceActivity

    @ContributesAndroidInjector(
            modules = [LeaveModule::class]
    )
    internal abstract fun bindLeaveActivity(): LeaveActivity

    @ContributesAndroidInjector(
            modules = [TaxModule::class]
    )
    internal abstract fun bindTaxActivity(): TaxActivity


    @ContributesAndroidInjector(
        modules = [LeaveApplyModule::class]
    )
    internal abstract fun bindLeaveApplyActivity(): LeaveApplyActivity

    @ContributesAndroidInjector(
            modules = [HrInfoSystemActivityModule::class]
    )
    internal abstract fun bindHrInfoSystemActivity(): HrInfoSystemActivity

    @ContributesAndroidInjector(
            modules = [LeaveApprovalActivityModule::class]
    )
    internal abstract fun bindLeaveApprovalActivity(): LeaveApprovalActivity

    @ContributesAndroidInjector(
            modules = [LeaveApprovalFormActivityModule::class, LeaveApprovalFormAdapterModule::class]
    )
    internal abstract fun bindLeaveApprovalFormActivity(): LeaveApprovalFormActivity

    @ContributesAndroidInjector(
            modules = [GPMSActivityModule::class]
    )
    internal abstract fun bindGPMSActivity(): GPMSActivity

    @ContributesAndroidInjector(
            modules = [BWPDActivityModule::class, BWPDAdapterModule::class]
    )
    internal abstract fun bindBWPDActivity(): BWPDActivity

    @ContributesAndroidInjector(
            modules = [HPDActivityModule::class, HPDAdapterModule::class]
    )
    internal abstract fun bindHPDActivity(): HPDActivity

    @ContributesAndroidInjector(
            modules = [LWPActivityModule::class, LWPAdapterModule::class]
    )
    internal abstract fun bindLWPActivity(): LWPActivity

    @ContributesAndroidInjector(
            modules = [HPDetailsActivityModule::class, HPDetailsAdapterModule::class]
    )
    internal abstract fun bindHPDetailsActivity(): HPDetailsActivity

    @ContributesAndroidInjector(
            modules = [ATMActivityModule::class]
    )
    internal abstract fun bindATMActivity(): ATMActivity

    @ContributesAndroidInjector(
            modules = [SEMActivityModule::class]
    )
    internal abstract fun bindSEMActivity(): SEMActivity

    @ContributesAndroidInjector(
            modules = [CommunicationPortalActivityModule::class]
    )
    internal abstract fun bindCommunicationPortalActivity(): CommunicationPortalActivity

    @ContributesAndroidInjector(
        modules = [AssetActivityModule::class]
    )
    internal abstract fun bindAssetActivity(): AssetActivity
}
