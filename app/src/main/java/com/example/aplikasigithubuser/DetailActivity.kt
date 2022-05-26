package com.example.aplikasigithubuser

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.adapter.SectionsPagerAdapter
import com.example.aplikasigithubuser.databinding.ActivityDetailBinding
import com.example.aplikasigithubuser.model.User
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {


    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        val toolbar: Toolbar = findViewById(R.id.toolbarLayoutCollapse)
        toolbar.title = user.login
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(this)
            .load(user.avatarUrl)
            .into(binding.imgAvatarCollapse)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.id=user.id
        sectionsPagerAdapter.username = user.login
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)

    }

    companion object {
        const val EXTRA_USER = "EXTRA_USER"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_0,
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }


}