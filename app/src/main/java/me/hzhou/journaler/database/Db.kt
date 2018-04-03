package me.hzhou.journaler.database

import me.hzhou.journaler.model.Note
import me.hzhou.journaler.model.Todo

/**
 * Created by hzhou on 4/3/18.
 * Email: hzhou.me@gmail.com
 */
object Db {
    private val tag = "Db"
    private val version = 1
    private val name = "students"

    val NOTE = object : Crud<Note> {
        override fun insert(what: Note): Long {
            TODO("not implemented") 
        }

        override fun insert(what: Collection<Note>): List<Long> {
            TODO("not implemented") 
        }

        override fun update(what: Note): Int {
            TODO("not implemented") 
        }

        override fun update(what: Collection<Note>): Int {
            TODO("not implemented") 
        }

        override fun delete(what: Note): Int {
            TODO("not implemented") 
        }

        override fun delete(what: Collection<Note>): Int {
            TODO("not implemented") 
        }

        override fun select(args: Pair<String, String>): List<Note> {
            TODO("not implemented") 
        }

        override fun select(args: Collection<Pair<String, String>>): List<Note> {
            TODO("not implemented") 
        }

        override fun selectAll(): List<Note> {
            TODO("not implemented") 
        }
        // Crud implementations
    }

    val TODO = object : Crud<Todo> {
        override fun insert(what: Todo): Long {
            TODO("not implemented") 
        }

        override fun insert(what: Collection<Todo>): List<Long> {
            TODO("not implemented") 
        }

        override fun update(what: Todo): Int {
            TODO("not implemented") 
        }

        override fun update(what: Collection<Todo>): Int {
            TODO("not implemented") 
        }

        override fun delete(what: Todo): Int {
            TODO("not implemented") 
        }

        override fun delete(what: Collection<Todo>): Int {
            TODO("not implemented") 
        }

        override fun select(args: Pair<String, String>): List<Todo> {
            TODO("not implemented") 
        }

        override fun select(args: Collection<Pair<String, String>>): List<Todo> {
            TODO("not implemented") 
        }

        override fun selectAll(): List<Todo> {
            TODO("not implemented") 
        }
        // Crud implementations
    }
}