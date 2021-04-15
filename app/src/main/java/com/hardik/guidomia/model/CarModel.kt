package com.hardik.guidomia.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CarModel(

    @ColumnInfo(name="position")
    var position: Int,

    @ColumnInfo(name = "make")
    @SerializedName("make")
    val make: String,

    @ColumnInfo(name = "model")
    @SerializedName("model")
    val model: String,

    @ColumnInfo(name = "marketPrice")
    @SerializedName("marketPrice")
    val marketPrice: Long,

    @ColumnInfo(name = "customerPrice")
    @SerializedName("customerPrice")
    var customerPrice: Long,

    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    val rating: Int,

    @ColumnInfo(name = "consList")
    @SerializedName("consList")
    val consList: ArrayList<String>,

    @ColumnInfo(name = "prosList")
    @SerializedName("prosList")
    val prosList: ArrayList<String>
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
