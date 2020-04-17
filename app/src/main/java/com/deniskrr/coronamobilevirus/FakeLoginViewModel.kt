package com.deniskrr.coronamobilevirus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FakeLoginViewModel : ViewModel() {

    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    var phoneNumber: String? = null
    var wasPasswordChecked: Boolean = false

}