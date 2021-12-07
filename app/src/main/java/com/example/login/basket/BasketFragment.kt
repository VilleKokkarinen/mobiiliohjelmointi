package com.example.login.basket

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.login.R
import com.example.login.adapters.BasketRecyclerViewAdapter
import com.example.login.adapters.ProductRecyclerViewAdapter
import com.example.login.data.ProductData
import com.example.login.placeholder.BasketContent
import com.example.login.ui.orders.OrderFragment
import com.google.firebase.database.*


class BasketFragment : Fragment() {
        var fragmentView : View? = null
        var mRecyclerView : RecyclerView? =null

        public fun redraw(){
            mRecyclerView?.adapter?.notifyDataSetChanged()
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

        ): View? {
            fragmentView= LayoutInflater.from(activity).inflate(R.layout.fragment_basket, container, false)

            mRecyclerView = fragmentView?.findViewById(R.id.basketlist)
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

            val adapter = BasketRecyclerViewAdapter(BasketContent.ITEMS)
            mRecyclerView?.adapter = adapter


            return  fragmentView
        }
    }