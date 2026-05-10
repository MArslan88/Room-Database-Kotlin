package com.mhdarslan.room_database_kotlin.DBModel

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mhdarslan.room_database_kotlin.DAO.ContactDAO
import com.mhdarslan.room_database_kotlin.Models.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase(){

    abstract fun contactDao(): ContactDAO


}