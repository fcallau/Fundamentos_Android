package com.keepcoding.waiter.fragment

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.keepcoding.waiter.R
import com.keepcoding.waiter.activity.TableActivity
import com.keepcoding.waiter.activity.TotalPriceActivity
import com.keepcoding.waiter.model.Item
import com.keepcoding.waiter.model.Tables

class TableListFragment : Fragment() {
    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun newInstance(tableIndex: Int): TableListFragment {
            val arguments = Bundle()
            val fragment = TableListFragment()

            arguments.putInt(EXTRA_TABLE_INDEX, tableIndex)
            fragment.arguments = arguments

            return fragment
        }
    }

    lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_table_list, container, false)

            var list = root.findViewById<ListView>(R.id.table_list)
            val tableIndex = arguments.getInt(EXTRA_TABLE_INDEX, 0)
            val textAddItems = root.findViewById<TextView>(R.id.text_add_items)

            if (Tables.get(tableIndex).count > 0) {
                textAddItems.visibility = View.INVISIBLE
                val adapter = ArrayAdapter<Item>(activity, android.R.layout.simple_list_item_1, Tables.get(tableIndex).toArray())
                val list = root.findViewById<ListView>(R.id.table_list)

                list.adapter = adapter
            } else {
                textAddItems.visibility = View.VISIBLE
            }
        }

        return root
    }
}