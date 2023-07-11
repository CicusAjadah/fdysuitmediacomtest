package com.suitmedia.competencytest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.suitmedia.competencytest.apiresponse.DataItem
import com.suitmedia.competencytest.data.ApiRepository

class ThirdViewModel(apiRepository: ApiRepository) : ViewModel() {

    val responses : LiveData<PagingData<DataItem>> = apiRepository.getUsers().cachedIn(viewModelScope)

    companion object{
        private const val TAG = "ListStoryViewModel"
    }
}