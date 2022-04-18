package com.example.mystylonic

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var  handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList : ArrayList<shop>

    lateinit var imageId : Array<Int>
    lateinit var title : Array<String>
    lateinit var price : Array<String>
    lateinit var shopDetail : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable,2000)
            }
        })

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
            "₹8,816.00",
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

        newArrayList = arrayListOf<shop>()
        getUserData()


        imageView.setOnClickListener{
            val intent = Intent(this,add_cart_activity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun getUserData() {

        for(i in imageId.indices)
        {
            val shop = shop(imageId[i],title[i],price[i])
            newArrayList.add(shop)
        }

        var adapter = MyAdapter(newArrayList)
        newRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                //    Toast.makeText(this@MainActivity,"You clicked on item no. $position",Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MainActivity,ShopDetailsActivity::class.java)
                intent.putExtra("title",newArrayList[position].title)
                intent.putExtra("imageId",newArrayList[position].image)
                intent.putExtra("price",newArrayList[position].price)
                intent.putExtra("shopDetails",shopDetail[position])
                startActivity(intent)

            }

        })
    }

    fun logoutUser(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable,2000)
    }
    private  val runnable =Runnable{
        viewPager2.currentItem = viewPager2.currentItem + 1

    }
    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40)) //margin added
        transformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)   //abs which is mathematical operation gives you absolute value so use abs as position
            page.scaleY=0.85f + r * 0.14f

        }
        viewPager2.setPageTransformer(transformer) //set on viewpager2
    }

    private fun init(){       //initialization and declare variables
        viewPager2 =findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.sliderimages)
        imageList.add(R.drawable.sliderimage1)
        imageList.add(R.drawable.sliderimage3)


        adapter= ImageAdapter(imageList,viewPager2)

        viewPager2.adapter=adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding =false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode =RecyclerView.OVER_SCROLL_NEVER

    }
}
