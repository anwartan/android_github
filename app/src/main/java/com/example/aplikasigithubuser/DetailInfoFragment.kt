package com.example.aplikasigithubuser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.aplikasigithubuser.databinding.FragmentDetailInfoBinding


class DetailInfoFragment : Fragment() {
    private val detailViewModel:DetailViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailInfoBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString(ARG_NAME)

        username?.let {
            detailViewModel.fetchUserDetail(it)
        }

        detailViewModel.isLoading.observe(viewLifecycleOwner,{

            showLoading(it)
        })
        detailViewModel.user.observe(viewLifecycleOwner,{user->
            Log.d("DETAIL","data user $user")
            binding.tvUsername.text = user.login
            binding.tvName.text = user.login
            binding.tvCompany.text = user.company
            binding.tvLocation.text = user.location
            binding.tvRepository.text = user.reposUrl
            binding.sharebtn.setOnClickListener {

                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/html"
                sharingIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Bagikan pengguna ${user.name} yang sedang bekerja di ${user.location} dan serta memiliki repository ${user.reposUrl}"
                )
                startActivity(Intent.createChooser(sharingIntent, "Share using"))
            }
        })

    }

    private fun showLoading(it: Boolean) {
        binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
    }

    companion object {
        const val ARG_NAME = "username"

    }
}