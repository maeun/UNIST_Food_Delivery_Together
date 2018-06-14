package com.maeun.unist_food_delivery_together

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PartyAdapter(var item : ArrayList<PartyItem>) :RecyclerView.Adapter<PartyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.party_item, parent, false)
        return PartyViewHolder(mainView)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PartyViewHolder, position: Int) {
        holder.category.text = item[position].category
        holder.restaurant.text = item[position].restaurant
        holder.writer.text = item[position].writer
        holder.time.text = item[position].time
    }
}