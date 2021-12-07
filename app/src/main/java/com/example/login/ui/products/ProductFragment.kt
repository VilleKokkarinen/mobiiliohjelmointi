package com.example.login.ui.products

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.login.R
import com.example.login.adapters.ProductRecyclerViewAdapter
import com.example.login.data.ProductData
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener

import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase


class ProductFragment : Fragment() {
    var fragmentView : View? = null
    var firedatabase : FirebaseDatabase? = null
    var ProductList : ArrayList<ProductData> ? = null
    var ref : DatabaseReference? = null

    var mRecyclerView : RecyclerView? =null

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        fragmentView= LayoutInflater.from(activity).inflate(R.layout.fragment_product_list, container, false)

        firedatabase = FirebaseDatabase.getInstance()


        mRecyclerView = fragmentView?.findViewById(R.id.list)
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

        ProductList = arrayListOf<ProductData>()
        ref = FirebaseDatabase.getInstance().getReference("products")


        ref?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){

                    for (h in p0.children){
                        val bal = h.getValue(ProductData::class.java)
                        ProductList?.add(bal!!)
                    }

                    val adapter = ProductRecyclerViewAdapter(ProductList!!)
                    mRecyclerView?.adapter = adapter

                }

            }
        })

        return  fragmentView
    }
}