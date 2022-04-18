package com.example.mystylonic

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_shop_details.*

class ShopDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_details)

        val titleShop : TextView = findViewById(R.id.title) //declare
        val shopDet : TextView = findViewById(R.id.det_detail)
        val shopImage : ImageView = findViewById(R.id.det_image)
        val shopPrice : TextView = findViewById(R.id.det_price)
        val btnaddcart : Button =findViewById(R.id.btn_add_to_cart)
        val btnbuy : Button =findViewById(R.id.btn_buy)

        val bundle : Bundle? = intent.extras

        val title = bundle!!.getString("title")
        val imageId = bundle!!.getInt("imageId")
        val price = bundle!!.getString("price")
        val shopDetail = bundle!!.getString("shopDetails")

        titleShop.text = title
        shopImage.setImageResource(imageId)
        shopDet.text = shopDetail
        shopPrice.text = price

        btnaddcart.setOnClickListener{
            val intent = Intent(this,add_cart_activity::class.java)
            startActivity(intent)
            finish()
        }
        btnbuy.setOnClickListener{
            val intent = Intent(this,PaymentActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}