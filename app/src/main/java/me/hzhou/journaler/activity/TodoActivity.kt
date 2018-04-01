package me.hzhou.journaler.activity

import me.hzhou.journaler.R

/**
 * Created by hzhou on 3/31/18.
 * Email: hzhou.me@gmail.com
 */
class TodoActivity : ItemActivity() {
    override val tag: String = javaClass.name
    override fun getLayout() = R.layout.activity_todo

}