package com.maeun.unist_food_delivery_together

import okhttp3.MultipartBody
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import kotlinx.android.synthetic.main.activity_add_restaurant.*
import java.text.SimpleDateFormat
import java.util.HashMap

class AddRestaurantActivity : AppCompatActivity() {

    private val REQ_CODE_SELECT_IMAGE = 100
    lateinit var data: Uri
    private var image: MultipartBody.Part? = null

    val databasereference = FirebaseDatabase.getInstance().getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_restaurant)

        add_restaurant_menu_btn.setOnClickListener {
            changeImage()
        }
        add_restaurant_add_btn.setOnClickListener {
            postBoard()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //if(ApplicationController.getInstance().is)
                    this.data = data!!.data

                    val options = BitmapFactory.Options()

                    var input: InputStream? = null // here, you need to get your context.
                    try {
                        input = contentResolver.openInputStream(this.data)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
                    val photo = File(this.data.toString()) // 가져온 파일의 이름을 알아내려고 사용합니다

                    ///RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
                    // MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용!!

                    image = MultipartBody.Part.createFormData("photo", photo.name, photoBody)

                    //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);

                    Glide.with(this)
                            .load(data.data)
                            .centerCrop()
                            .into(add_restaurant_menu_iv)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun changeImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    fun postBoard() {
        val users = HashMap<String, String>()

        users.put("category", add_restaurant_category_edit.getText().toString())
        users.put("restaurant", add_restaurant_restaurant_edit.getText().toString())
        users.put("contact", add_restaurant_contact_edit.getText().toString())

        databasereference.child("restaurant").child(add_restaurant_category_edit.getText().toString()).push().setValue(users)


        val storage = FirebaseStorage.getInstance().getReference()
        //val filename : String = add_restaurant_restaurant_edit.getText().toString() + "_menu"
        val storageRef: StorageReference = storage.child("menu").child(add_restaurant_category_edit.getText().toString() + "/" + add_restaurant_restaurant_edit.getText().toString())
        storageRef.putFile(data)

        Toast.makeText(applicationContext, "등록되었습니다", Toast.LENGTH_SHORT).show()
        finish()
        startActivity(Intent(applicationContext, MainActivity::
        class.java))

    }

}