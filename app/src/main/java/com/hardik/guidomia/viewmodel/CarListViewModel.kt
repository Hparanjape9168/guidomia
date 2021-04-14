package com.hardik.guidomia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hardik.guidomia.model.CarModel

class CarListViewModel : ViewModel() {

    val carList = MutableLiveData<List<CarModel>>()

    fun refreshData(){
        val car1 = CarModel("Rav 4",2500,"Toyota",3000,4, arrayListOf("Hello","Hi","cool"),
            arrayListOf("world","happy","test"))
        val car2 = CarModel("Equinox",200,"GM",3000,2, arrayListOf("Hello","Hi","cool"),
            arrayListOf("world","happy","test"))
        val car3 = CarModel("Coroloa",5000,"Toyota",3000,5, arrayListOf("Hello","Hi","cool"),
            arrayListOf("world","happy","test"))
        val car4 = CarModel("Alto",2100,"Maruti",800,1, arrayListOf("Hello","Hi","cool"),
            arrayListOf("world","happy","test"))
        val car5 = CarModel("Alto",2100,"Maruti",800,3, arrayListOf("Hello","Hi","cool"),
            arrayListOf("world","happy","test"))
        val car6 = CarModel("Alto",2100,"Maruti",800,4, arrayListOf("Hello","Hi","cool"),
            arrayListOf("world","happy","test"))

        val car7 = CarModel("Alto",2100,"Maruti",800,2, arrayListOf("Hello","Hi","cool"),
            arrayListOf("world","happy","test"))
        val carFinalList = arrayListOf<CarModel>(car1,car2,car3,car4,car5,car6,car7)
        carList.value = carFinalList
    }
}