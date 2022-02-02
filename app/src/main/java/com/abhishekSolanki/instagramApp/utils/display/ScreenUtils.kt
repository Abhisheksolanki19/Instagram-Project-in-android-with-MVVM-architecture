package com.abhishekSolanki.instagramApp.utils.display

import android.content.res.Resources

object ScreenUtils {

    fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels

    fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels
}