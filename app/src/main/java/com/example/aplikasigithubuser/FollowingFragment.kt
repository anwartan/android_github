package com.example.aplikasigithubuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.aplikasigithubuser.adapter.UserRecyclerAdapter
import com.example.aplikasigithubuser.databinding.FragmentFollowingBinding
import com.google.android.material.snackbar.Snackbar


class FollowingFragment : Fragment() {

    private val detailViewModel:DetailViewModel by activityViewModels()
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: UserRecyclerAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString(ARG_NAME)

        username?.let { detailViewModel.fetchListFollowing(it) }
        detailViewModel.isLoading.observe(viewLifecycleOwner,{

            showLoading(it)
        })

        detailViewModel.followings.observe(viewLifecycleOwner,{users->

            rvAdapter = UserRecyclerAdapter(users) {

                Snackbar.make(view,it.login,Snackbar.LENGTH_SHORT).show()
//                val moveWithUserIntent = Intent(context, DetailActivity::class.java)
//                moveWithUserIntent.putExtra(DetailActivity.EXTRA_USER, it)
//                startActivity(moveWithUserIntent)
            }
            binding.rvFollowing.adapter = rvAdapter
        })
    }

    private fun showLoading(it: Boolean) {
        binding.progressBarFollowing.visibility = if (it) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
    companion object {

        const val ARG_NAME = "username"

    }
}