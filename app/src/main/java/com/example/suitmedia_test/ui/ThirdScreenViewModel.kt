package com.example.suitmedia_test.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmedia_test.data.DataRepository
import com.example.suitmedia_test.di.Injection
import com.example.suitmedia_test.response.DataItem

class ThirdScreenViewModel(private val dataRepository: DataRepository): ViewModel() {

    fun getData(): LiveData<PagingData<DataItem>> {
        return dataRepository.getData().cachedIn(viewModelScope)
    }

    class ViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ThirdScreenViewModel(Injection.provideRepository()) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}