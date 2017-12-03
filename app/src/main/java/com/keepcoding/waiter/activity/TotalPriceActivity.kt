package com.keepcoding.waiter.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.keepcoding.waiter.R
import com.keepcoding.waiter.fragment.TableListFragment
import com.keepcoding.waiter.model.Item
import com.keepcoding.waiter.model.Tables

class TotalPriceActivity : AppCompatActivity() {
    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, tableIndex: Int) : Intent {
            val intent = Intent(context, TotalPriceActivity::class.java)

            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_price)

        supportActionBar?.title = Tables.get(intent.getIntExtra(TableActivity.EXTRA_TABLE_INDEX, 0)).name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val totalPrice = this.findViewById<TextView>(R.id.total_price)

        // The table index is received
        val tableIndex = intent.getIntExtra(TotalPriceActivity.EXTRA_TABLE_INDEX, 0)

        val totalItems = Tables.get(tableIndex).tableItems.count()
        var totalPriceItems: Float = 0f

        if (totalItems > 0) {
            for (i in 0..totalItems - 1) {
                totalPriceItems = totalPriceItems + Tables.get(tableIndex).tableItems.get(i).price
            }

            totalPrice.text = totalPriceItems.toString() + " €"
        } else {
           totalPrice.text = "0 €"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()

        return true
    }
}