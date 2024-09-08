package com.kodiiiofc.example.usercatalog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyDialog : DialogFragment() {

    private var removable: Removable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        removable = context as Removable?
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val user = requireArguments().getParcelable<User>("user") // запрашиваем из Bundle значение по ключу "user"
        val builder = AlertDialog.Builder(requireActivity())
        return builder
            .setTitle("Внимание")
            .setMessage("Удалить пользователя ${user!!.name}?")
            .setCancelable(true)
            .setPositiveButton("Да") { dialog, which ->
                removable?.remove(user)
            }
            .setNegativeButton("Нет") { dialog, which -> dialog.cancel() }
            .create()
    }
}

