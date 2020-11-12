package com.mayburger.sneakers.ui.sneaker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.mayburger.sneakers.BR
import com.mayburger.sneakers.R
import com.mayburger.sneakers.databinding.ActivitySneakerBinding
import com.mayburger.sneakers.models.Brand
import com.mayburger.sneakers.models.Sneaker
import com.mayburger.sneakers.models.events.BackEvent
import com.mayburger.sneakers.ui.base.BaseActivity
import com.mayburger.sneakers.ui.sneaker.bag.SneakerBagFragment
import com.mayburger.sneakers.util.ext.ViewUtils.hasTag
import com.mayburger.sneakers.util.rx.RxBus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sneaker.*


@AndroidEntryPoint
class SneakerActivity : BaseActivity<ActivitySneakerBinding, SneakerViewModel>(), SneakerNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_sneaker
    override val viewModel: SneakerViewModel by viewModels()

    companion object {
        const val EXTRA_SNEAKER = "extra_sneaker"
        const val EXTRA_BRAND = "extra_brand"
        fun startActivity(context: Activity, sneaker: Sneaker, brand: Brand) {
            context.startActivity(Intent(context, SneakerActivity::class.java).apply {
                putExtra(EXTRA_SNEAKER, sneaker)
                putExtra(EXTRA_BRAND, brand)
            })
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.lifecycleOwner = this
        viewModel.navigator = this
        viewModel.sneaker.value = intent.getParcelableExtra(EXTRA_SNEAKER)
        viewModel.sneaker.value?.let { sneaker ->
            viewModel.name.value =
                "${sneaker.title?.get(0)?.toUpperCase()}${sneaker.title?.subSequence(
                    1,
                    sneaker.title.length
                )}"
        }
        viewModel.brand.value = intent.getParcelableExtra(EXTRA_BRAND)
        viewModel.price.value = "$${viewModel.sneaker.value?.retailPrice?.toDouble()}"
    }

    override fun onBackPressed() {
        if (supportFragmentManager.hasTag(SneakerBagFragment.TAG)) {
            RxBus.getDefault().send(BackEvent())
        } else {
            super.onBackPressed()
        }
    }

    override fun onClickBag() {
        supportFragmentManager.beginTransaction().apply {
            add(
                R.id.container,
                SneakerBagFragment.newInstance(viewModel.sneaker.value, viewModel.brand.value),
                SneakerBagFragment.TAG
            )
            commit()
        }
    }

}