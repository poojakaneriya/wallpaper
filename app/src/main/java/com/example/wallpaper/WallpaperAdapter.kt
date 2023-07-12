package com.example.wallpaper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class WallpaperAdapter(List: ArrayList<PhotosItem>) :
    RecyclerView.Adapter<WallpaperAdapter.WallpaperHolder>() {

    var list = List
    lateinit var context : Context

    class WallpaperHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var img = itemView.findViewById<ImageView>(R.id.imgwallpaper)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperHolder {

        context = parent.context
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_item, parent, false)
        return WallpaperHolder(view)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: WallpaperHolder, position: Int) {

        Glide.with(context).load(list.get(position).src?.portrait).into(holder.img)
    }


}