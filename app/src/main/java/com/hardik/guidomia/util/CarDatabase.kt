package com.hardik.guidomia.util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hardik.guidomia.model.CarModel

@Database(entities = arrayOf(CarModel::class),version = 1)
abstract class CarDatabase : RoomDatabase() {

    abstract fun carDao() : CarDao

    /**
     * Below is Programming of Thread.
     * Need to create a single instance of DataBase object to get updated data of Database
     */

    companion object {
        @Volatile private var instance : CarDatabase? = null
        private val LOCK = Any()

        /**
         * invoke database and if Object null than Create a database
         * synchronized:: lock to Gaurantie the updated and single instance of object
         * while it's Accessing in thread
         */
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CarDatabase::class.java,
            "cardatabase"
        ).build()
    }
}