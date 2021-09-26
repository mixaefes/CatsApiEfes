package com.example.thecatsapi.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.thecatsapi.retrofit.Cat
import com.example.thecatsapi.retrofit.CatsApiService
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1
const val NETWORK_PAGE_SIZE = 3
class CatsPagingSource(
    private val service: CatsApiService
) : PagingSource<Int, Cat>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getCatsService(page = pageIndex)
            Log.i("CatsPagingSource","it's my response: $response")
            val nextKey = if (response.isEmpty()) {
                null
            } else {
             //   pageIndex + (params.loadSize/NETWORK_PAGE_SIZE)
                pageIndex + 1
            }
            Log.i("CatsPagingSource","umber of page : $nextKey")
            LoadResult.Page(
                data = response,
                prevKey = if(pageIndex== STARTING_PAGE_INDEX)null else nextKey,
                nextKey = nextKey
            )
        } catch (exception:IOException){
            return LoadResult.Error(exception)
        }catch (exception:HttpException){
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
       return state.anchorPosition?.let{
            anchorPosition -> state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
           ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
