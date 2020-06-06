package com.deniskrr.coronamobilevirus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.deniskrr.coronamobilevirus.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)

        binding.btnLoginHome.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_fakeLogin)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}
