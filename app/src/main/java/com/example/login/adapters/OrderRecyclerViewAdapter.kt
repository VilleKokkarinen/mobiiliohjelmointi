package com.example.login.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.login.data.ProductData

import com.example.login.databinding.FragmentProductBinding
import com.example.login.data.DownloadImageTask
import android.text.method.ScrollingMovementMethod
import android.view.MotionEvent
import android.widget.Button
import com.example.login.basket.BasketFragment
import com.example.login.data.OrderData
import com.example.login.databinding.FragmentOrderItemBinding
import com.example.login.placeholder.BasketContent
import java.util.*

class OrderRecyclerViewAdapter(
    private val values: MutableList<OrderData>
) : RecyclerView.Adapter<OrderRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentOrderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.title.text = item.orderId
        holder.date.text = Date(item.orderDate).toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentOrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.orderId
        val date: TextView = binding.orderDate

        override fun toString(): String {
            return super.toString() + " '" + title.text + "'"
        }
    }


}