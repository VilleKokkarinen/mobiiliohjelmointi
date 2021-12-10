package com.example.login.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.AbsSavedState
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.login.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LogoutActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailEt: EditText
    @Override
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContenView(R. layout.fragment_settings)

        emailEt = findViewById(R.id.email_edt_text)

        auth = FirebaseAuth.getInstance()

    }

    @Override
    override fun onStart() {
        super.onStart();


    }
    fun  logout() {
        auth.signOut();

    }

    private fun setContenView(fragmentSettings: Int) {

    }

}