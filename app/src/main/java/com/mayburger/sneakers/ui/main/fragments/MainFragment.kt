package com.mayburger.sneakers.ui.main.fragments

import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mayburger.sneakers.BR
import com.mayburger.sneakers.R
import com.mayburger.sneakers.databinding.FragmentMainBinding
import com.mayburger.sneakers.models.Brand
import com.mayburger.sneakers.models.Sneaker
import com.mayburger.sneakers.ui.adapters.MainAdapter
import com.mayburger.sneakers.ui.base.BaseFragment
import com.mayburger.sneakers.ui.sneaker.SneakerActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>(), MainFragmentNavigator,MainAdapter.Callback{

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_main
    override val viewModel: MainFragmentViewModel by viewModels()

    @Inject
    lateinit var mainAdapter: MainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        viewDataBinding?.lifecycleOwner = viewLifecycleOwner
        mainAdapter.setListener(this)
        rvMain.adapter = mainAdapter
        viewModel.currentBrandName.value = arguments?.getParcelable<Brand>(EXTRA_BRAND)?.name
    }

    companion object{
        const val EXTRA_BRAND = "brand"
        fun newInstance(brand:Brand): Fragment {
            return MainFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_BRAND,brand)
                }
            }
        }
    }


    override fun onSelectedItem(sneaker: Sneaker, sharedCard: CardView) {
        val brandName = sneaker.brand?.toLowerCase()
        val brand = viewModel.dataManager.getBrands().filter { brandName == it.name.toLowerCase() }
        SneakerActivity.startActivity(requireActivity(),sneaker, brand[0])
    }

}