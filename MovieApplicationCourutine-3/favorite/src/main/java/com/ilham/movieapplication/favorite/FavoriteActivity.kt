package com.ilham.movieapplication.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilham.movieapplication.favorite.databinding.ActivityFavoriteBinding
import com.ilham.movieapplication.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        val sectionPagerAdapter = FavoriteSectionPagerAdapter(this, supportFragmentManager)
        activityFavoriteBinding.viewPager.adapter = sectionPagerAdapter
        activityFavoriteBinding.tabs.setupWithViewPager(activityFavoriteBinding.viewPager)


        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadKoinModules(favoriteModule)
    }
}