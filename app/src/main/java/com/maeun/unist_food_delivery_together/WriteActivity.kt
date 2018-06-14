package com.maeun.unist_food_delivery_together

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_write.*
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*

class WriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        val databasereference = FirebaseDatabase.getInstance().getReference()

        write_send_btn.setOnClickListener{

            val now = System.currentTimeMillis()
            val time = Date(now)
            val sdf = SimpleDateFormat("HH시 mm분")
            val getTime = sdf.format(time)

            val users = HashMap<String, String>()
            users.put("writer", "maeuniyee")
            users.put("content", write_content_edit.getText().toString())
            users.put("time", getTime)
            databasereference.child("party").push().setValue(users)

            Toast.makeText(applicationContext,"등록되었습니다",Toast.LENGTH_SHORT).show()
            finish()
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
}
