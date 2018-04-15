package me.hzhou.journaler.activity

import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_note.*
import me.hzhou.journaler.R
import me.hzhou.journaler.execution.TaskExecutor
import me.hzhou.journaler.location.LocationProvider
import me.hzhou.journaler.model.MODE
import me.hzhou.journaler.model.Note
import me.hzhou.journaler.service.DatabaseService

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
    private var handler: Handler? = null

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
                this@NoteActivity.location = location;
                note = Note(title, content, location)

                // Switching to intent service.
                val dbIntent = Intent(this@NoteActivity, DatabaseService::class.java)
                dbIntent.putExtra(DatabaseService.EXTRA_ENTRY, note)
                dbIntent.putExtra(DatabaseService.EXTRA_OPERATION, MODE.CREATE.mode)
                startService(dbIntent)
                sendMessage(true)
            }

        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }

    }

    private fun sendMessage(result: Boolean) {
        val msg = handler?.obtainMessage()
        if (result) {
            msg?.arg1 = 1
        } else {
            msg?.arg1 = 0
        }
        handler?.sendMessage(msg)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message?) {
                msg?.let {
                    var color = R.color.vermilion
                    if (msg.arg1 > 0) {
                        color = R.color.green
                    }
                    indicator.setBackgroundColor(ContextCompat.getColor(this@NoteActivity, color))
                }
                super.handleMessage(msg)
            }
        }
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

            // Switching to intent service.
            val dbIntent = Intent(this@NoteActivity, DatabaseService::class.java)
            dbIntent.putExtra(DatabaseService.EXTRA_ENTRY, note)
            dbIntent.putExtra(DatabaseService.EXTRA_OPERATION, MODE.EDIT.mode)
            startService(dbIntent)
            sendMessage(true)
        }
    }

}