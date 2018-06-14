package com.maeun.unist_food_delivery_together

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class PartyViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {
    var category : TextView = itemView!!.findViewById(R.id.party_item_category_tv) as TextView
    var restaurant : TextView = itemView!!.findViewById(R.id.party_item_restaurant_tv) as TextView
    var writer : TextView = itemView!!.findViewById(R.id.party_item_writer_tv) as TextView
    var time : TextView = itemView!!.findViewById(R.id.party_item_time_tv) as TextView
}