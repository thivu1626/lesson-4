package com.example.lesson2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "QLSinhVien.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "SinhVien"
        private const val COL_ID = "id"
        private const val COL_HOTEN = "hoTen"
        private const val COL_MASV = "maSV"
        private const val COL_EMAIL = "email"
        private const val COL_AVATAR = "avatar"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_HOTEN TEXT, " +
                "$COL_MASV TEXT, " +
                "$COL_EMAIL TEXT, " +
                "$COL_AVATAR TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Thêm sinh viên (Create)
    fun insertSinhVien(sv: SinhVien): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_HOTEN, sv.hoTen)
            put(COL_MASV, sv.maSV)
            put(COL_EMAIL, sv.email)
            put(COL_AVATAR, sv.avatar)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    // Lấy danh sách sinh viên (Read)
    fun getAllSinhVien(): List<SinhVien> {
        val list = mutableListOf<SinhVien>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID))
                val hoTen = cursor.getString(cursor.getColumnIndexOrThrow(COL_HOTEN))
                val maSV = cursor.getString(cursor.getColumnIndexOrThrow(COL_MASV))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL))
                val avatar = cursor.getString(cursor.getColumnIndexOrThrow(COL_AVATAR))
                list.add(SinhVien(id, hoTen, maSV, email, avatar))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    // Sửa sinh viên (Update)
    fun updateSinhVien(sv: SinhVien): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_HOTEN, sv.hoTen)
            put(COL_MASV, sv.maSV)
            put(COL_EMAIL, sv.email)
            put(COL_AVATAR, sv.avatar)
        }
        return db.update(TABLE_NAME, values, "$COL_ID = ?", arrayOf(sv.id.toString()))
    }

    // Xóa sinh viên (Delete)
    fun deleteSinhVien(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COL_ID = ?", arrayOf(id.toString()))
    }
}