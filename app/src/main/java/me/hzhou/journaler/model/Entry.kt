package me.hzhou.journaler.model

import android.location.Location
import me.hzhou.journaler.database.DbModel

/**
 * Created by hzhou on 4/2/18.
 * Email: hzhou.me@gmail.com
 */
abstract class Entry(var title: String, var message: String, var location: Location) : DbModel()

