package com.keepcoding.waiter.model

import android.util.Log
import com.keepcoding.waiter.R
import org.json.JSONArray
import org.json.JSONObject

object Items {
    private var items: MutableList<Item> = mutableListOf<Item>()

    val count
        get() = items.size

    operator fun get(i: Int) = items[i]

    fun getItemsList() = items

    fun toArray() = items.toTypedArray()

    fun feedItemsList(itemsJSONArray: JSONArray) {
        for (i in 0..(itemsJSONArray.length()) - 1) {
            val nameImage = (itemsJSONArray[i] as JSONObject).get("idImage") as String

            val idImage = when (nameImage) {
                "img_01" -> R.drawable.img_01
                "img_02" -> R.drawable.img_02
                "img_03" -> R.drawable.img_03
                "img_04" -> R.drawable.img_04
                "img_05" -> R.drawable.img_05
                "img_06" -> R.drawable.img_06
                "img_07" -> R.drawable.img_07
                "img_08" -> R.drawable.img_08
                "img_09" -> R.drawable.img_09
                "img_10" -> R.drawable.img_10
                else -> R.drawable.img_01
            }

            val name = (itemsJSONArray[i] as JSONObject).get("name") as String
            val price = ((itemsJSONArray[i] as JSONObject).get("price") as String).toFloat()
            val allergens = (itemsJSONArray[i] as JSONObject).getJSONArray("allergens")
            val allergensList: MutableList<Allergen> = mutableListOf<Allergen>()

            for (j in 0..(allergens.length() - 1)) {
                allergensList.add(Allergen.valueOf(allergens[j] as String))
            }

            val moreInfo = (itemsJSONArray[i] as JSONObject).get("moreInfo") as String
            val item: Item = Item(idImage, name, price, allergensList, moreInfo, "")

            items.add(item)
        }
    }
}