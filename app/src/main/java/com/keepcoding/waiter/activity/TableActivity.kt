package com.keepcoding.waiter.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.keepcoding.waiter.R
import com.keepcoding.waiter.fragment.ItemsListFragment
import com.keepcoding.waiter.fragment.TableListFragment
import com.keepcoding.waiter.model.Tables


class TableActivity : AppCompatActivity() {
    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"
        var showingTableListFragment:Boolean = true

        fun intent(context: Context, tableIndex: Int) : Intent {
            val intent = Intent(context, TableActivity::class.java)

            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)

            showingTableListFragment = true

            return intent
        }
    }

    lateinit var addItemButton: FloatingActionButton

    override fun onBackPressed() {
        super.onBackPressed()

        addItemButton.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater?.inflate(R.menu.calculator, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        supportActionBar?.title = Tables.get(intent.getIntExtra(EXTRA_TABLE_INDEX, 0)).name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addItemButton = findViewById<FloatingActionButton>(R.id.add_item_button)

        if (showingTableListFragment) {
            addItemButton.visibility = View.VISIBLE
        } else {
            addItemButton.visibility = View.INVISIBLE
        }

        // The table index is received
        val tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX, 0)

        if (fragmentManager.findFragmentById(R.id.table_list_fragment) == null) {
            val fragment = TableListFragment.newInstance(tableIndex)

            fragmentManager.beginTransaction()
                    .add(R.id.table_list_fragment, fragment)
                    .commit()
        }

        // Listening the clicks on add_item_button
        findViewById<FloatingActionButton>(R.id.add_item_button).setOnClickListener { v: View ->
            addItemButton.visibility = View.INVISIBLE

            showingTableListFragment = false

            if (fragmentManager.findFragmentById(R.id.items_list_fragment) == null) {
                val fragment = ItemsListFragment.newInstance(tableIndex)

                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.table_list_fragment, fragment)
                        .commit()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_show_total_price) {
            startActivity(TotalPriceActivity.intent(this, intent.getIntExtra(EXTRA_TABLE_INDEX, 0)))
        } else {
            finish()
        }

        // startActivity(TotalPriceActivity.intent(this, intent.getIntExtra(EXTRA_TABLE_INDEX, 0)))


        return true
    }
}