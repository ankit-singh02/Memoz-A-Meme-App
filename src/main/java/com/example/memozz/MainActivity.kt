package com.example.memozz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {

    val apiUrl= "https://meme-api.herokuapp.com/gimme"
    var memeInfoItem= arrayOf<MemeInfoItem>()
    val memeInfo=MemeInfo()
    private lateinit var recyclerView: RecyclerView
    private lateinit var memeImageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerView)
        memeImageView=findViewById(R.id.memeImageView)

        recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter=MemeListAdapter(this,memeInfo)
        recyclerView.adapter=adapter

        val stringRequest= StringRequest(apiUrl, Response.Listener {
            val gsonBuilder= GsonBuilder()
            val gson=gsonBuilder.create()
            memeInfoItem=gson.fromJson(it,Array<MemeInfoItem>::class.java)
            memeInfoItem.forEach {
                memeInfo.add(it)
            }

        }, Response.ErrorListener {
            Toast.makeText(this,"Some Error Occured", Toast.LENGTH_SHORT).show()
        })

        val queue= Volley.newRequestQueue(this)
        queue.add(stringRequest)
    }
}