package com.keepcoding.waiter.model

import org.json.JSONArray

object Tables {
    var tablesFeeded: Boolean = false

    private var tables: MutableList<Table> = mutableListOf<Table>()

    val count
        get() = tables.size

    operator fun get(i: Int) = tables[i]

    fun toArray() = tables.toTypedArray()

    fun feedTablesList(tablesJSONArray: JSONArray) {
        for (i in 0..(tablesJSONArray.length()) - 1) {
            val t: Table = Table(tablesJSONArray[i].toString(), mutableListOf<Item>())

            tables.add(t)
        }

        tablesFeeded = true
    }
}