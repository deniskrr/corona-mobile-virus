package com.deniskrr.coronamobilevirus

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideSoftKeyboard() {
    val inputManager =
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(
        requireView().windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}