package com.hardik.guidomia

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hardik.guidomia.databinding.ActivityMainBinding
import com.hardik.guidomia.util.Constant
import com.hardik.guidomia.view.CarListAdapter
import com.hardik.guidomia.viewmodel.CarListViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

   private lateinit var viewModel: CarListViewModel
   private lateinit var dataBinding: ActivityMainBinding
   private val carListAdapter = CarListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(CarListViewModel::class.java)

        dataBinding.carList.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = carListAdapter
        }
        editText_make.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
               Log.d(Constant.TAG,"afterTextChanged::${p0.toString()}")
                viewModel.filtersMakeList(p0.toString())
                carListAdapter.notifyDataSetChanged()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        editText_model.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                viewModel.filtersModelList(p0.toString())
                carListAdapter.notifyDataSetChanged()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
        observeData()
    }

    private fun observeData(){
        viewModel.carList.observe(this, Observer { list->
            car_list.visibility = View.VISIBLE
            carListAdapter.updateCarList(list)
        })

        viewModel.isError.observe(this, Observer { isError ->
            isError?.let {
                textView_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}