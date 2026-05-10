package com.mhdarslan.room_database_kotlin

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhdarslan.room_database_kotlin.Adapters.ContactAdapter
import com.mhdarslan.room_database_kotlin.DBModel.ContactDatabase
import com.mhdarslan.room_database_kotlin.Models.Contact
import com.mhdarslan.room_database_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var database: ContactDatabase
    lateinit var adapter: ContactAdapter

    private val contactList = ArrayList<Contact>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        database = ContactDatabase.getDatabase(this)

        adapter = ContactAdapter(this, contactList, database)
        binding.rvContact.adapter = adapter
        binding.rvContact.layoutManager = LinearLayoutManager(this)


        getData()

        binding.btnSave.setOnClickListener {
            val name = binding.edtContName.text.toString().trim()
            val phone = binding.edtContPhone.text.toString().trim()
            if(name.isEmpty()){
                Toast.makeText(this, "Please enter contact name", Toast.LENGTH_SHORT).show()
            } else if( phone.isEmpty()){
                Toast.makeText(this, "Please enter contact phone", Toast.LENGTH_SHORT).show()
            } else {
                GlobalScope.launch { // this Coroutine for background thread
                    database.contactDao().insertContact(Contact(0, name, phone, Date()))
                }
                binding.edtContPhone.text.clear()
                binding.edtContName.text.clear()
                binding.edtContName.requestFocus()
                getData()
            }
        }


    }

    fun getData() {
        database.contactDao().getContact().observe(this, Observer{
            contactList.clear()      // clear old data
            contactList.addAll(it)   // add new data
            adapter.notifyDataSetChanged()
        })
    }
}