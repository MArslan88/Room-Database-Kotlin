package com.mhdarslan.room_database_kotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mhdarslan.room_database_kotlin.DBModel.ContactDatabase
import com.mhdarslan.room_database_kotlin.Models.Contact
import com.mhdarslan.room_database_kotlin.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContactAdapter(val context: Context, val contactList: List<Contact>, var database: ContactDatabase) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder,position: Int) {
        var contact = contactList.get(position)
        holder.txtName.text = contact.name
        holder.txtPhone.text = contact.phone

        holder.itemView.setOnLongClickListener { // delete contact
            GlobalScope.launch {
                database.contactDao().deleteContact(contact)
            }
            true
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtName = itemView.findViewById<TextView>(R.id.txtName)
        var txtPhone = itemView.findViewById<TextView>(R.id.txtPhone)

    }
}