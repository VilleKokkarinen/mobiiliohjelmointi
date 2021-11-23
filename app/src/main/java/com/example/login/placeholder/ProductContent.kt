package com.example.login.placeholder

import android.util.Log.d
import com.example.login.data.ProductData
import com.google.firebase.database.*
import java.util.ArrayList
import java.util.HashMap


object ProductContent {
    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<ProductContent> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, ProductContent> = HashMap()

    var productList: MutableList<ProductData> = ArrayList<ProductData>()

    init {
        val rootRef = FirebaseDatabase.getInstance().reference
        val productsref = rootRef.child("products")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val brand = ds.child("name").getValue(String::class.java)
                    val category = ds.child("name").getValue(String::class.java)
                    val details = ds.child("details").getValue(String::class.java)
                    val id = ds.child("id").getValue(String::class.java)
                    val image = ds.child("image").getValue(String::class.java)
                    val inStockAmount = ds.child("inStockAmount").getValue(Int::class.java)
                    val name = ds.child("name").getValue(String::class.java)
                    val price = ds.child("price").getValue(Double::class.java)

                    productList.add(ProductData(brand,category,details,id,image,
                        inStockAmount!!,name, price!!
                    ))
                }
                for (i in 0 until productList.size) {
                    addItem(createPlaceholderItem(i,productList[i]))
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                d("vk", databaseError.details + " |||| " + databaseError.message)
            }

        }
        productsref.addListenerForSingleValueEvent(eventListener)
    }

    private fun addItem(item: ProductContent) {
        ITEMS.add(item)
        ITEM_MAP.put(item.ListIndex, item)
    }

    private fun createPlaceholderItem(position: Int,data: ProductData): ProductContent {
        return ProductContent(position.toString(),
            ProductData(
                data.brand,
                data.category,
                data.details,
                data.id,
                data.image,
                data.inStockAmount,
                data.name,
                data.price
            ))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A placeholder item representing a piece of content.
     */
    data class ProductContent(val ListIndex: String, val content: ProductData) {
        override fun toString(): String = content.name
    }
}