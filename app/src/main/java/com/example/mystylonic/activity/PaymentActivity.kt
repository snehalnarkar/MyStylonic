package com.example.mystylonic.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.mystylonic.R

class PaymentActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val confirm_payment_btn :Button =findViewById(R.id.confirm_payment_btn)

        val backarrow : ImageView = findViewById(R.id.backarrow)


        backarrow.setOnClickListener {
            val intent = Intent(this, add_cart_activity::class.java)
            startActivity(intent)
            finish()
        }

        confirm_payment_btn.setOnClickListener{

        Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()

        }


    }
}