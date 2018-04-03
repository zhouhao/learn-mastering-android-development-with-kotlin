package me.hzhou.journaler.model

import android.location.Location

/**
 * Created by hzhou on 4/2/18.
 * Email: hzhou.me@gmail.com
 */
class Note(title: String,
           message: String,
           location: Location) : Entry(title, message, location) {

    override var id = 0L
}