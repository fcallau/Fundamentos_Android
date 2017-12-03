package com.keepcoding.waiter.model

object InfoDownloaded {

    lateinit var tablesDownLoaded: MutableList<Table>

    // var aMutableList: MutableList<Item> = mutableListOf<Item>(Items.get(0))

    fun setTablesDownLoaded() {
        tablesDownLoaded = mutableListOf<Table>()
    }

    /*val count
        get() = tablesDownloaded.size

    operator fun get(i: Int) = tablesDownloaded[i]

    fun toArray() = tablesDownloaded.toTypedArray()*/
}