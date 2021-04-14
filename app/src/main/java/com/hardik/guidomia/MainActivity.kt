package com.hardik.guidomia

import android.os.Bundle
import android.util.LayoutDirection
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hardik.guidomia.databinding.ActivityMainBinding
import com.hardik.guidomia.view.CarListAdapter
import com.hardik.guidomia.viewmodel.CarListViewModel


class MainActivity : AppCompatActivity() {

   private lateinit var viewModel: CarListViewModel
   private lateinit var dataBinding: ActivityMainBinding
   private val carListAdapter = CarListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(CarListViewModel::class.java)
        viewModel.refreshData()

        dataBinding.carList.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = carListAdapter
        }

        observeData()
    }

    private fun observeData(){
        viewModel.carList.observe(this, Observer {list->
            carListAdapter.updateCarList(list)
        })
    }
}