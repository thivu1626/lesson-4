package com.example.lesson2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SinhVienAdapter(
    private val list: List<SinhVien>,
    private val onItemClick: (SinhVien) -> Unit
) : RecyclerView.Adapter<SinhVienAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvHoTen: TextView = view.findViewById(R.id.tvHoTen)
        val tvMaSV: TextView = view.findViewById(R.id.tvMaSV)
        val tvEmail: TextView = view.findViewById(R.id.tvEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sinh_vien, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sv = list[position]
        holder.tvHoTen.text = sv.hoTen
        holder.tvMaSV.text = sv.maSV
        holder.tvEmail.text = sv.email

        holder.itemView.setOnClickListener {
            onItemClick(sv)
        }
    }

    override fun getItemCount(): Int = list.size
}