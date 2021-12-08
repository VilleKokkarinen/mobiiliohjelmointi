package com.example.login.basket

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
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
        var layoutmgr: LinearLayoutManager? = null
        var mRecyclerView : RecyclerView? = null

    companion object {
        var adapter: BasketRecyclerViewAdapter? = null
        @JvmName("getAdapter1")
        fun getAdapter(): BasketRecyclerViewAdapter? {
            return adapter
        }
        @JvmName("setAdapter1")
        fun setAdapter(adapter: BasketRecyclerViewAdapter){
            this.adapter = adapter
        }
        fun refresh(){
            adapter?.notifyDataSetChanged()
        }
    }
        public fun redraw(){
            refresh()
        }
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

        ): View? {
            fragmentView= LayoutInflater.from(activity).inflate(R.layout.fragment_basket, container, false)

            mRecyclerView = fragmentView?.findViewById(R.id.basketlist)!!
            mRecyclerView?.setHasFixedSize(true)
            layoutmgr = LinearLayoutManager(context)
            mRecyclerView?.layoutManager = layoutmgr

            val itemDecoration =
                DividerItemDecoration(mRecyclerView?.getContext(), DividerItemDecoration.VERTICAL)
            val drawable = GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP,
                intArrayOf(0xfff7f7f7.toInt(), 0xfff7f7f7.toInt())
            )
            drawable.setSize(1, 3)
            itemDecoration.setDrawable(drawable)
            mRecyclerView?.addItemDecoration(itemDecoration)

            setAdapter(BasketRecyclerViewAdapter(BasketContent.ITEMS))

            mRecyclerView?.adapter = getAdapter()

            return  fragmentView
        }
    }