package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()
    }
    private fun getNews(){
        val news : Call<News> = NewsService.newsInstance.getHeadlines("in", 1)
        news.enqueue(object: Callback<News>{
            override fun onFailure(call: Call<News>, t: Throwable){
                    Log.d("cheezycode", "errror in fetching news")
            }
            override fun onResponse(call: Call<News>, response: Response<News>){
                val news: News? = response.body()
                if(news!=null){
                    Log.d("cheezycode", news.toString())
                    val newsList = findViewById<View>(R.id.newsList) as RecyclerView
                    adapter = NewsAdapter(this@MainActivity, news.articles)
                    newsList.adapter = adapter
                    newsList.layoutManager = LinearLayoutManager(this@MainActivity)

                }
            }
        })
    }
}