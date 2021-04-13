package com.hardik.guidomia.model

data class Car(val make: String,
               val marketPrice: String,
               val model: String,
               val customerPrice : Long,
               val rating: Int,
               val consList : ArrayList<String>,
               val prosList : ArrayList<String>)