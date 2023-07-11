package com.suitmedia.competencytest.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.suitmedia.competencytest.api.ApiService
import com.suitmedia.competencytest.apiresponse.DataItem

class ApiPagingResponse(private val apiService: ApiService) : PagingSource<Int, DataItem>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {

        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val response = apiService.getUsers(position, 5)
            if (response.isSuccessful) {
                val responseData = response.body()?.data
                Log.e("Cek", "$responseData")
                if (responseData != null) {
                    if (responseData.isEmpty()) {
                        throw NullPointerException("You have reached the final page!")
                    }
                }

                LoadResult.Page(
                    data = responseData!!.filterNotNull(),
                    prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                    nextKey = if (responseData.isEmpty()) null else position + 1
                )
            } else {
                LoadResult.Error(Exception("Failed to load data"))
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}