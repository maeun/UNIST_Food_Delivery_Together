package com.maeun.unist_food_delivery_together

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_write.*
import org.json.JSONObject

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detail_category_tv.setText(intent.getStringExtra("category"))
        detail_restaurant_tv.setText(intent.getStringExtra("restaurant"))
        detail_writer_tv.setText(intent.getStringExtra("writer"))
        detail_time_tv.setText(intent.getStringExtra("time"))

//        val storage = FirebaseStorage.getInstance().getReference()
//        val storageRef: StorageReference = storage.child("menu").child(intent.getStringExtra("category") + "/" + intent.getStringExtra("restaurant"))
//
//        val ONE_MEGABYTE = (1024 * 1024).toLong()
//
//        //download file as a byte array
//        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener { bytes ->
//            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//            detail_menu_iv.setImageBitmap(bitmap)
//        }
//                .addOnFailureListener{
//
//                }

        val databasereference = FirebaseDatabase.getInstance().getReference()

        databasereference.child("restaurant").child(intent.getStringExtra("category")).child(intent.getStringExtra("restaurant"))
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        var jsonObject = JSONObject(dataSnapshot.getValue().toString())

                        var i: Iterator<String> = jsonObject.keys()

                        while (i.hasNext()) {
                            var key: String = i.next()

                            if (key == "contact") {
                                detail_contact_tv.setText(jsonObject.getString(key))
                            }
                        }
                    }
                })




//        databasereference.child("restaurant").child(intent.getStringExtra("category")).child(intent.getStringExtra("restaurant"))
//                .addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                Log.d("aaa",dataSnapshot.toString())
//
//                var contact: PartyItem? = dataSnapshot.getValue(PartyItem::class.java)
//                contact = PartyItem(contact!!.contact)
//                detail_contact_tv.setText(contact.toString())
            }

}