package me.hzhou.journaler.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_header.*
import me.hzhou.journaler.R
import me.hzhou.journaler.fragment.ItemsFragment
import me.hzhou.journaler.fragment.ManualFragment

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

        filter_menu.text = "H"
        filter_menu.setOnClickListener {
            val userManualFragment = ManualFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, userManualFragment)
                    .addToBackStack("User Manual")
                    .commit()
        }
    }

}
