package com.example.lesson2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class AddUpdateActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var edtHoTen: EditText
    private lateinit var edtMaSV: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtAvatar: EditText
    private lateinit var btnSave: Button

    private var svId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update)

        dbHelper = DatabaseHelper(this)
        edtHoTen = findViewById(R.id.edtHoTen)
        edtMaSV = findViewById(R.id.edtMaSV)
        edtEmail = findViewById(R.id.edtEmail)
        edtAvatar = findViewById(R.id.edtAvatar)
        btnSave = findViewById(R.id.btnSave)

        if (intent.hasExtra("id")) {
            svId = intent.getIntExtra("id", -1)
            edtHoTen.setText(intent.getStringExtra("hoTen"))
            edtMaSV.setText(intent.getStringExtra("maSV"))
            edtEmail.setText(intent.getStringExtra("email"))
            edtAvatar.setText(intent.getStringExtra("avatar"))
            btnSave.text = "Cập nhật"
        }

        btnSave.setOnClickListener {
            val hoTen = edtHoTen.text.toString()
            val maSV = edtMaSV.text.toString()
            val email = edtEmail.text.toString()
            val avatar = edtAvatar.text.toString()

            if (hoTen.isNotEmpty() && maSV.isNotEmpty()) {
                val actionName = if (svId == -1) "thêm mới" else "sửa"
                AlertDialog.Builder(this)
                    .setTitle("Xác nhận")
                    .setMessage("Bạn có muốn $actionName thông tin SV này không?")
                    .setPositiveButton("Có") { _, _ ->
                        if (svId == -1) {
                            dbHelper.insertSinhVien(SinhVien(hoTen = hoTen, maSV = maSV, email = email, avatar = avatar))
                        } else {
                            dbHelper.updateSinhVien(SinhVien(id = svId, hoTen = hoTen, maSV = maSV, email = email, avatar = avatar))
                        }
                        finish()
                    }
                    .setNegativeButton("Không", null)
                    .show()
            }
        }
    }
}