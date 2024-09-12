package com.kodiiiofc.example.usercatalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class UsersViewModel : ViewModel() {

    val mutableUserList = mutableListOf<User?>()
    val users: MutableLiveData<List<User?>> by lazy { MutableLiveData<List<User?>>() }


    // задаем значение пустой список для LiveData users
    init {
        users.value = mutableUserList
    }

}

