package com.mayburger.sneakers.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mayburger.sneakers.R
import com.mayburger.sneakers.BR
import com.mayburger.sneakers.databinding.ActivityMainBinding
import com.mayburger.sneakers.databinding.ItemBrandsBinding
import com.mayburger.sneakers.models.Brand
import com.mayburger.sneakers.models.Sneaker
import com.mayburger.sneakers.ui.adapters.MainAdapter
import com.mayburger.sneakers.ui.adapters.MainPagerAdapter
import com.mayburger.sneakers.ui.base.BaseActivity
import com.mayburger.sneakers.ui.main.fragments.MainFragment
import com.mayburger.sneakers.ui.sneaker.SneakerActivity
import com.mayburger.sneakers.util.ext.ViewUtils.fadeHide
import com.mayburger.sneakers.util.ext.ViewUtils.fadeShow
import com.mayburger.sneakers.util.ext.ViewUtils.scaleX
import com.mayburger.sneakers.util.ext.ViewUtils.scaleY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator{

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()


    val pagerChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            viewModel.currentIndex.value = position
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.lifecycleOwner = this
        pager.adapter = MainPagerAdapter(this, viewModel.brands.map {
            MainFragment.newInstance(it)
        }.toCollection(arrayListOf()))
        pager.registerOnPageChangeCallback(pagerChangeCallback)
        TabLayoutMediator(tab, pager) { tab, position ->
            tab.customView = getTabLayout(position,viewModel.brands[position])
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        pager.unregisterOnPageChangeCallback(pagerChangeCallback)
    }

    fun getTabLayout(index:Int, i: Brand): View {
        return ItemBrandsBinding.inflate(LayoutInflater.from(this)).apply {
            this@MainActivity.viewModel.currentIndex.observe(this@MainActivity, Observer {
                pager.setCurrentItem(it, true)
                if (it == index) {
                    this.background.scaleX(1.2f, 400)
                    this.background.scaleY(1.2f, 400, onEnd = {
                        this.background.scaleX(1f, 400)
                        this.background.scaleY(1f, 400)
                    })
                    this.background.fadeShow(duration = 400)
                    this.image.setColorFilter(resources.getColor(R.color.white))
                } else {
                    this.background.fadeHide(duration = 400, onEnd = {
                        this.background.scaleX = 0.5f
                        this.background.scaleY = 0.5f
                    })
                    this.image.setColorFilter(resources.getColor(R.color.colorTextGrey))
                }
            })
            this.id = index
            this.viewModel = this@MainActivity.viewModel
            this.image.setImageResource(i.image)
            this.root.setOnClickListener {
                viewModel?.currentIndex?.value = i.id
                viewModel?.currentBrandName?.value = i.name
            }
        }.root
    }
}