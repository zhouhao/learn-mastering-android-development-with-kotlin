package me.hzhou.journaler.preferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by hzhou on 4/3/18.
 * Email: hzhou.me@gmail.com
 */
class PreferencesProvider : PreferencesProviderAbstract() {
    override fun obtain(configuration: PreferencesConfiguration, ctx: Context): SharedPreferences {
        return ctx.getSharedPreferences(configuration.key, configuration.mode)
    }
}