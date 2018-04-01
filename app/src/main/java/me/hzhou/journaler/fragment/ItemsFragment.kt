package me.hzhou.journaler.fragment

import me.hzhou.journaler.R

/**
 * Created by hzhou on 4/1/18.
 * Email: hzhou.me@gmail.com
 */
class ItemsFragment : BaseFragment() {
    override val logTag: String = javaClass.name
    override fun getLayout() = R.layout.fragment_items
}