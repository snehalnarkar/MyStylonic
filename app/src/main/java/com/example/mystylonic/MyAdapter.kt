package com.example.mystylonic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class MyAdapter(private val shopList : ArrayList<shop>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return MyViewHolder(itemView , mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = shopList[position]
        holder.image.setImageResource(currentItem.image)
        holder.title.text = currentItem.title
        holder.price.text = currentItem.price
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    class MyViewHolder(itemView: View , listener: onItemClickListener) : RecyclerView.ViewHolder(itemView)
    {

        val image : ImageView = itemView.findViewById(R.id.item_image)
        val title : TextView = itemView.findViewById(R.id.item_title)
        val price : TextView = itemView.findViewById(R.id.item_price)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}