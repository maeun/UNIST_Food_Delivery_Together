package com.maeun.unist_food_delivery_together

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_test.*
import org.json.JSONObject
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.google.firebase.storage.StorageMetadata
import com.google.android.gms.tasks.OnSuccessListener
import android.graphics.Bitmap
import android.net.Uri
import com.firebase.ui.storage.images.FirebaseImageLoader
import java.io.BufferedInputStream
import java.net.URL


class Test : AppCompatActivity() {

    var categorylist = ArrayList<String>()
    var categorylista = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val databasereference = FirebaseDatabase.getInstance().getReference()

//        databasereference.child("restaurant").addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                var jsonObject = JSONObject(dataSnapshot.getValue().toString())
//
//                var i: Iterator<String> = jsonObject.keys()
//                while (i.hasNext()) {
//                    var category: String = i.next()
//                    Log.d("aaa", category)
//                }
//            }
//        })


        databasereference.child("restaurant").child("치킨").child("test")
                .addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                    override fun onDataChange(dataSnapshot : DataSnapshot) {
                        var jsonObject = JSONObject(dataSnapshot.getValue().toString())

//                        Log.d("test", jsonObject.toString()) //{"restaurant":"테스트치킨","contact":"052-1111-2222","category":"치킨"}

                        var i: Iterator<String> = jsonObject.keys()
                        while (i.hasNext()) {
                            var key: String = i.next()
//                            Log.d("test", key)

                            if (key == "contact") {
//                                Log.d("test", jsonObject.getString(key))
                            }
                        }
                    }

                })


        databasereference.child("restaurant").child("치킨").orderByChild("restaurant").equalTo("비버스치킨")
                .addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        Log.d("aaaaa", dataSnapshot.toString()) //{ key = 치킨, value = {-LEz4JPWNnHrNm6STn7n={restaurant=비버스치킨, contact=052-1111-1111, category=치킨}} }
                        Log.d("aaaaa",dataSnapshot.getValue().toString()) //{-LEz4JPWNnHrNm6STn7n={restaurant=비버스치킨, contact=052-1111-1111, category=치킨}}
                        var jsonObject = JSONObject(dataSnapshot.getValue().toString())



                        Log.d("aaaaa", jsonObject.toString()) //{"-LEz4JPWNnHrNm6STn7n":{"restaurant":"비버스치킨","contact":"052-1111-1111","category":"치킨"}}

                        Log.d("aaaaa",jsonObject.keys().toString()) //java.util.LinkedHashMap$KeyIterator@8b29de8

                        var i: Iterator<String> = jsonObject.keys()

                        while (i.hasNext()) {
                            var key: String = i.next()

                        databasereference.child("restaurant").child("치킨").child(key)
                                .addListenerForSingleValueEvent(object : ValueEventListener{
                                    override fun onCancelled(p0: DatabaseError?) {
                                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                    }
                                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                                        var jsonObject_ = JSONObject(dataSnapshot.getValue().toString())

                                        var i_: Iterator<String> = jsonObject_.keys()
                                        while (i_.hasNext()) {
                                            var key_: String = i_.next()
                                            Log.d("aaaaa", key_)

                                            if (key_ == "contact") {
                                                Log.d("aaaaa", jsonObject_.getString(key_))
                                            }
                                        }
                                    }

                                })


                    }

                }})

        databasereference.child("restaurant").child("치킨").orderByChild("restaurant").equalTo("비버스치킨")
                .addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        Log.d("aaaaa", dataSnapshot.toString())
//                        Log.d("aaaaa",dataSnapshot.getValue().toString())
                        var jsonObejct = JSONObject(dataSnapshot.getValue().toString())
                    }

                })

        databasereference.child("restaurant").child("치킨").child("테스트치킨")
                .addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        Log.d("aaaaa", dataSnapshot.toString())
//                        Log.d("aaaaa",dataSnapshot.getValue().toString())
//                        var jsonObejct = JSONObject(dataSnapshot.getValue().toString())
                    }

                })

//        databasereference.child("restaurant").child("치킨").orderByChild("restaurant").equalTo("비버스치킨")
//                .addListenerForSingleValueEvent(object : ValueEventListener {
//                    override fun onCancelled(p0: DatabaseError?) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }

//                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        //Log.d("aaa",dataSnapshot.key) // 치킨
                        //Log.d("aaa",dataSnapshot.getValue().toString()) //{-LEz4JPWNnHrNm6STn7n={restaurant=비버스치킨, category=치킨}}

//                    }
//                })

//        databasereference.child("restaurant").addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }

//            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //Log.d("aaa",dataSnapshot.getValue().toString()) //{치킨={-LEz4ZYv1c1HGsIp7iWu={restaurant=교촌치킨, category=치킨}, -LEz4JPWNnHrNm6STn7n={restaurant=비버스치킨, category=치킨}}}
//            }
//        })

//        databasereference.child("restaurant").addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }

//            override fun onDataChange(dataSnapshot: DataSnapshot) {

