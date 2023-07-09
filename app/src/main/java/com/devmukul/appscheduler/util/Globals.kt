package com.devmukul.appscheduler.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.MailTo
import java.util.Random

object Globals {
    fun isPremiumUser(context: Context): Boolean {
        return try {
            loadBooleanPreferences(Constants.ITEM_SKU_PREMIUM, "inapp", context, false)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun openAppInfo(context: Context, str: String?) {
        try {
            val intent = Intent()
            intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
            intent.data = Uri.fromParts("package", str, null as String?)
            intent.flags = 268435456
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isValidValue(charSequence: CharSequence?): Boolean {
        if (charSequence != null) {
            try {
                if (charSequence.length != 0) {
                    return true
                }
            } catch (unused: Exception) {
            }
        }
        return false
    }

    fun isValidValue(str: String?): Boolean {
        if (str != null) {
            try {
                if (str.length != 0) {
                    return true
                }
            } catch (unused: Exception) {
            }
        }
        return false
    }

    fun isValidValue(strArr: Array<String?>?): Boolean {
        if (strArr != null) {
            try {
                if (strArr.size != 0) {
                    return true
                }
            } catch (unused: Exception) {
            }
        }
        return false
    }

    fun isValidValue(arrayList: ArrayList<*>?): Boolean {
        if (arrayList != null) {
            try {
                if (arrayList.size > 0) {
                    return true
                }
            } catch (unused: Exception) {
            }
        }
        return false
    }

    fun isValidValue(list: List<*>?): Boolean {
        if (list != null) {
            try {
                if (list.size > 0) {
                    return true
                }
            } catch (unused: Exception) {
            }
        }
        return false
    }

    fun getStringFromResources(i: Int, context: Context): String {
        return try {
            context.resources.getString(i)
        } catch (unused: Exception) {
            ""
        }
    }

    fun showToastMessage(str: String?, context: Context?) {
        if (str != null) {
            Toast.makeText(context, str, 1).show()
        }
    }

    fun isConnected(context: Context): Boolean {
        return try {
            val connectivityManager =
                context.getSystemService("connectivity") as ConnectivityManager
            if (connectivityManager.activeNetworkInfo != null) {
                connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
            } else false
        } catch (unused: Exception) {
            false
        }
    }

    fun savePreferences(str: String?, str2: String?, str3: String?, context: Context) {
        val edit = context.getSharedPreferences(str3, 0).edit()
        edit.putString(str, str2)
        edit.apply()
    }

    fun savePreferences(str: String?, i: Int, str2: String?, context: Context) {
        val edit = context.getSharedPreferences(str2, 0).edit()
        edit.putInt(str, i)
        edit.apply()
    }

    fun savePreferences(str: String?, j: Long, str2: String?, context: Context) {
        val edit = context.getSharedPreferences(str2, 0).edit()
        edit.putLong(str, j)
        edit.apply()
    }

    fun savePreferences(str: String?, f: Float, str2: String?, context: Context) {
        val edit = context.getSharedPreferences(str2, 0).edit()
        edit.putFloat(str, f)
        edit.apply()
    }

    fun savePreferences(str: String?, z: Boolean, str2: String?, context: Context) {
        val edit = context.getSharedPreferences(str2, 0).edit()
        edit.putBoolean(str, z)
        edit.apply()
    }

    fun loadStringPreferences(
        str: String?,
        str2: String?,
        context: Context,
        str3: String?
    ): String? {
        return context.getSharedPreferences(str2, 0).getString(str, str3)
    }

    fun loadIntPreferences(str: String?, str2: String?, context: Context, i: Int): Int {
        return context.getSharedPreferences(str2, 0).getInt(str, i)
    }

    fun loadLongPreferences(str: String?, str2: String?, context: Context, j: Long): Long {
        return context.getSharedPreferences(str2, 0).getLong(str, j)
    }

    fun loadFloatPreferences(str: String?, str2: String?, context: Context, f: Float): Float {
        return context.getSharedPreferences(str2, 0).getFloat(str, f)
    }

    fun loadBooleanPreferences(str: String?, str2: String?, context: Context, z: Boolean): Boolean {
        return context.getSharedPreferences(str2, 0).getBoolean(str, z)
    }

    fun startGenericService(context: Context, bundle: Bundle?, i: Int, cls: Class<*>?) {
        try {
            val intent = Intent(context, cls)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            intent.addFlags(i)
            context.startService(intent)
        } catch (unused: Exception) {
        }
    }

    fun startGenericForegroundService(context: Context?, bundle: Bundle?, i: Int, cls: Class<*>?) {
        try {
            val intent = Intent(context, cls)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            intent.addFlags(i)
            ContextCompat.startForegroundService(context!!, intent)
        } catch (unused: Exception) {
        }
    }

    fun stopGenericService(context: Context, str: String?, str2: String?) {
        try {
            val intent = Intent()
            intent.setClassName(str!!, str2!!)
            context.stopService(intent)
        } catch (unused: Exception) {
        }
    }

    fun startGenericActivity(context: Context, str: String?, bundle: Bundle?, i: Int) {
        try {
            val intent = Intent(str)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            intent.flags = i
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun startGenericActivity(context: Context, bundle: Bundle?, i: Int, cls: Class<*>?) {
        try {
            val intent = Intent(context, cls)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            intent.addFlags(i)
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun refreshActivity(
        activity: Activity,
        str: String?,
        str2: String?,
        z: Boolean,
        bundle: Bundle?,
        i: Int
    ): Boolean {
        if (z) {
            try {
                activity.finish()
                val intent = Intent()
                intent.setClassName(str!!, str2!!)
                if (bundle != null) {
                    intent.putExtras(bundle)
                }
                intent.flags = i
                activity.startActivity(intent)
                return true
            } catch (unused: Exception) {
            }
        }
        return false
    }

    fun refreshActivity(
        activity: Activity,
        z: Boolean,
        bundle: Bundle?,
        i: Int,
        context: Context?,
        cls: Class<*>?
    ): Boolean {
        if (z) {
            try {
                activity.finish()
                val intent = Intent(context, cls)
                if (bundle != null) {
                    intent.putExtras(bundle)
                }
                intent.flags = i
                activity.startActivity(intent)
                return true
            } catch (unused: Exception) {
            }
        }
        return false
    }

    fun sendGenericBroadcast(context: Context, str: String?, bundle: Bundle?) {
        try {
            val intent = Intent()
            intent.action = str
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            context.sendBroadcast(intent)
        } catch (unused: Exception) {
        }
    }

    fun closeGenericActivity(activity: Activity?) {
        if (activity != null) {
            try {
                activity.finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun startExternalActivity(str: String?, str2: String?, activity: Activity) {
        try {
            val intent = Intent(str2)
            intent.data = Uri.parse(str)
            activity.startActivity(intent)
        } catch (e: Exception) {
            showToastMessage(
                getStringFromResources(C0491R.string.no_app_available, activity),
                activity
            )
            e.printStackTrace()
        }
    }

    fun startExternalActivity(str: String?, activity: Activity) {
        try {
            val intent = Intent("android.intent.action.SEND")
            intent.type = "text/plain"
            intent.putExtra("android.intent.extra.TEXT", str)
            activity.startActivity(
                Intent.createChooser(
                    intent,
                    activity.resources.getString(C0491R.string.share)
                )
            )
        } catch (e: Exception) {
            showToastMessage(
                getStringFromResources(C0491R.string.no_app_available, activity),
                activity
            )
            e.printStackTrace()
        }
    }

    fun startEmailActivity(
        activity: Activity,
        strArr: Array<String?>?,
        str: String?,
        str2: String?
    ) {
        try {
            val resources = activity.resources
            val intent = Intent("android.intent.action.SENDTO")
            intent.type = "message/rfc822"
            intent.putExtra("android.intent.extra.SUBJECT", str)
            intent.putExtra("android.intent.extra.TEXT", str2)
            intent.data = Uri.parse(MailTo.MAILTO_SCHEME)
            intent.putExtra("android.intent.extra.EMAIL", strArr)
            activity.startActivity(
                Intent.createChooser(
                    intent,
                    resources.getString(C0491R.string.send_email)
                )
            )
        } catch (e: Exception) {
            showToastMessage(
                getStringFromResources(C0491R.string.no_app_available, activity),
                activity
            )
            e.printStackTrace()
        }
    }

    fun getSupportEmailSubject(context: Context): String {
        val str: String
        val string = context.resources.getString(C0491R.string.app_name)
        str = try {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
        return if (!isValidValue(str)) {
            string
        } else "$string ($str)"
    }

    val uniqueId: Int
        get() = try {
            Random().nextInt(Int.MAX_VALUE) + 1
        } catch (unused: Exception) {
            -1
        }

    fun getUniqueId(arrayList: ArrayList<Int?>): Int {
        val nextInt = Random().nextInt(Int.MAX_VALUE) + 1
        return if (!arrayList.contains(Integer.valueOf(nextInt))) {
            nextInt
        } else getUniqueId(
            arrayList
        )
    }

    fun hideKeyboard(activity: Activity, view: View?) {
        if (view != null) {
            try {
                (activity.getSystemService("input_method") as InputMethodManager).hideSoftInputFromWindow(
                    view.windowToken,
                    0
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun showKeyboard(activity: Activity, view: View?) {
        if (view != null) {
            try {
                (activity.getSystemService("input_method") as InputMethodManager).showSoftInput(
                    view,
                    0
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getAppIntent(context: Context, str: String?): Intent? {
        var intent: Intent? = null
        return try {
            intent = context.packageManager.getLaunchIntentForPackage(str!!)
            intent!!.addCategory("android.intent.category.LAUNCHER")
            intent.flags = 268435456
            intent
        } catch (e: Exception) {
            e.printStackTrace()
            intent
        }
    }

    fun openApp(context: Context, str: String?) {
        try {
            val launchIntentForPackage = context.packageManager.getLaunchIntentForPackage(
                str!!
            )
            launchIntentForPackage!!.addCategory("android.intent.category.LAUNCHER")
            launchIntentForPackage.flags = 268435456
            context.startActivity(launchIntentForPackage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun canDrawOverlays(context: Context?): Boolean {
        return Settings.canDrawOverlays(context)
    }
}