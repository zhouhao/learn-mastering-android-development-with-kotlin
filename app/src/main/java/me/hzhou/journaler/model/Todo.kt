package me.hzhou.journaler.model

import android.location.Location
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by hzhou on 4/2/18.
 * Email: hzhou.me@gmail.com
 */
class Todo(title: String,
           message: String,
           location: Location,
           var scheduledFor: Long) : Entry(title, message, location), Parcelable {
    override var id = 0L

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readParcelable<Location>(Location::class.java.classLoader),
            source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(message)
        writeParcelable(location, 0)
        writeLong(scheduledFor)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Todo> = object : Parcelable.Creator<Todo> {
            override fun createFromParcel(source: Parcel): Todo = Todo(source)
            override fun newArray(size: Int): Array<Todo?> = arrayOfNulls(size)
        }
    }
}