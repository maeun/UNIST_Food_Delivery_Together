package com.maeun.unist_food_delivery_together

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.getStringExtra("category")
        intent.getStringExtra("restaurant")
        detail_writer_tv.setText(intent.getStringExtra("writer"))
        detail_time_tv.setText(intent.getStringExtra("time"))
    }
}
