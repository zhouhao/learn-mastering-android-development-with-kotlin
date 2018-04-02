package me.hzhou.journaler.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_todo.*
import me.hzhou.journaler.R

/**
 * Created by hzhou on 3/31/18.
 * Email: hzhou.me@gmail.com
 */
class TodoActivity : ItemActivity() {

    companion object {
        val EXTRA_DATE = "EXTRA_DATE"
        val EXTRA_TIME = "EXTRA_TIME"
    }

    override val tag: String = javaClass.name
    override fun getLayout() = R.layout.activity_todo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.extras
        data?.let {
            val date = data.getString(EXTRA_DATE, "")
            val time = data.getString(EXTRA_TIME, "")
            pick_date.text = date
            pick_time.text = time
        }
    }
}