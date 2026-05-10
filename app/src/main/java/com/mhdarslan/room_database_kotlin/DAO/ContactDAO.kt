package com.mhdarslan.room_database_kotlin.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mhdarslan.room_database_kotlin.Models.Contact

@Dao
interface ContactDAO {
    @Insert
    suspend fun insertContact(contact: Contact) // this function will be run in background thread with coroutine because of this suspend keyword

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    // here i use LiveData, and room is compatible with livedata so it will run in background thread,
    // we don't need to use suspend keyword here
    @Query("SELECT * FROM contact")
    fun getContact(): LiveData<List<Contact>>
}