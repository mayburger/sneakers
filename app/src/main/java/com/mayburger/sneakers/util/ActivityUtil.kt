package com.mayburger.sneakers.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mayburger.sneakers.R

object ActivityUtil {

    fun loadFragment(idFragment: Int, manager: FragmentManager, fragment: Fragment) {
        val transaction = manager.beginTransaction()
        val existingFrag = manager.findFragmentByTag(fragment.javaClass.name)
        if (existingFrag == null) {
            try {
                transaction.add(idFragment, fragment, fragment.javaClass.name)
                showViewInBackStack(transaction, manager, fragment)
                transaction.addToBackStack(fragment.javaClass.name)
                transaction.commit()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
        else {
            try {
                showViewInBackStack(transaction, manager, fragment)
                transaction.addToBackStack(fragment.javaClass.name)
                transaction.commit()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showViewInBackStack(transaction: FragmentTransaction, manager: FragmentManager, fragment: Fragment) {
        val fragList = manager.fragments
        for (frag in fragList) {
            if (frag.tag.equals(fragment.javaClass.name)) {
                transaction.show(frag)
            } else {
                transaction.hide(frag)
            }
        }
    }

    fun popFragment(manager:FragmentManager,fragment: Fragment){
        val transaction = manager.beginTransaction()
        val existingFrag = manager.findFragmentByTag(fragment.javaClass.name)
        if(existingFrag != null){
            transaction.remove(existingFrag)
        }
    }



    fun addFragment(fragment: Fragment, fragmentManager: FragmentManager) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, fragment)
        fragmentTransaction.commit()
    }
}