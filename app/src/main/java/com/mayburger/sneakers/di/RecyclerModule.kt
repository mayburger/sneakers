package com.mayburger.sneakers.di

import com.mayburger.sneakers.ui.main.MainAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object RecyclerModule {

    @Provides
    internal fun provideMainAdapter(): MainAdapter {
        return MainAdapter()
    }

}