package com.example.trainproject.data.service

import com.example.trainproject.data.model.PostModel
import com.example.trainproject.data.remote.ApiConstant
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class uses to declare one instance of retrofit builder to prevent the redundancy
 */
object PostClient {

    val baseURL: String = ApiConstant.baseURL
    val apiInterface: PostInterfaceAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(PostInterfaceAPI::class.java)
    }

    /**
     * Call all methods on api interface
     */
    fun getPosts(): Call<List<PostModel>> {

        return apiInterface.getPost()
    }

}