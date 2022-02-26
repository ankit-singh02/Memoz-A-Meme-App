package com.example.memozz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MemeListAdapter(private val context: Context,private val memeInfo: MemeInfo): RecyclerView.Adapter<MemeListAdapter.MemeViewHolder>()  {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_meme,parent,false)
        return MemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        Glide.with(context).load(memeInfo.get(position).url).into(holder.titleView)
    }

    override fun getItemCount(): Int {
        return memeInfo.size
    }

    class MemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: ImageView =itemView.findViewById(R.id.memeImageView)
    }
}