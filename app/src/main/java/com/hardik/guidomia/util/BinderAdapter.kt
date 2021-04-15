package com.hardik.guidomia.util

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.hardik.guidomia.R

@BindingAdapter("imageURL")
fun imageBinding(imageView: ImageView, imageName: String?) {
            val imageDrawable= when(imageName?.toLowerCase()?.replace(" ","")){
                    Constant.BMW -> R.drawable.bmw
                    Constant.CAR_MAKE_ALPINE -> R.drawable.alpine
                    Constant.CAR_MAKE_MERCEDEZ -> R.drawable.mercedezbenz
                    Constant.CAR_MAKE_LAND_ROVER -> R.drawable.landrover
                    else -> R.drawable.tacoma
                }
        imageView.setImageResource(imageDrawable)
}

@BindingAdapter("detailsLayout")
fun carDetails(linearLayout: LinearLayout,position: Int){
    if(Constant.itemExpand.value == position)
        linearLayout.visibility = View.VISIBLE
    else
        linearLayout.visibility = View.GONE
}

@BindingAdapter("pronsConsList")
fun carProsList(textView: TextView , prosList:ArrayList<String>?){
    textView.text=""
    if(prosList?.size==0)
      textView.setText(R.string.data_failed)
    else prosList?.forEach {
        if(it.isNotEmpty()) textView.append("* "+it+"\n")
    }
}
