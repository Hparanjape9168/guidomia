package com.hardik.guidomia.model

import com.google.gson.annotations.SerializedName

data class CarModel(

    var position: Int,

    @SerializedName("make")
    val make: String,

    @SerializedName("model")
    val model: String,

    @SerializedName("marketPrice")
    val marketPrice: Long,

    @SerializedName("customerPrice")
    var customerPrice: Long,

    @SerializedName("rating")
    val rating: Int,

    @SerializedName("consList")
    val consList: ArrayList<java.lang.String>,

    @SerializedName("prosList")
    val prosList: ArrayList<java.lang.String>
)
