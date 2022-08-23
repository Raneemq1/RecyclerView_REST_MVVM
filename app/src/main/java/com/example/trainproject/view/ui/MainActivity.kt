package com.example.trainproject.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.trainproject.R
import com.example.trainproject.data.model.PostModel
import com.example.trainproject.view.adapter.PostRecyclerAdapter
import com.example.trainproject.viewmodel.PostViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    /**
     * Initialize view model instance for using mvvm arch
     */
    private lateinit var viewModel: PostViewModel

    /**
     * Initialize recycler view and its adapter
     */
    private lateinit var postRecycler: RecyclerView
    private lateinit var postAdapter: PostRecyclerAdapter

    /**
     * Initialize swipe refresh to notify recycler adapter when data updated
     */
    private lateinit var swipeRefresh :SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = PostViewModel()
        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        /**
         * Invoke get data method from view model class
         */
        viewModel.getData()

        postRecycler = findViewById(R.id.postRecycler)
        postRecycler.layoutManager = LinearLayoutManager(this)
        postRecycler.setHasFixedSize(true)

        postAdapter = PostRecyclerAdapter()
        postRecycler.adapter = postAdapter

        swipeRefresh=findViewById(R.id.swipeRefresh)

        /**
         * Observe mutable live data in view model to update the list in the recycler adapter
         */
        viewModel.data.observe(this, Observer<List<PostModel>>{list->
            postAdapter.postsList=list
        })


        swipeRefresh.setOnRefreshListener {

            // on below line we are setting is refreshing to false.
            swipeRefresh.isRefreshing=false

            /**
             * Shuffle the data to simulate data updated process
             */
            Collections.shuffle(postAdapter.postsList, Random(System.currentTimeMillis()))

            postAdapter.notifyDataSetChanged()

        }


    }
}

