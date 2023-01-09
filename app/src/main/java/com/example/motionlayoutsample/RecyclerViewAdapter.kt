package com.example.motionlayoutsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motionlayoutsample.databinding.RecyclerViewItemBinding

class RecyclerViewAdapter(private val list:List<String>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(private val binding:RecyclerViewItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun setData(item: String) {
            binding.apply {
                tvItem.text = item
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.setData(item)
    }

    override fun getItemCount(): Int  = list.size
}