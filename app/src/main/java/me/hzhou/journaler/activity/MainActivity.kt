package me.hzhou.journaler.activity

import android.os.Bundle
import me.hzhou.journaler.R
import me.hzhou.journaler.fragment.ItemsFragment

class MainActivity : BaseActivity() {

    override val tag: String = "MainActivity"
    override fun getLayout() = R.layout.activity_main
    override fun getActivityTitle() = R.string.app_name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = ItemsFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
    }

}
