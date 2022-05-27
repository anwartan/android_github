package com.example.aplikasigithubuser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasigithubuser.R
import com.example.aplikasigithubuser.model.User

class UserRecyclerAdapter(private val users: List<User>, private val listener: (User) -> Unit) :RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val txtName: TextView = view.findViewById(R.id.tv_name)
        private val txtUsername: TextView = view.findViewById(R.id.tv_username)
        private val imgAvatar: ImageView = view.findViewById(R.id.img_avatar)
        private val txtGithub: TextView = view.findViewById(R.id.tv_github)

        fun bind(user: User) {
            txtName.text = user.name?:user.login
            txtUsername.text = user.login
            txtGithub.text = user.url
            Glide.with(itemView)
                .load(user.avatarUrl)
                .into(imgAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = users[position]
        holder.bind(item)

        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int {
        return users.size
    }


}