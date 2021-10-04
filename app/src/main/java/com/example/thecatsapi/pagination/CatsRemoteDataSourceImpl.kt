package com.example.thecatsapi.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.thecatsapi.retrofit.Cat
import com.example.thecatsapi.retrofit.CatsApiService
import kotlinx.coroutines.flow.Flow

class CatsRemoteDataSourceImpl(
    private val catsService: CatsApiService
) : CatsRemoteDataSource {
    override fun getAllCats(): Flow<PagingData<Cat>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                CatsPagingSource(service = catsService)
            }
        ).flow
    }
}
