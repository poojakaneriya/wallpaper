package com.example.wallpaper

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaper.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: WallpaperAdapter
    var List = ArrayList<PhotosItem>()
    var page = 1
    private val TAG = "MainActivity"
    lateinit var search: String


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {

            search = binding.editSearch.text.toString()



            callApi(search, page)


        }

        binding.setOnScroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {

                page++
                binding.progrebar.setVisibility(View.VISIBLE)
                callApi(search, page)


            }
        })

    }

    private fun callApi(search: String, page1: Int) {


        var apiInterface = APIClient.getapiClient().create(ApiInterface::class.java)
        apiInterface.getWallpaper(search, page1.toString(), APIClient.KEY)
            .enqueue(object : Callback<WallpeparModal> {
                override fun onResponse(
                    call: Call<WallpeparModal>,
                    response: Response<WallpeparModal>
                ) {

                    List.addAll(response.body()?.photos as ArrayList<PhotosItem>)
                    adapter = WallpaperAdapter(List)
                    binding.recycleView.layoutManager = GridLayoutManager(this@MainActivity, 3)
                    binding.recycleView.adapter = adapter
                    binding.progrebar.visibility = View.GONE

                    Log.e(TAG, "onResponse: ==============" + response.body())

                }

                override fun onFailure(call: Call<WallpeparModal>, t: Throwable) {
                    Log.e(TAG, "onFailure: ======== " + t.message)
                }


            })

    }

}


