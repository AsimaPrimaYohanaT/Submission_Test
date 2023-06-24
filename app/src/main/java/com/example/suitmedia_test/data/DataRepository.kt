package com.example.suitmedia_test.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.suitmedia_test.network.ApiService
import com.example.suitmedia_test.response.DataItem

class DataRepository(private val apiService: ApiService) {
    fun getData(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = { DataPagingSource(apiService) }
        ).liveData
    }
}