package com.example.mystylonic.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.mystylonic.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val backarrow : ImageView = findViewById(R.id.backarrow)
        val btnprofile : Button =findViewById(R.id.btnprofile)

        backarrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnprofile.setOnClickListener{

            Toast.makeText(this,"Profile Successfully updated", Toast.LENGTH_SHORT).show()

        }


    }
}