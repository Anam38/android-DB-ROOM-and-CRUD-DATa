package com.example.root.roomlocalproduck

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.root.roomlocalproduck.database.AppDatabase
import com.example.root.roomlocalproduck.database.Produk
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)



        btnsave.setOnClickListener {

            var hgr = harga.text.toString()
            var stk = stock.text.toString()
            var produk = Produk()
            produk.nama = nama.text.toString()
            produk.harga = hgr.toDouble()
            produk.stok = stk.toInt()

            async(UI) {

                bg {
                    AppDatabase.getDatabase(this@AddActivity).produckdao().insertAll(produk)
                }
                startActivity(Intent(this@AddActivity, MainActivity::class.java))


            }
        }


    }
}
