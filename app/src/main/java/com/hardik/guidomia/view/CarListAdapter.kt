package com.hardik.guidomia.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hardik.guidomia.R
import com.hardik.guidomia.databinding.ItemLayoutBinding
import com.hardik.guidomia.model.CarModel
import com.hardik.guidomia.util.ClickListener
import com.hardik.guidomia.util.Constant

class CarListAdapter(val carList: ArrayList<CarModel>) : RecyclerView.Adapter<CarListAdapter.CarViewHolder>(), ClickListener {

    class CarViewHolder(var view: ItemLayoutBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemLayoutBinding>(inflater,R.layout.item_layout,parent,false)
        return CarViewHolder(view)
    }

    override fun getItemCount() = carList.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
       holder.view.carModel = carList[position]
       holder.view.listener = this
    }

    fun updateCarList(newDogsList: List<CarModel>) {
        carList.clear()
        carList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onViewClicked(view: View) {
       val data = view.tag
        if(data is Int){
            Constant.itemExpand.value = data
            notifyDataSetChanged()
        }
    }
}