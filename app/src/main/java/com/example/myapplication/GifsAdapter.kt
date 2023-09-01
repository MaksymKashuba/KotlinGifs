package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GifsAdapter(val context: Context, val gifs: List<DataObject>) : RecyclerView.Adapter<GifsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.ivGif)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout,parent, false))
    }

    override fun getItemCount(): Int {
        return gifs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = gifs[position]

        Glide.with(context).load(data.images.ogImage.url).into(holder.imageView)
    }

}
