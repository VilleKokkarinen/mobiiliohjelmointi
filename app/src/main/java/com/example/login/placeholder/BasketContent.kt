package com.example.login.placeholder

import com.example.login.adapters.BasketRecyclerViewAdapter
import com.example.login.basket.BasketFragment
import com.example.login.data.ProductData
import java.util.ArrayList
import java.util.HashMap

object BasketContent {
    val ITEMS: MutableList<BasketContent> = ArrayList()

    val ITEM_MAP: MutableMap<String, BasketContent> = HashMap()

    fun increaseAmount(item: ProductData) {
        var basketitem: BasketContent? = ITEMS.find { it.content.id == item.id }
        var basketitem2: BasketContent? = ITEM_MAP[basketitem?.ListIndex]

        if(basketitem == null){
            basketitem = createBasketItem(ITEMS.lastIndex +1, item)
            ITEMS.add(basketitem)
        }
        else{
            basketitem.amount ++
            BasketFragment().redraw()
        }

        if(basketitem2 == null){
            basketitem2 = createBasketItem(ITEMS.lastIndex +1, item)
            ITEM_MAP.put(basketitem.ListIndex, basketitem2)
        }
        else{
            basketitem2.amount ++
            BasketFragment().redraw()
        }
    }

    fun decreaseAmount(item: ProductData) {
        var basketitem: BasketContent? = ITEMS.find { it.content.id == item.id }
        var basketitem2: BasketContent? = ITEM_MAP[basketitem?.ListIndex]

        if(basketitem != null && basketitem.amount <= 0){
            ITEMS.remove(basketitem)
        }
        else if(basketitem != null && basketitem.amount > 0){
            basketitem.amount --
            BasketFragment().redraw()
        }

        if(basketitem2 != null && basketitem2.amount <= 0){
            ITEM_MAP.remove(basketitem2.ListIndex, basketitem2)
        }
        else if(basketitem2 != null && basketitem2.amount > 0){
            basketitem2.amount --
            BasketFragment().redraw()
        }
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