package com.hardik.guidomia.util

import com.hardik.guidomia.model.CarModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CarApiService {
    private val BASE_URL = "https://json.extendsclass.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CarAPI::class.java)

    fun getCars() : Single<List<CarModel>> {
        return api.getCars()
    }
}