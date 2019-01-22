package com.example.root.roomlocalproduck.database

import android.arch.persistence.room.*

@Dao
interface Produckdao {

    @Query("SELECT * FROM produk")
    fun getAll(): List<Produk>

    @Query("SELECT * FROM produk WHERE uid = :produkIds")
    fun loadAllByIds(produkIds: Int): Produk

    @Insert
    fun insertAll(vararg produk: Produk)

    @Update
    fun update(vararg  produks: Produk)

    @Delete
    fun delete(produk: Produk)

}