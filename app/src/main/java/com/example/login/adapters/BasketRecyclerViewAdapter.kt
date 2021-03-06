package com.example.login.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.login.data.DownloadImageTask
import com.example.login.databinding.FragmentBasketProductBinding
import com.example.login.placeholder.BasketContent

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


        holder.incbtn.setOnClickListener{
            if(item != null){
                BasketContent.increaseAmount(item.content)
                holder.amount.text = item.amount.toString()
            }
        }
        holder.decbtn.setOnClickListener{
            if(item != null){
                BasketContent.decreaseAmount(item.content)
                holder.amount.text = item.amount.toString()
            }
        }
        holder.removebtn.setOnClickListener{
            if(item != null){
                BasketContent.removeItem(item.content)
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentBasketProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.productImage
        val title: TextView = binding.title
        val amount: TextView = binding.amountBasket
        val incbtn: Button = binding.btnIncrease
        val decbtn: Button = binding.btnDecrease
        val removebtn: Button = binding.btnRemoveFromBasket
    }

}