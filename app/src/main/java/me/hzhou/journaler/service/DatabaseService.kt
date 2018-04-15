package me.hzhou.journaler.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import me.hzhou.journaler.database.Crud
import me.hzhou.journaler.database.Db
import me.hzhou.journaler.model.MODE
import me.hzhou.journaler.model.Note

/**
 * Created by hzhou on 4/5/18.
 * Email: hzhou.me@gmail.com
 */
class DatabaseService : IntentService("DatabaseService") {
    companion object {
        val EXTRA_ENTRY = "entry"
        val EXTRA_OPERATION = "operation"
    }

    private val tag = "Database Service"

    override fun onCreate() {
        super.onCreate()
        Log.v(tag, "[ On Create ]")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.w(tag, "[ ON LOW MEMORY ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(tag, "[ ON DESTROY ]")
    }

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val note = intent.getParcelableExtra<Note>(EXTRA_ENTRY)
            note?.let {
                val operation = intent.getIntExtra(EXTRA_OPERATION, -1)
                when (operation) {
                    MODE.CREATE.mode -> {
                        val result = Db.NOTE.insert(note) > 0
                        if (result) {
                            Log.i(tag, "Note inserted.")
                        } else {
                            Log.e(tag, "Note not inserted.")
                        }
                        broadcastResult(result)
                    }
                    MODE.EDIT.mode -> {
                        val result = Db.NOTE.update(note) > 0
                        if (result) {
                            Log.i(tag, "Note updated.")
                        } else {
                            Log.e(tag, "Note not updated.")
                        }
                        broadcastResult(result)
                    }
                    else -> {
                        Log.w(tag, "Unknown mode [ $operation ]")
                    }
                }
            }
        }
    }

    private fun broadcastResult(result: Boolean) {
        val intent = Intent()
        intent.putExtra(Crud.BROADCAST_EXTRAS_KEY_CRUD_OPERATION_RESULT, if (result) 1 else 0)
    }
}