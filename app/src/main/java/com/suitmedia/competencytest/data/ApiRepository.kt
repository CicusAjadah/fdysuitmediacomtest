package com.suitmedia.competencytest.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.suitmedia.competencytest.api.ApiService
import com.suitmedia.competencytest.apiresponse.DataItem

class ApiRepository(private val apiService: ApiService) {
    fun getUsers(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ApiPagingResponse(apiService)
            }
        ).liveData
    }
}