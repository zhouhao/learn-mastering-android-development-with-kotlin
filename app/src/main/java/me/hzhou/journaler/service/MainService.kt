package me.hzhou.journaler.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import me.hzhou.journaler.execution.TaskExecutor

/**
 * Created by hzhou on 4/5/18.
 * Email: hzhou.me@gmail.com
 */
class MainService : Service(), DataSynchronization {

    private val tag = "Main Service"
    private val binder = getServiceBinder()
    private val executor = TaskExecutor.getInstance(1)

    override fun onCreate() {
        super.onCreate()
        Log.v(tag, "[ On Create ]")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.v(tag, "[ On Start Command ]")
        synchronize()
        return Service.START_STICKY
    }

    private fun getServiceBinder() = MainServiceBinder()

    override fun onBind(intent: Intent?): IBinder {
        Log.v(tag, "[ ON BIND ]")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        val result = super.onUnbind(intent)
        Log.v(tag, "[ On Unbind ]")
        return result
    }

    override fun onDestroy() {
        synchronize()
        super.onDestroy()
        Log.v(tag, "[ On Destroy ]")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.w(tag, "[ ON LOW MEMORY ]")
    }

    override fun synchronize() {
        executor.execute {
            Log.i(tag, "Synchronizing data [ START ]")
            // For now we will only simulate this operation!
            Thread.sleep(3000)
            Log.i(tag, "Synchronizing data [ END ]")
        }
    }

    inner class MainServiceBinder : Binder() {
        fun getService() = this@MainService
    }
}