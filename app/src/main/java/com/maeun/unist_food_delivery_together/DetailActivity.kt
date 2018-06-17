package com.maeun.unist_food_delivery_together

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detail_category_tv.setText(intent.getStringExtra("category"))
        detail_restaurant_tv.setText(intent.getStringExtra("restaurant"))
        detail_writer_tv.setText(intent.getStringExtra("writer"))
        detail_time_tv.setText(intent.getStringExtra("time"))

        val storage = FirebaseStorage.getInstance().getReference()
        val storageRef: StorageReference = storage.child("menu").child(intent.getStringExtra("category") + "/" + intent.getStringExtra("restaurant"))

        val ONE_MEGABYTE = (1024 * 1024).toLong()

        //download file as a byte array
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener { bytes ->
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            detail_menu_iv.setImageBitmap(bitmap)
        }

    }
}
