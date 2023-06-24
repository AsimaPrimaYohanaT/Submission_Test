package com.example.suitmedia_test.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmedia_test.databinding.ItemUserBinding
import com.example.suitmedia_test.response.DataItem
import com.example.suitmedia_test.ui.MainActivity
import java.sql.Timestamp

class UserPagingAdapter: PagingDataAdapter<DataItem, UserPagingAdapter.MyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class MyViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            Glide.with(itemView)
                .load(data.avatar)
                .circleCrop()
                .into(binding.ivPhoto)
            binding.firstName.text = data.firstName
            binding.lastName.text = data.lastName
            binding.email.text = data.email
            binding.root.setOnClickListener{
                data.firstName?.let { it1 -> addUsername(itemView.context, it1) }
            }
        }

        //save username to preference
        private fun addUsername(context: Context, name: String) {
            if (!name.isNullOrEmpty()) {
                val sharedPreferences = context.getSharedPreferences(
                    MainActivity.SHARED_PREFERENCES,
                    Context.MODE_PRIVATE
                )
                val editor = sharedPreferences.edit()
                editor.putString(MainActivity.USERNAME, name)
                editor.apply()
            }
        }
    }


    object DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }
}