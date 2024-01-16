package com.mz.app.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.Log


/**
 * Author: yuanchangzhu
 * Date: 2024/1/16 下午2:56
 * Description:日志工具类
 */
class MZLog private constructor() {
    companion object {
        var maxLevel: MZLogLevel = MZLogLevel.VERBOSE
        var baseTagPrefix: String = ""
        var tagFormat: String = "[%s][%s]"
        var isDebug: Boolean = true
        var debugLogLevel = MZLogLevel.DEBUG
        var isShowClassNumber = false
        var classNumberMessageFormat: String = "[%s()+%d]"

        fun v(tag: String, msg: String) {
            print(MZLogLevel.VERBOSE, tag, msg)
        }

        fun d(tag: String, msg: String) {
            print(MZLogLevel.DEBUG, tag, msg)
        }

        fun i(tag: String, msg: String) {
            print(MZLogLevel.INFO, tag, msg)
        }

        fun w(tag: String, msg: String) {
            print(MZLogLevel.WARNING, tag, msg)
        }

        fun e(tag: String, msg: String) {
            print(MZLogLevel.ERROR, tag, msg)
        }

        fun e(tag: String, msg: String, t: Throwable) {
            print(MZLogLevel.ERROR, tag, msg, t = t)
        }

        fun debug(tag: String, msg: String) {
            if (isDebug) print(debugLogLevel, tag, msg)
        }

        fun printStackTrace(tag: String) {
            if (isDebug) print(debugLogLevel, tag, Log.getStackTraceString(Throwable()))
        }

        private fun print(
            level: MZLogLevel,
            tag: String,
            msg: String,
            t: Throwable? = null,
        ) {
            if (level.ordinal < maxLevel.ordinal) return
            val finalTag = String.format(tagFormat, baseTagPrefix, tag)
            val finalMsg = if (isShowClassNumber) getClassLineNumber(tag, msg) else msg
            when (level) {
                MZLogLevel.VERBOSE -> Log.v(finalTag, finalMsg)
                MZLogLevel.DEBUG -> Log.d(finalTag, finalMsg)
                MZLogLevel.INFO -> Log.i(finalTag, finalMsg)
                MZLogLevel.WARNING -> Log.w(finalTag, finalMsg)
                MZLogLevel.ERROR -> {
                    Log.e(finalTag, finalMsg, t)
                }
            }
        }

        /**
         * 判断当前应用是否是debug状态
         */
        fun isApkInDebug(context: Context): Boolean {
            return try {
                val info = context.applicationInfo
                info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
            } catch (e: Exception) {
                false
            }
        }

        private fun getClassLineNumber(tag: String, msg: String): String {
            var message = msg
            try {
                for (st in Throwable().stackTrace) {
                    val b = st.className.lastIndexOf(".") + 1
                    val className = st.className.substring(b)
                    if (tag == className) {
                        message = StringBuilder().append(
                            String.format(
                                classNumberMessageFormat,
                                st.methodName,
                                st.lineNumber
                            )
                        ).append(msg).toString()
                        break
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return message
        }
    }
}