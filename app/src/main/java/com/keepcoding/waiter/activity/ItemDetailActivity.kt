package com.keepcoding.waiter.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import android.widget.ImageView
import com.keepcoding.waiter.R
import com.keepcoding.waiter.fragment.ItemDetailFragment
import com.keepcoding.waiter.model.Items
import com.keepcoding.waiter.model.Tables

class ItemDetailActivity : AppCompatActivity() {
    companion object {
        val EXTRA_ITEM_INDEX = "EXTRA_ITEM_INDEX"
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, itemIndex: Int, tableIndex: Int) : Intent {
            val intent = Intent(context, ItemDetailActivity::class.java)

            intent.putExtra(EXTRA_ITEM_INDEX, itemIndex)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        // Prevent opens the OnScreenKeyboard automatically if there is a focused EditText when the Activity starts
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // The item index is received
        val itemIndex = intent.getIntExtra(ItemDetailActivity.EXTRA_ITEM_INDEX, 0)

        // The table index is received
        val tableIndex = intent.getIntExtra(ItemDetailActivity.EXTRA_TABLE_INDEX, 0)

        supportActionBar?.title = Tables.get(tableIndex).name + " / " + Items.get(itemIndex).name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailItemImage = this.findViewById<ImageView>(R.id.detail_item_image)

        detailItemImage.setImageResource(Items.get(itemIndex).idImage)

        if (fragmentManager.findFragmentById(R.id.item_detail_fragment) == null) {
            // val fragment = ItemDetailFragment.newInstance(tableIndex)
            val fragment = ItemDetailFragment.newInstance(itemIndex, tableIndex)

            fragmentManager.beginTransaction()
                    .add(R.id.item_detail_fragment, fragment)
                    .commit()
        }



    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()

        return true
    }
}