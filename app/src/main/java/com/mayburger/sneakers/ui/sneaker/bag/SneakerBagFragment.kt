package com.mayburger.sneakers.ui.sneaker.bag

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mayburger.sneakers.BR
import com.mayburger.sneakers.R
import com.mayburger.sneakers.databinding.FragmentSneakerBagBinding
import com.mayburger.sneakers.models.Brand
import com.mayburger.sneakers.models.Sneaker
import com.mayburger.sneakers.ui.adapters.CommonRecyclerAdapter
import com.mayburger.sneakers.ui.adapters.viewmodels.ItemSizesViewModel
import com.mayburger.sneakers.ui.base.BaseFragment
import com.mayburger.sneakers.util.ext.ViewUtils.alpha
import com.mayburger.sneakers.util.ext.ViewUtils.animToY
import com.mayburger.sneakers.util.ext.ViewUtils.destroy
import com.mayburger.sneakers.util.ext.ViewUtils.dpToPx
import com.mayburger.sneakers.util.ext.ViewUtils.scale
import com.mayburger.sneakers.util.ext.addTransitionListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sneaker_bag.*


@AndroidEntryPoint
class SneakerBagFragment : BaseFragment<FragmentSneakerBagBinding, SneakerBagViewModel>(),
    SneakerBagNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_sneaker_bag
    override val viewModel: SneakerBagViewModel by viewModels()

    companion object {
        const val TAG = "SneakerBagFragment"
        const val EXTRA_SNEAKER = "extra_sneaker"
        const val EXTRA_BRAND = "extra_brand"
        fun newInstance(sneaker: Sneaker?, brand: Brand?): Fragment {
            return SneakerBagFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_SNEAKER, sneaker)
                    putParcelable(EXTRA_BRAND, brand)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        viewDataBinding?.lifecycleOwner = viewLifecycleOwner
        viewModel.sneaker.value = arguments?.getParcelable(EXTRA_SNEAKER)
        viewModel.sneaker.value?.let { sneaker ->
            viewModel.name.value =
                "${sneaker.title?.get(0)?.toUpperCase()}${sneaker.title?.subSequence(
                    1,
                    sneaker.title.length
                )}"
        }
        viewModel.brand.value = arguments?.getParcelable(EXTRA_BRAND)
        viewModel.price.value = "$${viewModel.sneaker.value?.retailPrice?.toDouble()}"

        content.setOnClickListener {
            viewModel.size.value?.let{
                motion.setTransition(R.id.startTransition)
                motion.transitionToEnd()
            }
        }

        buildSizes()
        buildAnimations()
    }

    fun buildAnimations(){
        // On Start Animation
        motion.animToY(1f, duration = 600)
        background.alpha(0.6f, duration = 600)
        // On Order Animation
        motion.addTransitionListener(onEnd = {p0,p1->
            circle.animToY(-200f, duration = 600)
            image.animToY(-200f, duration = 600)
            circle.scale(0.8f, duration = 600)
            image.scale(0.6f, duration = 600, onEnd = {
                circle.animToY(400f, duration = 600)
                image.animToY(400f, duration = 600)
                circle.scale(0.2f, duration = 600)
                image.scale(0f, duration = 600,onEnd = {
                    onTriggerBack()
                })
            })
        })
    }

    fun buildSizes(){
        rvSizes.adapter = CommonRecyclerAdapter<ItemSizesViewModel>().apply{
            setItems(arrayListOf(
                    ItemSizesViewModel("EU 40"),
                    ItemSizesViewModel("EU 41"),
                    ItemSizesViewModel("EU 42"),
                    ItemSizesViewModel("EU 43"),
                    ItemSizesViewModel("EU 44")
            ))
            setListener(object:CommonRecyclerAdapter.Callback<ItemSizesViewModel>{
                override fun onSelectedItem(position: Int, item: ItemSizesViewModel) {
                    viewModel.size.value = item.size
                }
            })
        }
    }

    override fun onTriggerBack() {
        motion.animToY(dpToPx(500).toFloat(), duration = 600)
        background.alpha(0f, duration = 600, onEnd = {
            try {
                destroy()
            } catch (e: Exception) {
            }
        })
    }

}