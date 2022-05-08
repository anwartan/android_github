package com.example.aplikasigithubuser

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasigithubuser.adapter.UserRecyclerAdapter
import com.example.aplikasigithubuser.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var rvAdapter: UserRecyclerAdapter
    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataFollower: IntArray
    private lateinit var dataFollowing: IntArray

    private lateinit var dataAvatar: TypedArray
    private var users = arrayListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbarLayoutNormal)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.rv_list)

        initData()
        insertData()

        rvAdapter = UserRecyclerAdapter(users) {
            val moveWithUserIntent = Intent(this@MainActivity, DetailActivity::class.java)
            moveWithUserIntent.putExtra(DetailActivity.EXTRA_USER, it)
            startActivity(moveWithUserIntent)
        }
        recyclerView.adapter = rvAdapter
    }


    private fun initData() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataAvatar = resources.obtainTypedArray(R.array.avatar)
        dataCompany = resources.getStringArray(R.array.company)
        dataFollower = resources.getIntArray(R.array.followers)
        dataFollowing = resources.getIntArray(R.array.following)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
    }

    private fun insertData() {
        for (position in dataName.indices) {
            val user = User(
                dataAvatar.getResourceId(position, -1),
                dataUsername[position],
                dataName[position],
                dataFollower[position],
                dataFollowing[position],
                dataLocation[position],
                dataRepository[position],
                dataCompany[position]
            )
            users.add(user)
        }

    }
}