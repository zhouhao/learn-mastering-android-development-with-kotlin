package me.hzhou.journaler.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by hzhou on 4/15/18.
 * Email: hzhou.me@gmail.com
 */
class BootReceiver : BroadcastReceiver() {
    val tag = "Boot receiver"

    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.i(tag, "Boot completed.")
        // Perform your on boot stuff here.
    }
}