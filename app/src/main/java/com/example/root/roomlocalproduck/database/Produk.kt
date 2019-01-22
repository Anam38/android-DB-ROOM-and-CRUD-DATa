package com.example.root.roomlocalproduck.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class Produk : Serializable {

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
    @ColumnInfo(name = "nama")
    var nama: String? = null
    @ColumnInfo(name = "harga")
    var harga: Double? = null
    @ColumnInfo(name = "stock")
    var stok: Int = 0
}