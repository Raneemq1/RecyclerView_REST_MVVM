package com.example.trainproject.data.service

import com.example.trainproject.data.model.PostModel
import retrofit2.Call
import retrofit2.http.GET

interface PostInterfaceAPI {

    /**
     * REST API with GET method to get all posts from placeholder website
     */
    @GET("posts")
    fun getPost(): Call<List<PostModel>>
}