package com.example.lesson2

data class SinhVien(
    val id: Int = -1,
    val hoTen: String,
    val maSV: String,
    val email: String,
    val avatar: String // Có thể là đường dẫn ảnh hoặc URI
)