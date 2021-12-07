package com.example.login.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.login.data.DownloadImageTask
import com.example.login.data.ProductData

import com.example.login.databinding.FragmentBasketProductBinding
import com.example.login.placeholder.BasketContent
import org.w3c.dom.Text

class BasketRecyclerViewAdapter(
    private val values: List<BasketContent.BasketContent>
) : RecyclerView.Adapter<BasketRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentBasketProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        DownloadImageTask(holder.image).execute(item.content.image)

        holder.title.text = item.content.name
        holder.amount.text = item.amount.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentBasketProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.productImage
        val title: TextView = binding.title
        val amount: TextView = binding.amountBasket
    }

}