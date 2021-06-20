package com.ilham.movieapplication.core.data

import com.ilham.movieapplication.core.data.source.local.LocalDataSource
import com.ilham.movieapplication.core.data.source.remote.ApiResponse
import com.ilham.movieapplication.core.data.source.remote.RemoteDataSource
import com.ilham.movieapplication.core.data.source.remote.response.TvResponse
import com.ilham.movieapplication.core.domain.model.TvShow
import com.ilham.movieapplication.core.domain.repository.ITvRepository
import com.ilham.movieapplication.core.utils.AppExecutors
import com.ilham.movieapplication.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TvShowRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITvRepository{

     override fun getAllTvShow(): Flow<Resource<List<TvShow>>> =
         object : NetworkBoundResource<List<TvShow>, List<TvResponse>>() {
             override fun loadFromDB(): Flow<List<TvShow>> {
                 return localDataSource.getAllTvShow().map {  DataMapper.mapTvEntitiesToDomain(it) }

             }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

             override suspend fun createCall(): Flow<ApiResponse<List<TvResponse>>> =
                remoteDataSource.getAllTvShow()

             override suspend fun saveCallResult(data: List<TvResponse>) {
                val tvList = DataMapper.mapTvResponseToEntities(data)
                 localDataSource.insertTvShow(tvList)
            }
        }.asFlow()



     override fun getLikedTvShow(): Flow<List<TvShow>> {
        return localDataSource.getLikedTvShow().map { DataMapper.mapTvEntitiesToDomain(it) }

    }

     override fun setTvShowLiked(tv: TvShow, state: Boolean) {
         val tvEntity = DataMapper.mapTvDomainToEntity(tv)
        return appExecutors.diskIO().execute { localDataSource.setTvLiked(tvEntity, state) }
    }

}