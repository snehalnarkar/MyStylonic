package com.example.mystylonic

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ShopDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_details)

        val titleShop : TextView = findViewById(R.id.title)
        val shopDet : TextView = findViewById(R.id.det_detail)
        val shopImage : ImageView = findViewById(R.id.det_image)
        val shopPrice : TextView = findViewById(R.id.det_price)

        val bundle : Bundle? = intent.extras

        val title = bundle!!.getString("title")
        val imageId = bundle!!.getInt("imageId")
        val price = bundle!!.getString("price")
        val shopDetail = bundle!!.getString("shopDetails")

        titleShop.text = title
        shopImage.setImageResource(imageId)
        shopDet.text = shopDetail
        shopPrice.text = price

    }
}