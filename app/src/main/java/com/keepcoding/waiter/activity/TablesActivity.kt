package com.keepcoding.waiter.activity

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ViewSwitcher
import com.keepcoding.waiter.R
import com.keepcoding.waiter.model.Items
import com.keepcoding.waiter.model.Table
import com.keepcoding.waiter.model.Tables
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.json.JSONObject
import java.net.URL
import java.util.*

class TablesActivity : AppCompatActivity() {
    enum class VIEW_INDEX(val index: Int) {
        LOADING(0),
        TABLES(1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        val viewSwitcher = this.findViewById<ViewSwitcher>(R.id.view_switcher)
        val myContext = this
        val list = this.findViewById<ListView>(R.id.tables_list)

        async(UI) {
            val newInfo: Deferred<Int> = bg {
                downloadInfo()
            }

            val downloadedInfo = newInfo.await()

            viewSwitcher.displayedChild = VIEW_INDEX.TABLES.index

            if (downloadedInfo != null) {
                val adapter = ArrayAdapter<Table>(myContext, android.R.layout.simple_list_item_1, Tables.toArray())
                list.adapter = adapter
            } else {
                AlertDialog.Builder(myContext)
                        .setTitle("Error")
                        .setMessage("Some problems trying to connect to the server. Try again later.")
                        .setNegativeButton("Exit", { _, _ -> myContext.finish() })
                        .show()
            }
        }

        list.setOnItemClickListener { parent, view, position, id ->
            // Navigate to TableActivity using position parameter
            startActivity(TableActivity.intent(this, position))
        }

    }

    fun downloadInfo(): Int {
        // 2 tables and 3 items
        // val url = URL("http://www.mocky.io/v2/5a208f3c310000ad40c0b2ea")

        // 6 tables and 11 items
        val url = URL("http://www.mocky.io/v2/5a23f2902e0000510883bf0f")

        val jsonString = Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next()
        val jsonRoot = JSONObject(jsonString)
        val tablesArray = jsonRoot.getJSONArray("tables")
        val itemsArray = jsonRoot.getJSONArray("items")

        if (!Tables.tablesFeeded) {
            Tables.feedTablesList(tablesArray)
            Items.feedItemsList(itemsArray)
        }

        return 99
    }
}
