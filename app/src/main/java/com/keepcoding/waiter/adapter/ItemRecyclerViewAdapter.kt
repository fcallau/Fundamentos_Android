package com.keepcoding.waiter.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.keepcoding.waiter.R
import com.keepcoding.waiter.model.Allergens
import com.keepcoding.waiter.model.Item

class ItemRecyclerViewAdapter(val items: List<Item>) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder>() {
    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_item, parent, false)
        view.setOnClickListener(onClickListener)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        if (items != null) {
            holder?.bindItem(items[position], position)
        }
    }

    override fun getItemCount() = items.size ?: 0

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.item_image)
        val name = itemView.findViewById<TextView>(R.id.item_name)
        val allergenText = itemView.findViewById<TextView>(R.id.allergen_text)
        val celleryAllergen = itemView.findViewById<ImageView>(R.id.cellery_allergen)
        val cerealsContainingGlutenAllergenAllergen = itemView.findViewById<ImageView>(R.id.cereals_containing_gluten_allergen)
        val crustaceansAllergen = itemView.findViewById<ImageView>(R.id.crustaceans_allergen)
        val eggsAllergen = itemView.findViewById<ImageView>(R.id.eggs_allergen)
        val fishAllergen = itemView.findViewById<ImageView>(R.id.fish_allergen)
        val lupinAllergen = itemView.findViewById<ImageView>(R.id.lupin_allergen)
        val milkAllergen = itemView.findViewById<ImageView>(R.id.milk_allergen)
        val molluscsAllergen = itemView.findViewById<ImageView>(R.id.molluscs_allergen)
        val mustardAllergen = itemView.findViewById<ImageView>(R.id.mustard_allergen)
        val nutsAllergen = itemView.findViewById<ImageView>(R.id.nuts_allergen)
        val peanutsAllergen = itemView.findViewById<ImageView>(R.id.peanuts_allergen)
        val sesameSeedsAllergen = itemView.findViewById<ImageView>(R.id.sesame_seeds_allergen)
        val soyaAllergen = itemView.findViewById<ImageView>(R.id.soya_allergen)
        val sulphurDioxideAllergen = itemView.findViewById<ImageView>(R.id.sulphur_dioxide_allergen)
        val price = itemView.findViewById<TextView>(R.id.item_price)

        // Images of every allergen
        val allergenImageViews: Array<ImageView> = arrayOf(
                celleryAllergen,
                cerealsContainingGlutenAllergenAllergen,
                crustaceansAllergen,
                eggsAllergen,
                fishAllergen,
                lupinAllergen,
                milkAllergen,
                molluscsAllergen,
                mustardAllergen,
                nutsAllergen,
                peanutsAllergen,
                sesameSeedsAllergen,
                soyaAllergen,
                sulphurDioxideAllergen)

        fun bindItem(item: Item, position: Int) {
            val context = itemView.context

            image.setImageResource(item.idImage)
            name.text = item.name

            for (allergenImageView in allergenImageViews) {
                allergenImageView.setImageResource(android.R.color.transparent)
            }

            if (item.allergens.count() > 0) {
                allergenText.text = "Allergens: "
            } else {
                allergenText.text = ""
            }

            for (allergen in item.allergens) {
                val position = Allergens.getPosition(allergen)

                if (position != null) {
                    allergenImageViews.get(position).setImageResource(Allergens.get(position).idImage)
                }
            }

            price.text = item.price.toString() + " €"
        }
    }
}