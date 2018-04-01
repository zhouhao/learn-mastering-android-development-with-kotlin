package me.hzhou.journaler.activity

import me.hzhou.journaler.R

class MainActivity : BaseActivity() {

    override val tag: String = "MainActivity"
    override fun getLayout() = R.layout.activity_main
    override fun getActivityTitle() = R.string.app_name
}
