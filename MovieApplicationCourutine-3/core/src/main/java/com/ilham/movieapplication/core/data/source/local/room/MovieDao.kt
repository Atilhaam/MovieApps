package com.ilham.movieapplication.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ilham.movieapplication.core.data.source.local.entity.MovieEntity
import com.ilham.movieapplication.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentities")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentities")
    fun getTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM movieentities where liked = 1")
    fun getLikedMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentities where liked = 1")
    fun getLikedTvShow(): Flow<List<TvShowEntity>>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMoviesById(movieId: String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: List<MovieEntity>)

    @Update
    fun upDateMovie(movie: MovieEntity)

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE tvId = :tvId")
    fun getTvById(tvId: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tv: List<TvShowEntity>)

    @Update
    fun updateTvShow(tv: TvShowEntity)

}