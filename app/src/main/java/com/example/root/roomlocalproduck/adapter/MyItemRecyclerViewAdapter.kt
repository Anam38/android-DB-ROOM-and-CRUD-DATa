package com.example.root.roomlocalproduck.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.root.roomlocalproduck.OnItemClickListener
import com.example.root.roomlocalproduck.R

import com.example.root.roomlocalproduck.database.Produk
import kotlinx.android.synthetic.main.fragment_item.view.*

class MyItemRecyclerViewAdapter(
    private val mValues: List<Produk>, val listener: OnItemClickListener
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.nama
        holder.mContentView.text = item.harga.toString()
        holder.mstock.text = item.stok.toString()

        holder.mbtup.setOnClickListener {

            listener.onItemClick(item,"update")
        }

        holder.mbtndel.setOnClickListener {

            listener.onItemClick(item,"delete")
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val mstock: TextView = mView.stocks
        val mbtndel: Button = mView.btndelete
        val mbtup : Button = mView.btnupdate

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
