package com.ilham.movieapplication.core.utils

import android.content.Context
import com.ilham.movieapplication.core.data.source.remote.response.MovieResponse
import com.ilham.movieapplication.core.data.source.remote.response.TvResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
    fun loadMovies() : List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val description = movie.getString("description")
                val director = movie.getString("director")
                val releaseDate = movie.getString("releaseDate")
                val poster = movie.getString("imagePath")

                val movieResponse = MovieResponse(id,title,description,director,releaseDate,poster)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
    fun loadTvShow() : List<TvResponse> {
        val list = ArrayList<TvResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvshowResponses.json").toString())
            val listArray = responseObject.getJSONArray("tvShow")
            for (i in 0 until listArray.length()) {
                val tv = listArray.getJSONObject(i)

                val id = tv.getString("id")
                val title = tv.getString("title")
                val description = tv.getString("description")
                val creator = tv.getString("creator")
                val releaseDate = tv.getString("releaseDate")
                val poster = tv.getString("imagePath")

                val tvResponse = TvResponse(id,title,description,creator,releaseDate,poster)
                list.add(tvResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}