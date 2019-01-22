package com.example.root.roomlocalproduck

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.root.roomlocalproduck.R.attr.content
import com.example.root.roomlocalproduck.adapter.MyItemRecyclerViewAdapter
import com.example.root.roomlocalproduck.database.AppDatabase
import com.example.root.roomlocalproduck.database.Produk
import com.example.root.roomlocalproduck.update.UpdateActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.appcompat.v7.alertDialogLayout
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivity(Intent(this, AddActivity::class.java))
        }

        ambilData()
    }

    private fun ambilData() {
        async(UI) {
            var data = bg {
                AppDatabase.getDatabase(this@MainActivity).produckdao().getAll()
            }
            getData(data.await())

        }

    }

    private fun getData(data: List<Produk>) {

        recyclearview.adapter = MyItemRecyclerViewAdapter(data, object : OnItemClickListener {
            override fun onItemClick(item: Produk, action: String) {
                if (action == "delete") {
                    AlertDialog.Builder(this@MainActivity).apply {
                        setTitle("Hapus Data")
                        setMessage("Apkah anda Yakin Mau menghapus")
                        setPositiveButton("yes") { dialog, which ->

                            var data = Produk()
                            data.uid = item.uid

                            async(UI) {

                                var check = bg {
                                    AppDatabase.getDatabase(this@MainActivity).produckdao().delete(data)
                                }

                                Toast.makeText(this@MainActivity, "Delete berhasil", Toast.LENGTH_SHORT).show()

                                if (check.isCompleted) {

                                    ambilData()

                                }
                            }
                        }
                    }.show()

                } else if(action == "update"){

                    val inten = Intent(this@MainActivity, UpdateActivity::class.java)
                    inten.putExtra("data", item)
                    startActivity(inten)
                }

            }
        })
        recyclearview.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
