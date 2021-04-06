package com.murali.byjus.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.retrofit.coroutines.ui.base.ViewModelFactory
import com.mindorks.retrofit.coroutines.ui.main.viewmodel.MainViewModel
import com.mindorks.retrofit.coroutines.utils.ServiceStatus
import com.murali.byjus.R
import com.murali.byjus.servicedata.api.ApiHelper
import com.murali.byjus.servicedata.api.RetrofitBuilder
import com.murali.byjus.servicedata.model.Articles
import com.murali.byjus.servicedata.model.News
import com.murali.byjus.ui.adapter.NewsAdapter
import com.murali.byjus.utils.Common
import com.murali.byjus.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_main)

        if(Common.isOnline(this)) {
            setupViewModel()
            setupObservers()
        }else{
            progressBar.visibility = View.GONE
            Common.showNetworkAlert(this)
        }
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI(keyName: List<Articles>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(this, keyName)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getUsers(Common.getCurrentDate(),"publishedAt",Constants.API_KEY).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    ServiceStatus.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        //resource.data?.let { news -> retrieveList(news) }
                        resource.data?.enqueue(object : Callback<News?> {
                            override fun onResponse(
                                call: Call<News?>?,
                                response: Response<News?>
                            ) {
                                if (response.isSuccessful()) {
                                    response.body()?.articles?.let { it1 -> setupUI(it1) }
                                }
                            }

                            override fun onFailure(
                                call: Call<News?>?,
                                t: Throwable
                            ) {
                                setupUI(arrayListOf())
                                t.printStackTrace()
                            }
                        })
                    }
                    ServiceStatus.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    ServiceStatus.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })

    }

}