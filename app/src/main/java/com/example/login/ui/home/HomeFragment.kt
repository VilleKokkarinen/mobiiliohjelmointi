package com.example.login.ui.home

import android.app.Activity
import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.login.R
import com.example.login.basket.BasketFragment
import com.example.login.databinding.FragmentHomeBinding
import kotlin.properties.Delegates

class HomeFragment : Fragment(), View.OnTouchListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    public lateinit var FL : FrameLayout

    private var mLastPosY : Float = 0f

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val button = root.findViewById<Button>(R.id.btnBasket)
        val co_button = root.findViewById<Button>(R.id.btnCheckout)
        FL = root.findViewById<FrameLayout>(R.id.basketContainer)

        var trans = (context as MainActivity).supportFragmentManager.beginTransaction()
        trans.add(FL.id, BasketFragment(), "BasketFragment")
        trans.commit()

        button.setOnClickListener{
            if(FL.translationY + 250 >= FL.height){
                var floatvalue : Float = 5f
                var interpolator = OvershootInterpolator(5f)
                FL.animate().setInterpolator(interpolator).translationYBy(-250f).setDuration(500)
            }
        }

        co_button.setOnClickListener{
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_purchaseFragment);
        }

        FL.setOnTouchListener(this)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event != null) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> { mLastPosY = event.getY(); return true }
                MotionEvent.ACTION_MOVE -> {
                    var currentPosition = event.getY()
                    var deltaY = mLastPosY-currentPosition

                    var transY = v?.translationY

                    if(transY != null){
                        transY -= deltaY
                    }

                    if (transY != null) {
                        if(transY < 0){
                            transY = 0f
                        }
                    }

                    if (v != null) {
                        if (transY != null) {
                            v.translationY = transY
                        }
                    }

                    return true
                }
            }
            return true
        }
        else
            return true
    }
}