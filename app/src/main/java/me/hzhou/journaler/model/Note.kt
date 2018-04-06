package me.hzhou.journaler.model

import android.location.Location
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by hzhou on 4/2/18.
 * Email: hzhou.me@gmail.com
 */
class Note(title: String,
           message: String,
           location: Location) : Entry(title, message, location), Parcelable {
    override var id = 0L

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readParcelable<Location>(Location::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(message)
        writeParcelable(location, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Note> = object : Parcelable.Creator<Note> {
            override fun createFromParcel(source: Parcel): Note = Note(source)
            override fun newArray(size: Int): Array<Note?> = arrayOfNulls(size)
        }
    }
}