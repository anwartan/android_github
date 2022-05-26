package com.example.aplikasigithubuser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasigithubuser.databinding.FragmentDetailInfoBinding
import com.example.aplikasigithubuser.helper.ViewModelFactory
import com.example.aplikasigithubuser.model.toUserEntity


class DetailInfoFragment : Fragment() {
    private val detailViewModel:DetailViewModel by activityViewModels()
    private lateinit var userFavoriteViewModel:UserFavoriteViewModel
    private var _binding: FragmentDetailInfoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailInfoBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString(ARG_NAME)
        val id = arguments?.getInt(ARG_ID)
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        userFavoriteViewModel = ViewModelProvider(requireActivity(), factory).get(UserFavoriteViewModel::class.java)

        username?.let {
            detailViewModel.fetchUserDetail(it)
        }

        detailViewModel.isLoading.observe(viewLifecycleOwner,{

            showLoading(it)
        })
        detailViewModel.user.observe(viewLifecycleOwner,{user->
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

            id?.let {
                userFavoriteViewModel.isAddedFavoriteUser(it).observe(viewLifecycleOwner,{userEntity->
                    if(userEntity==null){
                        binding.favoriteBtn.text=getString(R.string.favorite)
                        binding.favoriteBtn.setOnClickListener {
                            userFavoriteViewModel.insert(user.toUserEntity())
                        }
                    }else{
                        binding.favoriteBtn.text=getString(R.string.unfavorite)
                        binding.favoriteBtn.setOnClickListener {
                            userFavoriteViewModel.delete(user.toUserEntity())
                        }
                    }

                })
            }

        })



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    private fun showLoading(it: Boolean) {
        binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
    }



    companion object {
        const val ARG_NAME = "username"
        const val ARG_ID = "id"

    }
}