package com.socialx.assignment_2

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    fun getPosts() : Call<MutableList<PostModel>>
}