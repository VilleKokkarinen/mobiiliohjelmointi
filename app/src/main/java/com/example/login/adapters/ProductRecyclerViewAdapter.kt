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
import com.example.login.placeholder.BasketContent

class ProductRecyclerViewAdapter(
    private val values: MutableList<ProductData>
) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        DownloadImageTask(holder.image).execute(item.image)

        holder.title.text = item.name
        holder.description.text = item.details
        holder.description.movementMethod = ScrollingMovementMethod()
        holder.description.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> holder.itemView.parent.requestDisallowInterceptTouchEvent(true)
            }

            v?.onTouchEvent(event) ?: true
        }
        holder.title.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> holder.itemView.parent.requestDisallowInterceptTouchEvent(true)
            }

            v?.onTouchEvent(event) ?: true
        }

        holder.btn.setOnClickListener{
            if(item != null){
                //BasketFragment.
                BasketContent.increaseAmount(item)
            }

        }

        //Enabling scrolling on TextViews.
        holder.description.movementMethod = ScrollingMovementMethod()
        holder.title.movementMethod = ScrollingMovementMethod()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.productImage
        val title: TextView = binding.title
        val description: TextView = binding.description
        val btn: Button = binding.btnAddToBasket

        override fun toString(): String {
            return super.toString() + " '" + description.text + "'"
        }
    }


}