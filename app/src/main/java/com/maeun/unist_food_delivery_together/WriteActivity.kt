package com.maeun.unist_food_delivery_together

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_write.*
import com.google.firebase.database.*

class WriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        val databasereference = FirebaseDatabase.getInstance().getReference()

        write_send_btn.setOnClickListener{

            val users = HashMap<String, String>()
            users.put("writer", "maeuniyee")
            users.put("content", write_content_edit.getText().toString())
            databasereference.child("party").push().setValue(users)


        }
    }
}
