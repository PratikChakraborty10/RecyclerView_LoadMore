package com.socialx.assignment_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val recyclerView2 = findViewById<RecyclerView>(R.id.recyclerView2)



        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getPosts()

        val bt = findViewById<Button>(R.id.loadMoreBt)





            call.enqueue(object : retrofit2.Callback<MutableList<PostModel>> {
                override fun onResponse(
                    call: Call<MutableList<PostModel>>,
                    response: Response<MutableList<PostModel>>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Loading Data ....", Toast.LENGTH_SHORT).show()
                        //Log.e("success", response.body().toString())
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = PostAdapter(response.body()!!)
                        }
                        bt.setOnClickListener {
                            recyclerView2.apply {
                                layoutManager = LinearLayoutManager(this@MainActivity)
                                adapter = PostAdapterNew(response.body()!!)
                                bt.visibility = View.GONE
                                Toast.makeText(this@MainActivity, "10 more views loaded successfully", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<MutableList<PostModel>>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error", t.message.toString())
                    Toast.makeText(this@MainActivity, "Response from API Failed\nPlease check your internet connection", Toast.LENGTH_SHORT).show()
                }

            })
    }
}