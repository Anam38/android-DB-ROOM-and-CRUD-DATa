package com.example.root.roomlocalproduck

import com.example.root.roomlocalproduck.database.Produk

interface OnItemClickListener {

    fun onItemClick(item: Produk , action : String)
}