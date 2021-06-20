package com.ilham.movieapplication.core.di

import androidx.room.Room
import com.ilham.movieapplication.core.data.MovieRepository
import com.ilham.movieapplication.core.data.TvShowRepository
import com.ilham.movieapplication.core.data.source.local.LocalDataSource
import com.ilham.movieapplication.core.data.source.local.room.MovieDatabase
import com.ilham.movieapplication.core.data.source.remote.RemoteDataSource
import com.ilham.movieapplication.core.data.source.remote.network.ApiService
import com.ilham.movieapplication.core.domain.repository.IMovieRepository
import com.ilham.movieapplication.core.domain.repository.ITvRepository
import com.ilham.movieapplication.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "Movies.db"
        ).build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
         retrofit.create(ApiService::class.java)
    }

}
val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository>  { MovieRepository(get(), get(), get()) }
    single<ITvRepository> { TvShowRepository(get(), get(), get()) }

}