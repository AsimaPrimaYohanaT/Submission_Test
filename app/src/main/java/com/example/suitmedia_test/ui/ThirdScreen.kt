package com.example.suitmedia_test.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.suitmedia_test.adapter.LoadingStateAdapter
import com.example.suitmedia_test.adapter.UserPagingAdapter
import com.example.suitmedia_test.databinding.ActivityThirdScreenBinding

class ThirdScreen : AppCompatActivity() {
    private  lateinit var binding: ActivityThirdScreenBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewModel: ThirdScreenViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //make refresh list data
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        viewModel = ViewModelProvider(this, ThirdScreenViewModel.ViewModelFactory())[ThirdScreenViewModel::class.java]

        showList()

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        //make divider in recycle view
        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(dividerItemDecoration)
    }

    //function for refresh data in recycle view
    private fun refreshData() {
        showList()
        swipeRefreshLayout.isRefreshing = false
    }

    //function to show recycle view
    private fun showList() {
        val adapter = UserPagingAdapter()
        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        viewModel.getData().observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }

    //function back to previous activity
    override fun onBackPressed() {
        val intent = Intent(this, SecondScreen::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

}