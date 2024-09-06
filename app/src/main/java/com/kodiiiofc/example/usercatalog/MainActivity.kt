package com.kodiiiofc.example.usercatalog

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var nameET: EditText
    private lateinit var ageET: EditText
    private lateinit var saveBTN: Button
    private lateinit var usersLV: ListView

    val users: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        nameET = findViewById(R.id.et_name)
        ageET = findViewById(R.id.et_age)
        saveBTN = findViewById(R.id.btn_save)
        usersLV = findViewById(R.id.lv_users)

        setSupportActionBar(toolbar)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        usersLV.adapter = adapter

        saveBTN.setOnClickListener {
            if (checkET()) {
                val name = nameET.text.toString()
                val age = ageET.text.toString().toInt()
                val user = User(name, age)
                users.add(user)
                adapter.notifyDataSetChanged()
                nameET.text.clear()
                ageET.text.clear()
            }
        }

        usersLV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val user = adapter.getItem(position)
                Snackbar.make(view, "Удалить пользователя ${user!!.name}?", Snackbar.LENGTH_LONG)
                    .setAction("Удалить") {
                        adapter.remove(user)
                        Snackbar.make(view, "Пользователь удален", Snackbar.LENGTH_SHORT).show()
                    }.show()
            }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_menu_exit -> finish()
        }

        return super.onOptionsItemSelected(item)
    }


    fun checkET(): Boolean {
        var flag = true

        if (nameET.text.isEmpty()) {
            nameET.text.clear()
            nameET.setHintTextColor(Color.RED)
            flag = false
        }

        if (ageET.text.isEmpty() || ageET.text.contains(".*\\D+.*")) {
            ageET.text.clear()
            ageET.setHintTextColor(Color.RED)
            flag = false
        }

        if (flag) {
            nameET.setHintTextColor(resources.getColor(R.color.light_gray))
            ageET.setHintTextColor(resources.getColor(R.color.light_gray))
        }

        return flag
    }

    data class User(val name: String, val age: Int) {
        override fun toString(): String {
            return "Имя: $name, возраст: $age"
        }
    }

}