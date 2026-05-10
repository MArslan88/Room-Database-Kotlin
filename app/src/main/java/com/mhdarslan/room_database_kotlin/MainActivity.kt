package com.mhdarslan.room_database_kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.room.Room
import com.mhdarslan.room_database_kotlin.Models.Contact
import com.mhdarslan.room_database_kotlin.DBModel.ContactDatabase
import com.mhdarslan.room_database_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // this is not a good practice
        // we should use singleton for this
        database = Room.databaseBuilder(applicationContext,
            ContactDatabase::class.java,
            "contactDB").build()

        GlobalScope.launch { // this Coroutine for background thread
            database.contactDao().insertContact(Contact(0, "Arslan", "03451234567"))
        }

    }

    fun getData(view: View) {
        database.contactDao().getContact().observe(this, Observer{
            Log.d("cheezyCode",it.toString())
        })
    }
}