package com.example.login.ui.orders

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.adapters.OrderRecyclerViewAdapter
import com.example.login.R
import com.example.login.adapters.ProductRecyclerViewAdapter
import com.example.login.data.OrderData
import com.example.login.data.ProductData
import com.example.login.placeholder.PlaceholderContent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class OrderFragment : Fragment() {
    var fragmentView : View? = null
    var firedatabase : FirebaseDatabase? = null
    var OrderList : ArrayList<OrderData> ? = null
    var ref : DatabaseReference? = null

    private lateinit var auth: FirebaseAuth

    var mRecyclerView : RecyclerView? =null

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        fragmentView= LayoutInflater.from(activity).inflate(R.layout.fragment_order_item_list, container, false)

        firedatabase = FirebaseDatabase.getInstance()


        mRecyclerView = fragmentView?.findViewById(R.id.orderList)
        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView?.layoutManager = LinearLayoutManager(context)

        val itemDecoration =
            DividerItemDecoration(mRecyclerView?.getContext(), DividerItemDecoration.VERTICAL)
        val drawable = GradientDrawable(
            GradientDrawable.Orientation.BOTTOM_TOP,
            intArrayOf(0xfff7f7f7.toInt(), 0xfff7f7f7.toInt())
        )
        drawable.setSize(1, 3)
        itemDecoration.setDrawable(drawable)
        mRecyclerView?.addItemDecoration(itemDecoration)

        OrderList = arrayListOf<OrderData>()
        auth = FirebaseAuth.getInstance()

        ref = FirebaseDatabase.getInstance().getReference("orders/"+ (auth.currentUser?.uid).toString()+"/orders")


        ref?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){

                    for (h in p0.children){
                        val bal = h.getValue(OrderData::class.java)
                        OrderList?.add(bal!!)
                    }

                    val adapter = OrderRecyclerViewAdapter(OrderList!!)
                    mRecyclerView?.adapter = adapter

                }

            }
        })

        return  fragmentView
    }
}