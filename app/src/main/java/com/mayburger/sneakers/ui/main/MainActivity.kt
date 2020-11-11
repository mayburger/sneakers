package com.mayburger.sneakers.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mayburger.sneakers.R
import com.mayburger.sneakers.BR
import com.mayburger.sneakers.databinding.ActivityMainBinding
import com.mayburger.sneakers.databinding.ItemBrandsBinding
import com.mayburger.sneakers.ui.adapters.MainAdapter
import com.mayburger.sneakers.ui.adapters.MainPagerAdapter
import com.mayburger.sneakers.ui.base.BaseActivity
import com.mayburger.sneakers.ui.main.fragments.MainFragment
import com.mayburger.sneakers.util.ext.ViewUtils.fadeHide
import com.mayburger.sneakers.util.ext.ViewUtils.fadeShow
import com.mayburger.sneakers.util.ext.ViewUtils.scaleX
import com.mayburger.sneakers.util.ext.ViewUtils.scaleY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.lifecycleOwner = this
        loadBrands()
        pager.adapter = MainPagerAdapter(this,viewModel.brandData.map {
            MainFragment.newInstance(it)
        }.toCollection(arrayListOf()))
        pager.isUserInputEnabled = false
    }

    fun loadBrands() {
        viewModel.brandData.mapIndexed { index, i ->
            ItemBrandsBinding.inflate(LayoutInflater.from(this)).apply {
                this@MainActivity.viewModel.currentIndex.observe(this@MainActivity, Observer {
                    pager.setCurrentItem(it,true)
                    if (it == index){
                        this.background.scaleX(1.2f,400)
                        this.background.scaleY(1.2f,400,onEnd = {
                            this.background.scaleX(1f,400)
                            this.background.scaleY(1f,400)
                        })
                        this.background.fadeShow(duration=400)
                        this.image.setColorFilter(resources.getColor(R.color.white))
                    } else{
                        this.background.fadeHide(duration=400,onEnd = {
                            this.background.scaleX = 0.5f
                            this.background.scaleY = 0.5f
                        })
                        this.image.setColorFilter(resources.getColor(R.color.colorTextGrey))
                    }
                })
                this.id = index
                this.viewModel = this@MainActivity.viewModel
                this.image.setImageResource(i.image)
                this.root.setOnClickListener{
                    viewModel?.currentIndex?.value = i.id
                    viewModel?.currentBrandName?.value = i.name
                }
                brands.addView(this.root)
            }
        }
    }
}