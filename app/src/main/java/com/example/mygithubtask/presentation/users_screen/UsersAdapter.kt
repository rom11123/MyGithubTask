package com.example.mygithubtask.presentation.users_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubtask.data.model.Users
import com.example.mygithubtask.databinding.ItemUsersBinding

class UsersAdapter: ListAdapter<Users, UsersAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Users>() {
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class ViewHolder(private var binding: ItemUsersBinding):
        RecyclerView.ViewHolder(binding.root){


        fun bind(users: Users){
            binding.usersLogin.text = users.login
            Glide.with(binding.usersImage.context)
                .load(users.avatarUrl)
                .into(binding.usersImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users = getItem(position)
        holder.bind(users)
        holder.itemView.setOnClickListener {  onItemClickListener?.let { it(users) }
       }
    }

    private var onItemClickListener: ((Users) -> Unit)? = null

    fun setOnItemClickListener(listener: (Users) -> Unit) {
        onItemClickListener = listener
    }
}