package com.example.root.roomlocalproduck.update

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.root.roomlocalproduck.MainActivity
import com.example.root.roomlocalproduck.R
import com.example.root.roomlocalproduck.database.AppDatabase
import com.example.root.roomlocalproduck.database.Produk
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.startActivity

class UpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val data = intent.getSerializableExtra("data") as Produk

        showupdate(data)

        btnsaveupdate.setOnClickListener {

            var datas = Produk()
            datas.uid = data.uid
            datas.nama = upnama.text.toString()
            datas.harga = upharga.text.toString().toDouble()
            datas.stok = upstock.text.toString().toInt()

            async(UI){

               val check =  bg {
                    AppDatabase.getDatabase(this@UpdateActivity).produckdao().update(datas)
                }

            }
            startActivity<MainActivity>()
        }


        btnupcancel.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun showupdate(data: Produk){

        upnama.setText(data.nama).toString()
        upharga.setText(data.harga.toString())
        upstock.setText(data.stok.toString())

    }
}

