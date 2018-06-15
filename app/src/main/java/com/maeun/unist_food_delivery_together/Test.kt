package com.maeun.unist_food_delivery_together

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONObject

class Test : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val databasereference = FirebaseDatabase.getInstance().getReference()

        databasereference.child("restaurant").child("치킨").orderByChild("restaurant").equalTo("비버스치킨")
                .addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("aaa","start")
                Log.d("aaa",dataSnapshot.key)
                Log.d("aaa",dataSnapshot.getValue().toString())
                Log.d("aaa","end")
            }
        })

        databasereference.child("restaurant").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("aaa","start")

                Log.d("aaa",dataSnapshot.getValue().toString())

                Log.d("aaa","end")
            }
        })




        databasereference.child("restaurant").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                Log.d("aaa",dataSnapshot.getValue().toString())

                var jsonObject = JSONObject(dataSnapshot.getValue().toString())

                Log.d("aaa", jsonObject.toString())
            }
        })


//
//        try
//        {
//            JSONObject jsonObject = new JSONObject(dataSnapshot.getValue().toString())
//            String fruitValue = jsonObject.getString("fruit")
//            JSONObject fruitObject = new JSONObject(fruitValue)
//            Iterator i = fruitObject.keys();
//            while(i.hasNext())
//            {
//                String b = i.next().toString();
//                Log.d("ITPangpang",b);
//                fruitKeyList.add(b);
//            }
//        }
//        catch (JSONException e)
//        {
//            e.printStackTrace();
//        }
//    }



//        databasereference.child("restaurant").addListenerForSingleValueEvent(object : ValueEventListener{
//            override fun onCancelled(p0: DatabaseError?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                Log.d("aaa",dataSnapshot.getValue().toString())
//                Log.d("aaa",dataSnapshot.key)
//            }
//
//        })

    }
}
