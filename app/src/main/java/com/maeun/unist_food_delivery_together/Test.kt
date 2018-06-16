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

    var categorylist = ArrayList<String>()
    var categorylista = ArrayList<String>()

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

                //Log.d("aaa",dataSnapshot.key) // 치킨
                //Log.d("aaa",dataSnapshot.getValue().toString()) //{-LEz4JPWNnHrNm6STn7n={restaurant=비버스치킨, category=치킨}}

            }
        })

        databasereference.child("restaurant").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //Log.d("aaa",dataSnapshot.getValue().toString()) //{치킨={-LEz4ZYv1c1HGsIp7iWu={restaurant=교촌치킨, category=치킨}, -LEz4JPWNnHrNm6STn7n={restaurant=비버스치킨, category=치킨}}}
            }
        })

        databasereference.child("restaurant").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var jsonObject = JSONObject(dataSnapshot.getValue().toString())

                //Log.d("aaa", jsonObject.toString()) //{"치킨":{"-LEz4ZYv1c1HGsIp7iWu":{"restaurant":"교촌치킨","category":"치킨"},"-LEz4JPWNnHrNm6STn7n":{"restaurant":"비버스치킨","category":"치킨"}}}
                //Log.d("aaa", jsonObject.keys().toString()) //java.util.LinkedHashMap$KeyIterator@f6e0bc0

                var i : Iterator<String> = jsonObject.keys()
                while(i.hasNext()){
                    var category : String = i.next()
                    //Log.d("aaa", b) //치킨
                    //categorylist.add(category)
                    //2nd
                    //Log.d("aaa", categorylist.toString())
                }
                //3rd
                //Log.d("aaa", categorylist.toString())
            }
        })
        //1st
        //Log.d("aaa", categorylist.toString())











        databasereference.child("restaurant").child("치킨").orderByChild("restaurant").equalTo("비버스치킨")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        //Log.d("aaa",dataSnapshot.key) // 치킨
                        //Log.d("aaa",dataSnapshot.getValue().toString()) //{-LEz4JPWNnHrNm6STn7n={restaurant=비버스치킨, category=치킨}}

                    }
                })


        databasereference.child("restaurant").child("치킨").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var jsonObject = JSONObject(dataSnapshot.getValue().toString())

                //Log.d("aaa", jsonObject.toString()) //{"치킨":{"-LEz4ZYv1c1HGsIp7iWu":{"restaurant":"교촌치킨","category":"치킨"},"-LEz4JPWNnHrNm6STn7n":{"restaurant":"비버스치킨","category":"치킨"}}}
                //Log.d("aaa", jsonObject.keys().toString()) //java.util.LinkedHashMap$KeyIterator@f6e0bc0

                var i : Iterator<String> = jsonObject.keys()
                while(i.hasNext()){
                    var category : String = i.next()
                    //Log.d("aaa", b) //치킨
                    categorylist.add(category)
                    Log.d("bbb",category)

                    databasereference.child("restaurant").child("치킨").child(category).addListenerForSingleValueEvent(object : ValueEventListener{
                        override fun onCancelled(p0: DatabaseError?) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }
                        override fun onDataChange(dataSnapshot: DataSnapshot) {

                            var jsonObject = JSONObject(dataSnapshot.getValue().toString())

                            //Log.d("aaa", jsonObject.toString()) //{"치킨":{"-LEz4ZYv1c1HGsIp7iWu":{"restaurant":"교촌치킨","category":"치킨"},"-LEz4JPWNnHrNm6STn7n":{"restaurant":"비버스치킨","category":"치킨"}}}
                            //Log.d("aaa", jsonObject.keys().toString()) //java.util.LinkedHashMap$KeyIterator@f6e0bc0

                            var i : Iterator<String> = jsonObject.keys()
                            var a = 0
                            while(i.hasNext()){
                                var category : String = i.next()
                                //Log.d("aaa", b) //치킨
                                if(category == "restaurant"){
                                    Log.d("aaa",jsonObject.getString(category))
                                }
//                                categorylista.add(category)
//                                Log.d("aaa", "start2")
//                                Log.d("aaa", categorylista.toString())
//
//                                Log.d("aaa",jsonObject.getString(categorylista.get(a)))
//                                a++
                            }

                        }
                    })
//                    Log.d("aaa", "start1")
//                    Log.d("aaa", categorylist.toString())

                }

            }
        })
















    }
}
