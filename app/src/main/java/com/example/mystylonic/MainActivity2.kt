package com.example.mystylonic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.LinearFragment_Container,MyCartFragment()).commit()

    }
}