package com.bd.mascogroup.automation.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.bd.mascogroup.automation.data.AppDataManager
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.local.db.AppDatabase
import com.bd.mascogroup.automation.data.local.db.AppDbHelper
import com.bd.mascogroup.automation.data.local.db.IDbHelper
import com.bd.mascogroup.automation.data.local.prefs.IPreferencesHelper
import com.bd.mascogroup.automation.data.local.prefs.PreferencesHelper
import com.bd.mascogroup.automation.di.DatabaseInfo
import com.bd.mascogroup.automation.di.PreferenceInfo
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.rx.AppISchedulerProvider
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, NetworkModule::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): IDataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
        lateinit var appDatabase: AppDatabase
        try {
            appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, dbName)
                    .fallbackToDestructiveMigration()
                    .build()
        } catch (ex: Exception) {

        }

        return appDatabase

    }

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @Singleton
    internal fun provideDbHelper(appDbHelper: AppDbHelper): IDbHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }


    @Provides
    @PreferenceInfo
    internal fun providePreferenceName(): String {
        return "dagger_pref"
    }

    @Provides
    @Singleton
    internal fun providePreferencesHelper(appPreferencesHelper: PreferencesHelper): IPreferencesHelper {
        return appPreferencesHelper
    }
    @Provides
    internal fun provideSchedulerProvider(): ISchedulerProvider {
        return AppISchedulerProvider()
    }
}