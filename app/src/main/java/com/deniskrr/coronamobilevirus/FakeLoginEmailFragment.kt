package com.deniskrr.coronamobilevirus

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.deniskrr.coronamobilevirus.databinding.FakeLoginEmailFragmentBinding

class FakeLoginEmailFragment : Fragment() {

    private lateinit var binding: FakeLoginEmailFragmentBinding
    private val viewModel: FakeLoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FakeLoginEmailFragmentBinding.inflate(inflater)

        binding.btnNextLogin.setOnClickListener {
            binding.progressLogin.visibility = View.VISIBLE

            Handler().postDelayed({
                binding.progressLogin.visibility = View.INVISIBLE

                val action = FakeLoginEmailFragmentDirections.actionEmailToPassword()
                findNavController().navigate(action)
            }, 1400)

        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}