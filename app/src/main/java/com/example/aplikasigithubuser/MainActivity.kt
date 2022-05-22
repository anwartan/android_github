package com.example.aplikasigithubuser

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasigithubuser.adapter.UserRecyclerAdapter
import com.example.aplikasigithubuser.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var rvAdapter: UserRecyclerAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = binding.toolbarLayoutNormal
        setSupportActionBar(toolbar)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.users.observe(this, { users ->
            rvAdapter = UserRecyclerAdapter(users) {
                val moveWithUserIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveWithUserIntent.putExtra(DetailActivity.EXTRA_USER, it)
                startActivity(moveWithUserIntent)
            }
            binding.rvList.adapter = rvAdapter
        })

        mainViewModel.isLoading.observe(this,{
            showLoading(it)
        })

        mainViewModel.errorText.observe(this,{
            it.getContentIfNotHandled()?.let { snackBarText ->
                Snackbar.make(
                    window.decorView.rootView,
                    snackBarText,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel.fetchSearchUser(query)
                searchView.clearFocus()
                return true
            }


            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }



}