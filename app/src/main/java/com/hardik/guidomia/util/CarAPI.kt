package com.hardik.guidomia.util

import com.hardik.guidomia.model.CarModel
import io.reactivex.Single
import retrofit2.http.GET

interface CarAPI {

    @GET("bin/3d0a5a091da7")
    fun getCars(): Single<List<CarModel>>
}