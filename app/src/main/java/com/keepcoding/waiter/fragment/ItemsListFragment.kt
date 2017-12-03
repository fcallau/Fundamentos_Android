package com.keepcoding.waiter.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keepcoding.waiter.R
import com.keepcoding.waiter.activity.ItemDetailActivity
import com.keepcoding.waiter.adapter.ItemRecyclerViewAdapter
import com.keepcoding.waiter.model.Items

class ItemsListFragment : Fragment() {
    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun newInstance(tableIndex: Int): ItemsListFragment {
            val arguments = Bundle()
            val fragment = ItemsListFragment()

            arguments.putInt(EXTRA_TABLE_INDEX, tableIndex)
            fragment.arguments = arguments

            return fragment
        }
    }

    lateinit var root: View
    lateinit var itemsList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_items_list, container, false)
            itemsList = root.findViewById(R.id.items_list)
            itemsList.layoutManager = GridLayoutManager(activity, 1)
            itemsList.itemAnimator = DefaultItemAnimator()

            val adapter = ItemRecyclerViewAdapter(Items.getItemsList())
            itemsList.adapter = adapter

            adapter.onClickListener = View.OnClickListener { v: View ->
                val position = itemsList.getChildAdapterPosition(v)
                val tableIndex = arguments.getInt(TableListFragment.EXTRA_TABLE_INDEX, 0)

                // Start ItemDetailActivity
                startActivity(ItemDetailActivity.intent(activity, position, tableIndex))
            }
        }

        return root
    }
}