package com.mayburger.sneakers.di

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayburger.sneakers.ui.adapters.MainAdapter
import com.mayburger.sneakers.ui.adapters.MainPagerAdapter
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