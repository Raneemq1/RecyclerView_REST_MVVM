package com.example.trainproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainproject.data.model.PostModel
import com.example.trainproject.data.service.PostClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel : ViewModel() {

    var data: MutableLiveData<List<PostModel>> = MutableLiveData()

    fun getData() {
        val callData = PostClient.getPosts()
        callData.enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>, response: Response<List<PostModel>>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {


            }
        })


    }
}