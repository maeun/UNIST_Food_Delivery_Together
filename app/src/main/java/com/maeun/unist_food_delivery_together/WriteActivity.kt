package com.maeun.unist_food_delivery_together

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_write.*
import com.google.firebase.database.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class WriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        val databasereference = FirebaseDatabase.getInstance().getReference()

        val categorylist = ArrayList<String>()
        val restaurantlist = ArrayList<String>()

        val categorylist_adapter = ArrayAdapter(
                this, // Context
                android.R.layout.simple_spinner_item, // Layout
                categorylist // Array
        )

        val restaurantlist_adapter = ArrayAdapter(
                this, // Context
                android.R.layout.simple_spinner_item, // Layout
                restaurantlist // Array
        )

        databasereference.child("restaurant").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var jsonObject = JSONObject(dataSnapshot.getValue().toString())

                var i: Iterator<String> = jsonObject.keys()
                while (i.hasNext()) {
                    var category: String = i.next()
                    categorylist.add(category)
                    Log.d("aaa", category)
                }

                write_category_spinner.adapter = categorylist_adapter

                write_category_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                        restaurantlist.clear()
                        databasereference.child("restaurant").child(write_category_spinner.selectedItem.toString())
                                .addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onCancelled(p0: DatabaseError?) {
                                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                    }

                                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                                        var jsonObject = JSONObject(dataSnapshot.getValue().toString())

                                        var i: Iterator<String> = jsonObject.keys()

                                        while (i.hasNext()) {
                                            var key: String = i.next()

                                            databasereference.child("restaurant").child(write_category_spinner.selectedItem.toString()).child(key)
                                                    .addListenerForSingleValueEvent(object : ValueEventListener {
                                                        override fun onCancelled(p0: DatabaseError?) {
                                                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                                        }

                                                        override fun onDataChange(dataSnapshot: DataSnapshot) {

                                                            var jsonObject = JSONObject(dataSnapshot.getValue().toString())

                                                            var i: Iterator<String> = jsonObject.keys()

                                                            while (i.hasNext()) {
                                                                var key: String = i.next()

                                                                if (key == "restaurant") {
                                                                    restaurantlist.add(jsonObject.getString(key))
                                                                }
                                                            }
                                                            write_restaurant_spinner.adapter = restaurantlist_adapter
                                                            write_restaurant_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                                                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                                                                    // Display the selected item text on text view
                                                                }
                                                                override fun onNothingSelected(parent: AdapterView<*>) {
                                                                    // Another interface callback
                                                                }
                                                            }
                                                        }
                                                    })

                                        }

                                    }
                                })
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // Another interface callback
                    }
                }

            }
        })

        categorylist_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        restaurantlist_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        write_send_btn.setOnClickListener {

            val now = System.currentTimeMillis()
            val time = Date(now)
            val sdf = SimpleDateFormat("HH시 mm분")
            val getTime = sdf.format(time)

            val users = HashMap<String, String>()
            users.put("writer", "maeuniyee")
            users.put("content", write_content_edit.getText().toString())
            users.put("time", getTime)
            databasereference.child("party").push().setValue(users)

            Toast.makeText(applicationContext, "등록되었습니다", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
}
