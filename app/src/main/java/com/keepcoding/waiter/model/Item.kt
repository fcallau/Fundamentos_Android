package com.keepcoding.waiter.model

data class Item(val idImage: Int, val name: String, val price: Float, val allergens: List<Allergen>, val moreInfo: String, var variants: String) {
    companion object: Comparator<Item> {
        override fun compare(p0: Item?, p1: Item?): Int {
            if (p0 != null && p1 != null) {
                if (p0.name > p1.name) {
                    return 1
                } else {
                    return -1
                }
            } else {
                return 0
            }
        }
    }

    override fun toString() = name
}