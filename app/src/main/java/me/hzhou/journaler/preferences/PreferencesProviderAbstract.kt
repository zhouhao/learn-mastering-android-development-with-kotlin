package me.hzhou.journaler.preferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by hzhou on 4/3/18.
 * Email: hzhou.me@gmail.com
 */
abstract class PreferencesProviderAbstract {
    abstract fun obtain(configuration: PreferencesConfiguration, ctx: Context): SharedPreferences
}