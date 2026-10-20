package com.example.lesson2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        btnAdd = findViewById(R.id.btnAdd)

        recyclerView.layoutManager = LinearLayoutManager(this)
        loadData()

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddUpdateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadData() {
        val list = dbHelper.getAllSinhVien()
        val adapter = SinhVienAdapter(list) { sv ->
            showOptionsDialog(sv)
        }
        recyclerView.adapter = adapter
    }

    private fun showOptionsDialog(sv: SinhVien) {
        val options = arrayOf("Sửa thông tin", "Xóa sinh viên")
        AlertDialog.Builder(this)
            .setTitle("Chọn thao tác cho ${sv.hoTen}")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        val intent = Intent(this, AddUpdateActivity::class.java).apply {
                            putExtra("id", sv.id)
                            putExtra("hoTen", sv.hoTen)
                            putExtra("maSV", sv.maSV)
                            putExtra("email", sv.email)
                            putExtra("avatar", sv.avatar)
                        }
                        startActivity(intent)
                    }
                    1 -> {
                        AlertDialog.Builder(this)
                            .setTitle("Xác nhận xóa")
                            .setMessage(R.string.msg_confirm_delete)
                            .setPositiveButton("Có") { _, _ ->
                                dbHelper.deleteSinhVien(sv.id)
                                loadData()
                            }
                            .setNegativeButton("Không", null)
                            .show()
                    }
                }
            }
            .show()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
}