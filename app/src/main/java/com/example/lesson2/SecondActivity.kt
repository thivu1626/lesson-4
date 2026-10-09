package com.example.lesson2

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val userName = intent.getStringExtra("USERNAME") ?: ""
        val mssv = intent.getStringExtra("MSSV") ?: ""

        findViewById<TextView>(R.id.tvUserName).text = "UserName: $userName"
        findViewById<TextView>(R.id.tvMssv).text = "MSSV: $mssv"

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}