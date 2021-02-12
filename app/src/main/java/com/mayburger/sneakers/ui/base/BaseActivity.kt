package com.mayburger.sneakers.ui.base

import android.annotation.TargetApi
import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentResolver
import android.content.Context
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.facebook.stetho.Stetho
import com.google.android.material.snackbar.Snackbar
import com.mayburger.sneakers.BuildConfig
import com.mayburger.sneakers.R
import com.mayburger.sneakers.util.ShakeDetector
import com.mayburger.sneakers.util.ext.ViewUtils
import com.readystatesoftware.chuck.Chuck


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(),
    BaseFragment.Callback,
    BaseNavigator {

    lateinit var viewDataBinding: T
        private set

    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int
    private var pDialog: ProgressDialog? = null

    abstract val viewModel: V


    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun showBottomSheet(fragment: BaseFragment<*, *>, tag: String) {

    }

    open fun setLightStatusBar(view: View, activity: Activity) {
    }


    fun delay(delay: Long, runnable: ()->Unit) {
        Handler().postDelayed({
            runnable.invoke()
        },delay)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vaClass = Class.forName("android.animation.ValueAnimator")
        val method = vaClass.getMethod("setDurationScale",Float::class.java)
        method.invoke(null, 0.5f)
        pDialog = ViewUtils.getProgressDialog(this, "Please Wait")
        performDataBinding()
        Stetho.initializeWithDefaults(this)
        initShakeToDebugApiCall()
    }

    fun initShakeToDebugApiCall() {
        val sensorManager =
            getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val shakeDetector = ShakeDetector {
            if (BuildConfig.DEBUG) {
                startActivity(Chuck.getLaunchIntent(this))
            }
        }
        shakeDetector.start(sensorManager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
//        if (!TimeUtils.isTimeValid(this)){
//            val dialog = AlertDialog.Builder(this)
//            dialog.setTitle("Incorrect Date")
//            dialog.setMessage("Your date settings is incorrect, please set it to Automatic date & time")
//            dialog.setPositiveButton("Okay") { _: DialogInterface, i: Int ->
//                finishActivity()
//            }
//            dialog.show()
//        }
    }

    override fun hideLoading() {
        if (pDialog?.isShowing == true) pDialog?.dismiss()
    }

    override fun showLoading() {
        if (pDialog?.isShowing == false) pDialog?.show()
    }

    override fun onError(message: String?) {
        message?.let { showSnackBar(it) }
    }

    override fun showSnackBar(message: String) {
        val snackBar: Snackbar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        val sbView: View = snackBar.view
        val textView: TextView = sbView.findViewById(R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        snackBar.show()
    }


    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)

        //this.mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding.apply {
            setVariable(bindingVariable, viewModel)
            executePendingBindings()
        }
    }


    override fun finishActivity() {
        finish()
    }

    override fun getContentResolver(): ContentResolver = super.getContentResolver()
}

