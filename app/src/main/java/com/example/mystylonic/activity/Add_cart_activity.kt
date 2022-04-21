package com.example.mystylonic.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.mystylonic.R

class add_cart_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cart)
        val btn_checkout:Button =findViewById(R.id.btn_checkout)
        val backarrow : ImageView = findViewById(R.id.backarrow)


        backarrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_checkout.setOnClickListener{
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}