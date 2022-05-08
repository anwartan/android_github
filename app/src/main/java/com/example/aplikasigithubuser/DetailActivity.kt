package com.example.aplikasigithubuser

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.aplikasigithubuser.databinding.ActivityDetailBinding
import com.example.aplikasigithubuser.model.User

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "EXTRA_USER"
    }

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        val toolbar: Toolbar = findViewById(R.id.toolbarLayoutCollapse)
        toolbar.title = user.username
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.includeDetail.tvUsername.text = user.username
        binding.includeDetail.tvName.text = user.name
        binding.imgAvatarCollapse.setImageResource(user.avatar)
        binding.includeDetail.tvFollower.text = user.follower.toString()
        binding.includeDetail.tvFollowing.text = user.following.toString()
        binding.includeDetail.tvCompany.text = user.company
        binding.includeDetail.tvLocation.text = user.location
        binding.includeDetail.tvRepository.text = user.repository
        binding.fabShare.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/html"
            sharingIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Bagikan pengguna ${user.name} yang sedang bekerja di ${user.company} dan serta memiliki repository ${user.repository}"
            )
            startActivity(Intent.createChooser(sharingIntent, "Share using"))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)

    }


}