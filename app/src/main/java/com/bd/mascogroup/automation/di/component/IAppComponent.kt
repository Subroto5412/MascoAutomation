package com.bd.mascogroup.automation.di.component

import android.app.Application
import com.bd.mascogroup.automation.MascoGroupApp
import com.bd.mascogroup.automation.di.builder.ActivityBuilder
import com.bd.mascogroup.automation.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])

interface IAppComponent: AndroidInjector<MascoGroupApp> {
    override fun inject(app: MascoGroupApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): IAppComponent
    }
}