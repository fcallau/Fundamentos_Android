package com.keepcoding.waiter.model

data class Table (val name: String, var tableItems: MutableList<Item>) {
    override fun toString() = name

    val count
        get() = tableItems.size

    operator fun get(i: Int) = tableItems[i]

    /*fun itemComparator(item1: Item, item2: Item): Int {
        return 0
    }*/

    // fun toArray() = tableItems.toTypedArray()

    fun toArray(): Array<Item> {
        val listOrderedByItemName: List<Item> = tableItems.sortedWith(Item)

        return listOrderedByItemName.toTypedArray()
    }

    fun addItem(item: Item) {
        tableItems.add(item)
    }
}