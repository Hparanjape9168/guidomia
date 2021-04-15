package com.hardik.guidomia.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hardik.guidomia.model.CarModel
import com.hardik.guidomia.util.CarApiService
import com.hardik.guidomia.util.CarDatabase
import com.hardik.guidomia.util.Constant
import com.hardik.guidomia.util.Constant.itemExpand
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class CarListViewModel(application: Application) : BaseViewModel(application) {

    val carList = MutableLiveData<List<CarModel>>()
    private val disposable = CompositeDisposable()
    private val carApiService = CarApiService()
    val isError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        itemExpand.value = 0
        val disposableObject = carApiService.getCars()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<CarModel>>(){
                override fun onSuccess(list: List<CarModel>) {
                    carsListRetrieved(dataModification(list))
                    Log.d(Constant.TAG,"CarListViewModel:: Fetch From API${list.size}")
                }
                override fun onError(e: Throwable) {
                    Log.d(Constant.TAG,"Error::${e}")
                    isError.value = true
                    isLoading.value = false
                }

            })
        disposable.add(disposableObject)
    }

    private fun dataModification(list: List<CarModel>) : List<CarModel>{
        var position = 0
        var mutableList = list
        mutableList.apply {
            this.forEach {
                it.customerPrice = it.customerPrice/1000
                it.position = position
                position++
            }
        }
        return mutableList
    }

    private fun storeLocalDatabase(carList : List<CarModel>){
        launch {
            val dao = CarDatabase(getApplication()).carDao()
            dao.deleteAllCars()

            val resultAll = dao.insertAll(*carList.toTypedArray())
            var i = 0
            while (i < carList.size) {
                carList[i].id = resultAll[i].toInt()
                ++i
            }
            Log.d(Constant.TAG,"Fetch From DataBase")
            carsListRetrieved(carList)
        }
    }

    private fun carsListRetrieved(carListValues: List<CarModel>) {
        carList.value = carListValues
        isError.value = false
        isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}