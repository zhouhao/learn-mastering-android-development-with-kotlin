package me.hzhou.journaler.activity

import android.os.Bundle
import android.util.Log
import me.hzhou.journaler.R
import me.hzhou.journaler.model.MODE

/**
 * Created by hzhou on 3/31/18.
 * Email: hzhou.me@gmail.com
 */
abstract class ItemActivity : BaseActivity() {
    override fun getActivityTitle() = R.string.app_name
    protected var mode = MODE.VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val modeToSet = intent.getIntExtra(MODE.EXTRAS_KEY, MODE.VIEW.mode)
        mode = MODE.getByValue(modeToSet)
        Log.v(tag, "Mode [ $mode ]")
    }
}
