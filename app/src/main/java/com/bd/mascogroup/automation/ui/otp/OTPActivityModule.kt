package com.bd.mascogroup.automation.ui.otp

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class OTPActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(OTPViewModel::class)
    abstract fun bindOTPViewModel(viewModel: OTPViewModel): ViewModel
}