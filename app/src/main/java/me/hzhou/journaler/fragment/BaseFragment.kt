package me.hzhou.journaler.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by hzhou on 4/1/18.
 * Email: hzhou.me@gmail.com
 */
abstract class BaseFragment : Fragment() {

    protected abstract val logTag: String
    protected abstract fun getLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d(logTag, "[ ON CREATE VIEW ]")
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onPause() {
        super.onPause()
        Log.v(logTag, "[ ON PAUSE ]")
    }

    override fun onResume() {
        super.onResume()
        Log.v(logTag, "[ ON RESUME ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(logTag, "[ ON DESTROY ]")
    }

}