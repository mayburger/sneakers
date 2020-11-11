package com.mayburger.sneakers.di

import android.app.Application
import android.content.Context
import com.mayburger.sneakers.data.AppDataManager
import com.mayburger.sneakers.data.DataManager
import com.mayburger.sneakers.data.hawk.AppHawkHelper
import com.mayburger.sneakers.data.hawk.HawkHelper
import com.mayburger.sneakers.data.remote.ApiHelper
import com.mayburger.sneakers.util.rx.AppSchedulerProvider
import com.mayburger.sneakers.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }


    @Provides
    @Singleton
    internal fun provideApiHelper(): ApiHelper = ApiHelper.create()

    @Provides
    @Singleton
    internal fun provideHawkHelper(appHawkHelper: AppHawkHelper): HawkHelper = appHawkHelper

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}