package me.hzhou.journaler.fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.hzhou.journaler.R
import me.hzhou.journaler.activity.NoteActivity
import me.hzhou.journaler.activity.TodoActivity
import me.hzhou.journaler.model.MODE

/**
 * Created by hzhou on 4/1/18.
 * Email: hzhou.me@gmail.com
 */
class ItemsFragment : BaseFragment() {
    override val logTag: String = javaClass.name
    override fun getLayout() = R.layout.fragment_items

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(getLayout(), container, false)
        val btn = view.findViewById<FloatingActionButton>(R.id.new_item)
        btn.setOnClickListener {
            val items = arrayOf(getString(R.string.todos), getString(R.string.notes))
            val builder = AlertDialog.Builder(this@ItemsFragment.context!!)
                    .setTitle(R.string.choose_a_type)
                    .setItems(items, { _, which ->
                        when (which) {
                            0 -> openCreateTodo()
                            1 -> openCreateNote()
                            else -> Log.e(logTag, "Unknown option selected [ $which ]")
                        }
                    })
            builder.show()
        }
        return view
    }

    private fun openCreateNote() {
        val intent = Intent(context, NoteActivity::class.java)
        intent.putExtra(MODE.EXTRAS_KEY, MODE.CREATE.mode)
        startActivity(intent)
    }

    private fun openCreateTodo() {
        val intent = Intent(context, TodoActivity::class.java)
        intent.putExtra(MODE.EXTRAS_KEY, MODE.CREATE.mode)
        startActivity(intent)

    }
}