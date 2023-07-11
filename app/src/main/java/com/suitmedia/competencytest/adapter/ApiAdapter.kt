package com.suitmedia.competencytest.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suitmedia.competencytest.apiresponse.DataItem
import com.suitmedia.competencytest.databinding.ItemViewBinding
import com.suitmedia.competencytest.utils.SELECTED_USER

class ApiAdapter :
    PagingDataAdapter<DataItem, ApiAdapter.ViewHolder>(DIFF_CALLBACK){

    class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            Glide.with(binding.imgItemPhoto)
                .load(data.avatar)
                .into(binding.imgItemPhoto)
            binding.tvItemName.text = "${data.firstName} ${data.lastName}"
            binding.tvItemEmail.text = "${data.email}"
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) : ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data!= null) {
            viewHolder.bind(data)
            viewHolder.itemView.setOnClickListener {
                val backIntent = Intent()
                backIntent.putExtra(SELECTED_USER, "${data.firstName} ${data.lastName}")
                val vhContext = viewHolder.itemView.context as Activity
                vhContext.setResult(Activity.RESULT_OK, backIntent)
                vhContext.finish()
            }
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}