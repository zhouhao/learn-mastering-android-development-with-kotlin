package me.hzhou.journaler.activity

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_note.*
import me.hzhou.journaler.R
import me.hzhou.journaler.database.Db
import me.hzhou.journaler.execution.TaskExecutor
import me.hzhou.journaler.location.LocationProvider
import me.hzhou.journaler.model.Note

/**
 * Created by hzhou on 3/31/18.
 * Email: hzhou.me@gmail.com
 */
class NoteActivity : ItemActivity() {
    override val tag: String = javaClass.name
    override fun getLayout() = R.layout.activity_note
    private var note: Note? = null
    private var location: Location? = null

    private val executor = TaskExecutor.getInstance(1)

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            updateNote()
        }
    }

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            location?.let {
                LocationProvider.unsubscribe(this)
                val title = getNoteTitle()
                val content = getNoteContent()
                note = Note(title, content, location)
                executor.execute {
                    val param = note
                    var result = false
                    param?.let {
                        result = Db.NOTE.insert(param) > 0

                    }
                    if (result) {
                        Log.i(tag, "Note inserted.")
                    } else {
                        Log.e(tag, "Note not inserted.")
                    }
                }
            }

        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        note_title.addTextChangedListener(textWatcher)
        note_content.addTextChangedListener(textWatcher)
    }

    private fun getNoteContent(): String {
        return note_content.text.toString()
    }

    private fun getNoteTitle(): String {
        return note_title.text.toString()
    }

    private fun updateNote() {
        if (note == null) {
            if (!TextUtils.isEmpty(getNoteTitle()) && !TextUtils.isEmpty(getNoteContent())) {
                LocationProvider.subscribe(locationListener)
            }
        } else {
            note?.title = getNoteTitle()
            note?.message = getNoteContent()
            executor.execute {
                val param = note
                var result = false

                param?.let {
                    result = Db.NOTE.update(param) > 0
                }

                if (result) {
                    Log.i(tag, "Note updated.")
                } else {
                    Log.e(tag, "Note not updated.")
                }

            }
        }
    }


}