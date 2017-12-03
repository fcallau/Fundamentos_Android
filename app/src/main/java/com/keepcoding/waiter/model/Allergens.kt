package com.keepcoding.waiter.model

import android.widget.ImageView
import com.keepcoding.waiter.R

object Allergens {
    private var allergens: List<InfoAllergen> = listOf(
            InfoAllergen(R.drawable.allergen_01, Allergen.CELERY),
            InfoAllergen(R.drawable.allergen_02, Allergen.CEREALS_CONTAINING_GLUTEN),
            InfoAllergen(R.drawable.allergen_03, Allergen.CRUSTACEANS),
            InfoAllergen(R.drawable.allergen_04, Allergen.EGGS),
            InfoAllergen(R.drawable.allergen_05, Allergen.FISH),
            InfoAllergen(R.drawable.allergen_06, Allergen.LUPIN),
            InfoAllergen(R.drawable.allergen_07, Allergen.MILK),
            InfoAllergen(R.drawable.allergen_08, Allergen.MOLLUSCS),
            InfoAllergen(R.drawable.allergen_09, Allergen.MUSTARD),
            InfoAllergen(R.drawable.allergen_10, Allergen.NUTS),
            InfoAllergen(R.drawable.allergen_11, Allergen.PEANUTS),
            InfoAllergen(R.drawable.allergen_12, Allergen.SESAME_SEEDS),
            InfoAllergen(R.drawable.allergen_13, Allergen.SOYA),
            InfoAllergen(R.drawable.allergen_14, Allergen.SULPHUR_DIOXIDE)
    )

    val count
        get() = allergens.size

    operator fun get(i: Int) = allergens[i]

    fun getAllergensList() = allergens

    fun toArray() = allergens.toTypedArray()

    fun getPosition(allergen: Allergen): Int? {
        var position: Int? = null

        val list = getAllergensList()

        for (i in list.indices) {
            if (allergen == list[i].name) {
                position = i
                break
            }
        }

        return position
    }
}