//                var jsonObject = JSONObject(dataSnapshot.getValue().toString())

                //Log.d("aaa", jsonObject.toString()) //{"치킨":{"-LEz4ZYv1c1HGsIp7iWu":{"restaurant":"교촌치킨","category":"치킨"},"-LEz4JPWNnHrNm6STn7n":{"restaurant":"비버스치킨","category":"치킨"}}}
                //Log.d("aaa", jsonObject.keys().toString()) //java.util.LinkedHashMap$KeyIterator@f6e0bc0

//                var i: Iterator<String> = jsonObject.keys()
//                while (i.hasNext()) {
//                    var category: String = i.next()
                    //Log.d("aaa", b) //치킨
                    //categorylist.add(category)
                    //2nd
                    //Log.d("aaa", categorylist.toString())
//                }
                //3rd
                //Log.d("aaa", categorylist.toString())
//            }
//        })
        //1st
        //Log.d("aaa", categorylist.toString())


//        databasereference.child("restaurant").child("치킨").orderByChild("restaurant").equalTo("비버스치킨")
//                .addListenerForSingleValueEvent(object : ValueEventListener {
//                    override fun onCancelled(p0: DatabaseError?) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }

//                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        //Log.d("aaa",dataSnapshot.key) // 치킨
                        //Log.d("aaa",dataSnapshot.getValue().toString()) //{-LEz4JPWNnHrNm6STn7n={restaurant=비버스치킨, category=치킨}}

//                    }
//                })


//        databasereference.child("restaurant").child("치킨").addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }

//            override fun onDataChange(dataSnapshot: DataSnapshot) {

//                var jsonObject = JSONObject(dataSnapshot.getValue().toString())

                //Log.d("aaa", jsonObject.toString()) //{"치킨":{"-LEz4ZYv1c1HGsIp7iWu":{"restaurant":"교촌치킨","category":"치킨"},"-LEz4JPWNnHrNm6STn7n":{"restaurant":"비버스치킨","category":"치킨"}}}
                //Log.d("aaa", jsonObject.keys().toString()) //java.util.LinkedHashMap$KeyIterator@f6e0bc0

//                var i: Iterator<String> = jsonObject.keys()
//                while (i.hasNext()) {
//                    var category: String = i.next()
                    //Log.d("aaa", b) //치킨
//                    categorylist.add(category)
//                    Log.d("bbb", category)

//                    databasereference.child("restaurant").child("치킨").child(category).addListenerForSingleValueEvent(object : ValueEventListener {
//                        override fun onCancelled(p0: DatabaseError?) {
//                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                        }

//                        override fun onDataChange(dataSnapshot: DataSnapshot) {

//                            var jsonObject = JSONObject(dataSnapshot.getValue().toString())

                            //Log.d("aaa", jsonObject.toString()) //{"치킨":{"-LEz4ZYv1c1HGsIp7iWu":{"restaurant":"교촌치킨","category":"치킨"},"-LEz4JPWNnHrNm6STn7n":{"restaurant":"비버스치킨","category":"치킨"}}}
                            //Log.d("aaa", jsonObject.keys().toString()) //java.util.LinkedHashMap$KeyIterator@f6e0bc0

//                            var i: Iterator<String> = jsonObject.keys()
//                            var a = 0
//                            while (i.hasNext()) {
//                                var category: String = i.next()
                                //Log.d("aaa", b) //치킨
//                                if (category == "restaurant") {
//                                    Log.d("aaa", jsonObject.getString(category))
//                                }
//                                categorylista.add(category)
//                                Log.d("aaa", "start2")
//                                Log.d("aaa", categorylista.toString())
//
//                                Log.d("aaa",jsonObject.getString(categorylista.get(a)))
//                                a++
                            }

                        }
//                    })
//                    Log.d("aaa", "start1")
//                    Log.d("aaa", categorylist.toString())

//                }

//            }
//        })
//        val storage = FirebaseStorage.getInstance().getReference()
//        val storageRef: StorageReference = storage.child("menu").child("치킨").child("테스트치킨")

//        val storage = FirebaseStorage.getInstance()
//        val storageRef: StorageReference = storage.getReferenceFromUrl("gs://unist-food-delivery-together.appspot.com")
//                .child("menu").child("치킨").child("테스트치킨")


//
//        Glide.with(this /* context */)
//                .load(storageRef.downloadUrl.getResult().toString())
//                .into(test_iv)


//


//        Glide.with(applicationContext)
//                //FirebaseImageLoader()을 사용하기 위해서는 gradle에 implementation 'com.firebaseui:firebase-ui-storage:0.6.0'을 추가해야 함
//                .using(FirebaseImageLoader())
//                .load(storageRef)
//                .into(test_iv)

//        test_iv.setOnClickListener {
//            Toast.makeText(applicationContext, storageRef.toString(), Toast.LENGTH_SHORT).show()
//        }

//        storageRef.metadata
//                .addOnSuccessListener { storageMetadata ->
//
//                    Toast.makeText(applicationContext, "성공", Toast.LENGTH_SHORT).show()
//
//
//
//                }
//                .addOnFailureListener {
//                    Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
//                }
//    }
//}
