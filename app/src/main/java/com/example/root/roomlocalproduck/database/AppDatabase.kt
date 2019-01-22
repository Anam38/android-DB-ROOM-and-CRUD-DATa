package com.example.root.roomlocalproduck.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Produk::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produckdao():Produckdao
    companion object {
        fun getDatabase(context: Context): AppDatabase {

            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "db.produk"
            ).build()

            return db

        }
    }

}