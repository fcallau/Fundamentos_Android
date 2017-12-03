package com.keepcoding.waiter.fragment

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.keepcoding.waiter.R
import com.keepcoding.waiter.model.Items
import com.keepcoding.waiter.model.Tables

class ItemDetailFragment : Fragment() {
    companion object {
        val EXTRA_ITEM_INDEX = "EXTRA_ITEM_INDEX"
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun newInstance(itemIndex: Int, tableIndex: Int): ItemDetailFragment {
            val arguments = Bundle()
            val fragment = ItemDetailFragment()

            arguments.putInt(ItemDetailFragment.EXTRA_ITEM_INDEX, itemIndex)
            arguments.putInt(ItemDetailFragment.EXTRA_TABLE_INDEX, tableIndex)
            fragment.arguments = arguments

            return fragment
        }
    }

    lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_item_detail, container, false)
            val itemIndex = arguments.getInt(ItemDetailFragment.EXTRA_ITEM_INDEX, 0)
            val moreInfo = root.findViewById<TextView>(R.id.more_info)
            var allergensInfo = root.findViewById<TextView>(R.id.allergens_info)
            val cancelButton = root.findViewById<Button>(R.id.cancel_button)
            val acceptButton = root.findViewById<Button>(R.id.accept_button)

            // Show more info about item
            if (Items.getItemsList().get(itemIndex).moreInfo != "") {
                moreInfo.text = Items.getItemsList().get(itemIndex).moreInfo
                moreInfo.visibility = View.VISIBLE
            } else {
                moreInfo.visibility = View.GONE
            }

            // Show the allergens if the item has
            if (Items.getItemsList().get(itemIndex).allergens.size > 0) {
                allergensInfo.text = getString(R.string.allergens_text_of_the_detail_item)
                allergensInfo.visibility = View.VISIBLE

                // Format allergens info
                for (i in Items.getItemsList().get(itemIndex).allergens.indices){
                    // If the last allergen
                    if (i == Items.getItemsList().get(itemIndex).allergens.size - 1) {
                        // If more than one allergen
                        if (Items.getItemsList().get(itemIndex).allergens.size > 1) {
                            allergensInfo.text = allergensInfo.text as String + " and " + Items.getItemsList().get(itemIndex).allergens.get(i) + "."
                        } else {
                            allergensInfo.text = allergensInfo.text as String + " " + Items.getItemsList().get(itemIndex).allergens.get(i) + "."
                        }
                    } else {
                        // If the first allergen (and there are more than one)
                        if (i == 0) {
                            allergensInfo.text = allergensInfo.text as String + " " + Items.getItemsList().get(itemIndex).allergens.get(i)
                        } else {
                            allergensInfo.text = allergensInfo.text as String + ", " + Items.getItemsList().get(itemIndex).allergens.get(i)
                        }
                    }
                }
            } else {
                allergensInfo.visibility = View.GONE
            }

            cancelButton.setOnClickListener { v: View ->
                activity.finish()
            }

            acceptButton.setOnClickListener { v: View ->
                // showModel()

                val variants = root.findViewById<TextView>(R.id.variants_text)

                // Add the item on Table
                val tableIndex:Int = arguments.getInt(ItemDetailFragment.EXTRA_TABLE_INDEX, 0)
                val item = Items.getItemsList().get(itemIndex)

                item.variants = variants.text.toString()

                Tables.get(tableIndex).addItem(item)

                //showModel()

                activity.finish()
            }
        }

        return root
    }

    fun showModel() {
        /*for (i in Items.getItemsList().indices) {
            val item = Items.getItemsList().get(i)

            Log.v("TAG", "Item number ${i}")
            Log.v("TAG", "> idImage    : ${item.idImage.toString()}")
            Log.v("TAG", "> name       : ${item.name}")
            Log.v("TAG", "> price      : ${item.price.toString()}")

            for (j in item.allergens.indices) {
                val allergen = item.allergens.get(j)

                Log.v("TAG", ">>> allergen(${j}): ${allergen}")
            }

            Log.v("TAG", "> moreInfo   : ${item.moreInfo}")
            Log.v("TAG", "> variants   : ${item.variants}")

            Log.v("TAG", "----------------------------------------------------------")
        }*/

        /*Log.v("TAG", moment)

        for (i in 0..Tables.count - 1) {
            Log.v("TAG", "Tables[i].tableItems.count(): ${Tables[i].tableItems.count()}")
            // Log.v("TAG", "i: ${i}")
            Log.v("TAG", "> Tables[i].name: ${Tables[i].name}")

            for (j in 0..(Tables[i].tableItems.count() - 1)) {
                // Log.v("TAG", "Caracola")
                val item = Tables[i].tableItems[j]

                Log.v("TAG", ">> j: ${j}")
                Log.v("TAG", "item.name    : ${item.name}")
                Log.v("TAG", "item.variants: ${item.variants}")
            }
        }*/
    }
}