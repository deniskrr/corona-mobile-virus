package com.deniskrr.coronamobilevirus

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.deniskrr.coronamobilevirus.databinding.FakeLoginPasswordFragmentBinding

class FakeLoginPasswordFragment : Fragment() {

    private lateinit var binding: FakeLoginPasswordFragmentBinding
    private val viewModel: FakeLoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FakeLoginPasswordFragmentBinding.inflate(inflater)

        binding.btnSignInLogin.setOnClickListener {
            handlePassword()
        }

        binding.edittextPasswordLogin.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                handlePassword()
                true
            } else {
                false
            }
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun handlePassword() {
        binding.progressLogin.visibility = View.VISIBLE
        hideSoftKeyboard()

        // Simulate loading
        Handler().postDelayed({
            binding.progressLogin.visibility = View.INVISIBLE
            val password = viewModel.email.value ?: ""

            // Check to see if the victim entered a valid password.
            if (password.length < 6) {
                binding.inputPasswordLogin.error =
                    "Wrong password. Try again or click Forgot password to reset it."
            } else if (!viewModel.wasPasswordChecked) {
                binding.inputPasswordLogin.error =
                    "Wrong password. Try again or click Forgot password to reset it."

                viewModel.wasPasswordChecked = true
            } else {
                findNavController().navigate(R.id.action_fakeLoginPassword_to_dashboard)
            }
        }, 1400)
    }

}
