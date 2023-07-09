package com.devmukul.appscheduler.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devmukul.appscheduler.databinding.ListItemTemplateBinding
import com.devmukul.appscheduler.model.Template
import com.jachai.jachaimart.R
import com.jachai.jachaimart.elearning.model.Program

class AppListAdapter (private val context: Context,
                      private var list: MutableList<Template>,
                      private val interaction: Interaction?

) : RecyclerView.Adapter<AppListAdapter.ViewHolder>() {

    class ViewHolder(private var binding: ListItemTemplateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            context: Context,
            product: Template?,
            interaction: Interaction?
        ) {
            binding.apply {
                if (product != null) {
//                    Glide.with(context)
//                        .load(product.)
//                        .placeholder(R.drawable.ic_place_holder)
//                        .error(R.drawable.ic_place_holder)
//                        .into(productImageView)

//                    val count = JachaiApplication.mDatabase.daoAccess()
//                        .getProductOrderCount(product.id.toString())
//
//                    if (count > 0) {
//                        countView.visibility = View.VISIBLE
//                        countView.text = count.toString()
//                        //icCount.text = count.toString()
//                    } else {
//                        countView.visibility = View.GONE
//                    }

                    tName.text = product.name
                    tName.text = product.name
                    tName.text = product.name


                    binding.root.setOnClickListener {
                        interaction?.onProductSelected(product)
                    }

                }
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemTemplateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(context, data, interaction)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(it: MutableList<Template>?) {
        if (it != null) {
            this.list = it
        }
    }

    interface Interaction {
        fun onProductSelected(product: Template?)
        fun onProductAddToCartX(product: Template?, quantity: Int, position: Int)
    }


}

