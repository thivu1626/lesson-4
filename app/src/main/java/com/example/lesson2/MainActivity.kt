package com.example.lesson2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtUserName = findViewById<EditText>(R.id.edtUserName)
        val edtMssv = findViewById<EditText>(R.id.edtMssv)
        val btnClickMe = findViewById<Button>(R.id.btnClickMe)

        btnClickMe.setOnClickListener {
            val userName = edtUserName.text.toString().trim()
            val mssv = edtMssv.text.toString().trim()

            var valid = true
            if (userName.isEmpty()) {
                edtUserName.error = "UserName không được để trống"
                valid = false
            }
            if (mssv.isEmpty()) {
                edtMssv.error = "MSSV không được để trống"
                valid = false
            }
            if (!valid) return@setOnClickListener

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("USERNAME", userName)
            intent.putExtra("MSSV", mssv)
            startActivity(intent)
        }
    }
}