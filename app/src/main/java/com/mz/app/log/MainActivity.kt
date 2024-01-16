package com.mz.app.log

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.mz.app.utils.MZLog

class MainActivity : ComponentActivity() {
    companion object {
        const val BASE_TAG = "MZLOG"
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLog()
        testLog()
    }

    private fun initLog() {
        MZLog.baseTagPrefix = BASE_TAG
        MZLog.isDebug = MZLog.isApkInDebug(this)
        MZLog.isShowClassNumber = true
        MZLog.classNumberMessageFormat = "[%s()+%d]->"
    }

    private fun testLog() {
        MZLog.v(TAG,"log v")
        MZLog.d(TAG,"log d")
        MZLog.i(TAG,"log i")
        MZLog.w(TAG,"log w")
        MZLog.e(TAG,"log e")
        MZLog.e(TAG,"log e2", Throwable("t"))
        MZLog.debug(TAG,"log debug")
        MZLog.printStackTrace(TAG)
    }
}

