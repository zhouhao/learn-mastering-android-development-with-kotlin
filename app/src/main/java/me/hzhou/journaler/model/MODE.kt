package me.hzhou.journaler.model

/**
 * Created by hzhou on 4/1/18.
 * Email: hzhou.me@gmail.com
 */
enum class MODE(val mode: Int) {
    CREATE(0),
    EDIT(1),
    VIEW(2);

    companion object {
        val EXTRAS_KEY = "MODE"

        fun getByValue(value: Int): MODE {
            values().forEach { item ->
                if (item.mode == value) {
                    return item
                }
            }
            return VIEW
        }
    }
}