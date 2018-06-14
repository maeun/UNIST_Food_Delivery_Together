package com.maeun.unist_food_delivery_together

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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

        partyAdapter = PartyAdapter(partyItem)
        main_rv.layoutManager = LinearLayoutManager(this)
        main_rv.adapter = partyAdapter
    }
}
