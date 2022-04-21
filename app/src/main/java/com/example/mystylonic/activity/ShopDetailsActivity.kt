package com.example.mystylonic.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.mystylonic.R
import com.google.android.material.textfield.TextInputLayout

class ShopDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_details)

        val titleShop : TextView = findViewById(R.id.title) //declare
        val shopDet : TextView = findViewById(R.id.det_detail)
        val shopImage : ImageView = findViewById(R.id.det_image)
        val shopsize : TextView = findViewById(R.id.det_size)
        val shopPrice : TextView = findViewById(R.id.det_price)
        val btnaddcart : Button =findViewById(R.id.btn_add_to_cart)
        val btnbuy : Button =findViewById(R.id.btn_buy)

        val bundle : Bundle? = intent.extras

        val title = bundle!!.getString("title")
        val imageId = bundle!!.getInt("imageId")
        val size = bundle!!.getString("size")
        val price = bundle!!.getString("price")
        val shopDetail = bundle!!.getString("shopDetails")

        titleShop.text = title
        shopImage.setImageResource(imageId)
        shopDet.text = shopDetail
        shopsize.text = size
        shopPrice.text = price

        btnaddcart.setOnClickListener{
            val intent = Intent(this, add_cart_activity::class.java)
            startActivity(intent)
            finish()
        }
        btnbuy.setOnClickListener{
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}