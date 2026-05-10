package com.mhdarslan.room_database_kotlin.DBModel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mhdarslan.room_database_kotlin.DAO.ContactDAO
import com.mhdarslan.room_database_kotlin.Models.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase(){

    abstract fun contactDao(): ContactDAO

    companion object{
        @Volatile // when anything is assign to INSTANCE, so that will be available to every thread because of this 'Volatile' keywork
        private var INSTANCE: ContactDatabase? = null
        fun getDatabase(context: Context): ContactDatabase{
             if(INSTANCE == null){
                 synchronized(this){
                     INSTANCE = Room.databaseBuilder(context.applicationContext,
                         ContactDatabase::class.java,
                         "contactDB").build()
                 }

             }
            return INSTANCE!!
        }
    }

}