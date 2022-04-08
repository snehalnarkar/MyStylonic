package com.example.mystylonic

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrrayList : ArrayList<shop>

    lateinit var imageId : Array<Int>
    lateinit var title : Array<String>
    lateinit var price : Array<String>
    lateinit var shopDetail : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView:ImageView = findViewById(R.id.home_add_cart)


        imageId = arrayOf(
            R.drawable.w_dress4,
            R.drawable.kids_dress1,
            R.drawable.shirt_1,
            R.drawable.saree,
            R.drawable.kids_dress4,
            R.drawable.m_kurta,
            R.drawable.kids_dress3,
            R.drawable.shirt_2,
            R.drawable.w_dress2,
            R.drawable.m_dress,
            R.drawable.shirt_4,
            R.drawable.w_dress1,
            R.drawable.w_dress5
        )

        title = arrayOf(
            "Babiva Fashion",
            "KAARIGARI ",
            "Allen Solly",
            "Kanchipuram Silk Saree",
            "Chandrika ",
            "KSH Trendz ",
            "KL Collection ",
            "Blackberrys ",
            "Dressberry ","BLIVE "
            ,"AJ Dezines ",
            "Selvia",
            "NAMDEV ",
            "pantivilla "

        )

        price = arrayOf(
            "₹519 ",
            "₹250",
            "₹1,476",
            "₹18,816.00",
            "₹410",
            "₹229",
            "₹220",
            "₹1,114",
            "₹1,094",
            "₹129",
            "₹554",
            "₹554",
            "₹339"
            )


        shopDetail = arrayOf(

            getString(R.string.dress1),
            getString(R.string.dress2),
            getString(R.string.dress3),
            getString(R.string.dress4),
            getString(R.string.dress5),
            getString(R.string.dress6),
            getString(R.string.dress7),
            getString(R.string.dress8),
            getString(R.string.dress9),
            getString(R.string.dress10),
            getString(R.string.dress11),
            getString(R.string.dress12),
            getString(R.string.dress13)
        )

        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrrayList = arrayListOf<shop>()
        getUserData()


        imageView.setOnClickListener{
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun getUserData() {

        for(i in imageId.indices)
        {
            val shop = shop(imageId[i],title[i],price[i])
            newArrrayList.add(shop)
        }

        var adapter = MyAdapter(newArrrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                //    Toast.makeText(this@MainActivity,"You clicked on item no. $position",Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MainActivity,ShopDetailsActivity::class.java)
                intent.putExtra("title",newArrrayList[position].title)
                intent.putExtra("imageId",newArrrayList[position].image)
                intent.putExtra("price",newArrrayList[position].price)
                intent.putExtra("shopDetails",shopDetail[position])
                startActivity(intent)

            }

        })
    }

    fun logoutUser(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

    }
}
