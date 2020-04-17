package com.deniskrr.coronamobilevirus

import android.os.Bundle
import android.os.Handler
import android.telephony.PhoneNumberUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.deniskrr.coronamobilevirus.databinding.FakeLoginEmailFragmentBinding
import java.util.regex.Pattern


class FakeLoginEmailFragment : Fragment() {

    private lateinit var binding: FakeLoginEmailFragmentBinding
    private val viewModel: FakeLoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FakeLoginEmailFragmentBinding.inflate(inflater)

        binding.btnNextLogin.setOnClickListener {
            handleEmail()
        }

        binding.edittextEmailLogin.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                handleEmail()
                true
            } else {
                false
            }
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun handleEmail() {
        binding.progressLogin.visibility = View.VISIBLE
        hideSoftKeyboard()

        // Simulate loading
        Handler().postDelayed({
            binding.progressLogin.visibility = View.INVISIBLE
            val emailOrPhoneNumber = viewModel.email.value

            // Check to see if the victim entered a valid phone number.
            // If yes, save it and make him enter his email address too.
            if (!emailOrPhoneNumber.isNullOrEmpty() && PhoneNumberUtils.isGlobalPhoneNumber(
                    emailOrPhoneNumber
                )
            ) {
                viewModel.phoneNumber = emailOrPhoneNumber
                binding.inputEmailLogin.error =
                    "Couldn't find your Google Account. Try using your email address instead."
            }
            // Check to see if the victim entered a valid email address.
            else if (!emailOrPhoneNumber.isNullOrEmpty() && Pattern.matches(
                    //
                    "^[a-z0-9]([a-z0-9._]){5,}@g(oogle)?mail.com$",
                    emailOrPhoneNumber
                )
            ) {
                val action = FakeLoginEmailFragmentDirections.actionEmailToPassword()
                findNavController().navigate(action)
            } else {
                binding.inputEmailLogin.error = "Enter a valid email or phone number"
            }
        }, 1400)
    }
}