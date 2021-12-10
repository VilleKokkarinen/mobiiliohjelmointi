package com.example.login.ui.purchase

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.login.R
import com.example.login.data.OrderData
import com.example.login.data.OrderRowData
import com.example.login.placeholder.BasketContent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.ZoneOffset
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService




class PurchaseFragment : Fragment() {
    var deliveryRadioGroup: RadioGroup? = null
    lateinit var deliveryRadioButton: RadioButton

    var paymentRadioGroup: RadioGroup? = null
    lateinit var paymentRadioButton: RadioButton

    private lateinit var confirmButton: Button

    private lateinit var Name: TextView
    private lateinit var LastName: TextView
    private lateinit var Email: TextView
    private lateinit var Phone: TextView
    private lateinit var Address: TextView
    private lateinit var Address2: TextView
    private lateinit var City: TextView
    private lateinit var State: TextView
    private lateinit var Zip: TextView

    lateinit var mainHandler: Handler

    var firedatabase : FirebaseDatabase? = null

    private lateinit var auth: FirebaseAuth

    private val BtnEnabledTask = object : Runnable {
        override fun run() {
            Checkfields()
            mainHandler.postDelayed(this, 1000)
        }
    }

    fun Checkfields(){
        if( Name.text.toString().equals("") ||
            LastName.text.toString().equals("") ||
            Email.text.toString().equals("") ||
            Phone.text.toString().equals("") ||
            Address.text.toString().equals("") ||
            City.text.toString().equals("") ||
            State.text.toString().equals("") ||
            deliveryRadioGroup!!.getCheckedRadioButtonId() == -1 ||
            paymentRadioGroup!!.getCheckedRadioButtonId() == -1 ||
            Zip.text.toString().equals("") && Zip.text.toString().length == 5
        ){
            confirmButton.isEnabled = false
        }
        else{
            confirmButton.isEnabled = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentView = inflater.inflate(R.layout.fragment_purchase, container, false)


        confirmButton = fragmentView.findViewById(R.id.co_confirmbutton)
        confirmButton.isEnabled = false

        Name = fragmentView.findViewById(R.id.co_firstName)
        LastName = fragmentView.findViewById(R.id.co_lastName)
        Email = fragmentView.findViewById(R.id.co_email)
        Phone = fragmentView.findViewById(R.id.co_phone)
        Address = fragmentView.findViewById(R.id.co_address)
        Address2 = fragmentView.findViewById(R.id.co_address2)
        City = fragmentView.findViewById(R.id.co_city)
        State = fragmentView.findViewById(R.id.co_state)
        Zip = fragmentView.findViewById(R.id.co_zip)

        deliveryRadioGroup = fragmentView.findViewById(R.id.co_deliverygroup)
        paymentRadioGroup = fragmentView.findViewById(R.id.co_paymentgroup)

        firedatabase = FirebaseDatabase.getInstance()

        auth = FirebaseAuth.getInstance()

        confirmButton.setOnClickListener{
            val selectedDeliveryOption: Int = deliveryRadioGroup!!.checkedRadioButtonId
            val selectedPaymentOption: Int = paymentRadioGroup!!.checkedRadioButtonId

            val selectedDeliveryOptionString = resources.getResourceEntryName(selectedDeliveryOption)
            val selectedPaymentOptionString = resources.getResourceEntryName(selectedPaymentOption)

            val now = LocalDateTime.now(ZoneOffset.UTC)
            val milliseconds = now.atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()

            val uniqueKey: String? = FirebaseDatabase.getInstance().getReference().child("orders").child(auth.uid.toString()).child("orders").push().getKey()

            var selPayment = ""
            var selDelivery = ""

            when (selectedDeliveryOptionString) {
                "co_delivery_1" -> selDelivery = "Nouto Postista"
                "co_delivery_2"-> selDelivery = "Matkahuolto, bussipaketti"
                "co_delivery_3"-> selDelivery = "Posti kotiinkuljetus"
                "co_delivery_4"-> selDelivery = "Matkahuolto kotiinkuljetus"
            }
            when (selectedPaymentOptionString) {
                "co_payment_1" -> selPayment = "Ennakkomaksu"
                "co_payment_2"  -> selPayment = "Jousto"
                "co_payment_3"  -> selPayment = "Postiennakko"
                "co_payment_4"  -> selPayment = "Klarna"
            }


            val order = OrderData(
                Address.text.toString(),
                Address2.text.toString(),
                City.text.toString(),
                uniqueKey,
                selDelivery,
                Name.text.toString(),
                LastName.text.toString(),
                milliseconds,
                selPayment,
                Phone.text.toString(),
                State.text.toString(),
                auth.uid,
                Zip.text.toString()
                )

            FirebaseDatabase.getInstance().getReference().child("orders").child(auth.uid.toString()).child("orders").child(uniqueKey.toString()).setValue(order)

            for(basketItem in BasketContent.ITEMS){
                val uniqueKeyRow: String? = FirebaseDatabase.getInstance().getReference().child("orderRows").child(uniqueKey.toString()).child("orderItems").push().getKey()

                val orderRow = OrderRowData(false,uniqueKey,auth.uid,basketItem.content)

                FirebaseDatabase.getInstance().getReference().child("orderRows").child(uniqueKey.toString()).child("orderItems").child(uniqueKeyRow.toString()).setValue(orderRow)
            }

            val dialogBuilder = AlertDialog.Builder(requireActivity())
            dialogBuilder.setMessage("Order placed!")
                // if the dialog is cancelable
                .setCancelable(false)
                .setPositiveButton("Ok", DialogInterface.OnClickListener {
                        dialog, id ->
                    dialog.dismiss()

                })

            val alert = dialogBuilder.create()
            alert.setTitle("Order placed!")
            alert.show()
        }

        mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(BtnEnabledTask)

        return fragmentView
    }
}