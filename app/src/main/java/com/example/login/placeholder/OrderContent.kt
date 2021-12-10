package com.example.login.placeholder

import android.util.Log
import com.example.login.data.OrderData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList
import java.util.HashMap

object OrderContent {

    val ITEMS: MutableList<OrderContent> = ArrayList()

    val ITEM_MAP: MutableMap<String, OrderContent> = HashMap()

    var orderList: MutableList<OrderData> = ArrayList<OrderData>()
    private var auth: FirebaseAuth

    init {
        val rootRef = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()
        val orderref = rootRef.child("orders/"+ (auth.currentUser?.uid)+"/orders")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val address = ds.child("address").getValue(String::class.java)
                    val address2 = ds.child("address2").getValue(String::class.java)
                    val city = ds.child("city").getValue(String::class.java)
                    val orderId = ds.child("orderId").getValue(String::class.java)
                    val delivery = ds.child("delivery").getValue(String::class.java)
                    val firstName = ds.child("firstName").getValue(String::class.java)
                    val lastName = ds.child("lastName").getValue(String::class.java)
                    val orderDate = ds.child("orderDate").getValue(Long::class.java)
                    val payment = ds.child("payment").getValue(String::class.java)
                    val phone = ds.child("phone").getValue(String::class.java)
                    val state = ds.child("state").getValue(String::class.java)
                    val uid = ds.child("uid").getValue(String::class.java)
                    val zip = ds.child("zip").getValue(String::class.java)
                    orderList.add(
                        OrderData(address,
                            address2,
                            city,
                            orderId,
                            delivery,
                            firstName,
                            lastName,
                            orderDate,
                            payment,
                            phone,
                            state,
                            uid,
                            zip
                    )
                    )
                }
                for (i in 0 until orderList.size) {
                    addItem(createPlaceholderItem(i,orderList[i]))
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("vk", databaseError.details + " |||| " + databaseError.message)
            }

        }
        orderref.addListenerForSingleValueEvent(eventListener)
    }

    private fun addItem(item: OrderContent) {
        ITEMS.add(item)
        ITEM_MAP.put(item.ListIndex, item)
    }

    private fun createPlaceholderItem(position: Int,data: OrderData): OrderContent {
        return OrderContent(position.toString(),
            OrderData(
               data.address,
                data.address2,
                data.city,
                data.orderId,
                data.delivery,
                data.firstName,
                data.lastName,
                data.orderDate,
                data.payment,
                data.phone,
                data.state,
                data.uid,
                data.zip
            )
        )
    }
    data class OrderContent(val ListIndex: String, val content: OrderData) {
        override fun toString(): String = content.orderId
    }
}