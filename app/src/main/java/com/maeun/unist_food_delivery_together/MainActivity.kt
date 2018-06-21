package com.maeun.unist_food_delivery_together

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var partyItem: ArrayList<PartyItem>
    lateinit var partyAdapter: PartyAdapter
//    lateinit var mChild : ChildEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        partyItem = ArrayList()

        val databasereference = FirebaseDatabase.getInstance().getReference()

        databasereference.child("party").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                partyItem.clear()
                for (snapshot: DataSnapshot in dataSnapshot.children) {
                    //getValue()는 현재 snapshot이 가리키고 있는 데이터를 자바 객체 형태로 반환해줍니다.( 이 자바 객체를 받아줄 Bbs 클래스를 생성해주어야 합니다.)
                    //출처: https://github.com/Ronal92/FireBaseOne
                    //그리고 PartyItem의 각 인자들에게 ? = null을 해줘야 함
                    var party: PartyItem? = snapshot.getValue(PartyItem::class.java)

                    partyItem.add(PartyItem(party!!.category, party!!.restaurant, party!!.writer, party!!.time))
                }
                partyAdapter.notifyDataSetChanged()
            }
        })

        partyAdapter = PartyAdapter(partyItem, this)
        //setOnItemClickListener(this)를 해주려면, Adapter 부분에 함수 추가하는 것도 중요하지만,
        //그 후에 Class MainActivity에 View.OnClickListener를 추가해주는 것도 중요
        //그렇지 않으면 this랑 override fun onClick에서 override에 에러 발생
        partyAdapter.setOnItemClickListener(this)
        main_rv.layoutManager = LinearLayoutManager(this)
        main_rv.adapter = partyAdapter

        main_write_btn.setOnClickListener{
            startActivity(Intent(applicationContext, WriteActivity::class.java))
        }
    }

    override fun onClick(v: View?) {
        Toast.makeText(applicationContext,"클릭",Toast.LENGTH_SHORT).show()

        val idx : Int = main_rv.getChildAdapterPosition(v)

        val category : String? = partyItem[idx].category
        val restaurant : String? = partyItem[idx].restaurant
        val writer : String? = partyItem[idx].writer
        val time : String? = partyItem[idx].time

        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("category", category)
        intent.putExtra("restaurant", restaurant)
        intent.putExtra("writer", writer)
        intent.putExtra("time", time)

        startActivity(intent)
    }
}