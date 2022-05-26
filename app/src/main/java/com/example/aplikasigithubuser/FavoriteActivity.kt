package com.example.aplikasigithubuser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasigithubuser.adapter.UserRecyclerAdapter
import com.example.aplikasigithubuser.database.toUser
import com.example.aplikasigithubuser.databinding.ActivityFavoriteBinding
import com.example.aplikasigithubuser.helper.ViewModelFactory


class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteViewModel: UserFavoriteViewModel

    private lateinit var rvAdapter: UserRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar: Toolbar = binding.toolbarLayoutNormal
        toolbar.title="Favorite User"
        setSupportActionBar(toolbar)

        favoriteViewModel = obtainViewModel(this)
        favoriteViewModel.getAllFavoriteUsers().observe(this,{userEntities->
            val users = userEntities.map { user->user.toUser() }
            rvAdapter = UserRecyclerAdapter(users) {
                val moveWithUserIntent = Intent(this, DetailActivity::class.java)
                moveWithUserIntent.putExtra(DetailActivity.EXTRA_USER, it)
                startActivity(moveWithUserIntent)
            }
            binding.rvFavorite.adapter = rvAdapter
        })


    }

    private fun obtainViewModel(activity: AppCompatActivity): UserFavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(UserFavoriteViewModel::class.java)
    }


}