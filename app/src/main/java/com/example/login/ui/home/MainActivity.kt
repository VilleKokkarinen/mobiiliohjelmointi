package com.example.login.ui.home

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.login.R
import com.example.login.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity(), Parcelable {

    private lateinit var binding: ActivityMainBinding

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<BottomNavigationView
                >(R.id.bottom_navigation_view)
        val navController = findNavController(R.id.nav_fragment)
        bottomNavigationView.setupWithNavController(navController
        )
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}