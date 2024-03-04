package com.example.imagepicker.classes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagepicker.R

class adopter(val data: ArrayList<String>, val context: Context) :
    RecyclerView.Adapter<adopter.v>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adopter.v {
        val lay = LayoutInflater.from(parent.context).inflate(R.layout.adopter, parent, false)
        return v(lay)
    }

    override fun onBindViewHolder(holder: adopter.v, position: Int) {
        val current = data[position]
        Glide.with(context)

            .load(current)

            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class v(v: View) : RecyclerView.ViewHolder(v) {
        val image: ImageView = v.findViewById(R.id.image)
    }
}