package me.hzhou.journaler

import android.app.Application
import android.content.Context
import android.util.Log

/**
 * Created by hzhou on 4/2/18.
 * Email: hzhou.me@gmail.com
 */
class Journaler : Application() {

    companion object {
        val tag = "Journaler"
        var ctx: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        Log.v(tag, "[ ON CREATE ]")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.w(tag, "[ ON LOW MEMORY ]")
        // If we get low on memory we will stop service if running.
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Log.d(tag, "[ ON TRIM MEMORY ]: $level")
    }
}