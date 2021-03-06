package com.mayburger.sneakers.util.binding

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.mayburger.sneakers.ui.main.MainAdapter
import com.mayburger.sneakers.ui.viewmodels.ItemMainViewModel

object AdapterBinding {

    @BindingAdapter("mainAdapter")
    @JvmStatic
    fun addMainItems(
        recyclerView: RecyclerView,
        items: LiveData<List<ItemMainViewModel>?>
    ) {
        val adapter = recyclerView.adapter as MainAdapter?
        if (adapter != null) {
            items.value?.let {
                adapter.clearItems()
                adapter.addItems(ArrayList(it))
            }
        }
    }

}