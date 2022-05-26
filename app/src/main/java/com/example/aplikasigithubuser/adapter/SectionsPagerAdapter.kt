package com.example.aplikasigithubuser.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aplikasigithubuser.DetailInfoFragment
import com.example.aplikasigithubuser.FollowerFragment
import com.example.aplikasigithubuser.FollowingFragment

class SectionsPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    var username = ""
    var id: Int = -1
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = DetailInfoFragment()
                fragment.arguments = Bundle().apply {
                    putString(FollowerFragment.ARG_NAME,username)
                    putInt(DetailInfoFragment.ARG_ID,id)
                }
            }
            1 -> {
                fragment = FollowerFragment()
                fragment.arguments = Bundle().apply {
                    putString(FollowerFragment.ARG_NAME,username)
                }
            }
            2 -> {
                fragment = FollowingFragment()
                fragment.arguments = Bundle().apply {
                    putString(FollowerFragment.ARG_NAME,username)
                }
            }
        }

        return fragment as Fragment
    }
}