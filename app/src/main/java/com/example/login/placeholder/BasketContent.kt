package com.example.login.placeholder

import com.example.login.R
import com.example.login.adapters.BasketRecyclerViewAdapter
import com.example.login.basket.BasketFragment
import com.example.login.data.ProductData
import java.util.ArrayList
import java.util.HashMap

object BasketContent {
    val ITEMS: MutableList<BasketContent> = ArrayList()

    fun increaseAmount(item: ProductData) {
        var basketitem: BasketContent? = ITEMS.find { it.content.id == item.id }

        if(basketitem == null){
            basketitem = createBasketItem(ITEMS.lastIndex, item)
            ITEMS.add(basketitem)
        }
        else{
            basketitem.amount ++
        }

        BasketFragment.refresh()
    }

    fun decreaseAmount(item: ProductData) {
        var basketitem: BasketContent? = ITEMS.find { it.content.id == item.id }

        if(basketitem != null && basketitem.amount <= 1){
            ITEMS.remove(basketitem)
        }
        else if(basketitem != null && basketitem.amount > 1){
            basketitem.amount --
        }


        BasketFragment.refresh()
    }
    fun removeItem(item: ProductData){
        var basketitem: BasketContent? = ITEMS.find { it.content.id == item.id }

        if(basketitem != null){
            ITEMS.remove(basketitem)
        }
        BasketFragment.refresh()
    }

    private fun createBasketItem(position: Int,data: ProductData): BasketContent {
        return BasketContent(position.toString(),
            ProductData(
                data.brand,
                data.category,
                data.details,
                data.id,
                data.image,
                data.inStockAmount,
                data.name,
                data.price
            ), 1)
    }
    data class BasketContent(val ListIndex: String, val content: ProductData, var amount:Int) {
        override fun toString(): String = content.name
    }
}