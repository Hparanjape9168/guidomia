package com.hardik.guidomia.model

data class CarModel(
    val make: String,
    val marketPrice: Long,
    val model: String,
    val customerPrice: Long,
    val rating: Int,
    val consList: ArrayList<String>,
    val prosList: ArrayList<String>)