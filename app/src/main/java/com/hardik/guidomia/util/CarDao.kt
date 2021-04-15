package com.hardik.guidomia.util

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hardik.guidomia.model.CarModel

@Dao
interface CarDao {

    /**
     * Data Access Object which Links Between the DataBase and Objects in
     * Our Project.
     * ALl Function to CRUD operation should write here
     *
     * Suspend :: To execute Operation on Background thread, it will not compile the project otherwise
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg carModel: CarModel) : List<Long>

    @Query("SELECT * FROM carModel")
    suspend fun getAllCars() : List<CarModel>

    @Query("DELETE FROM carModel")
    suspend fun deleteAllCars()
}