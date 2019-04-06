package com.yusufborucu.volleysample.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yusufborucu.volleysample.R
import com.yusufborucu.volleysample.model.User
import kotlinx.android.synthetic.main.list_item.view.*

class UserAdapter(private val users : MutableList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return UserViewHolder(v)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun add(item:User, position:Int) {
        users.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(item:User) {
        val position = users.indexOf(item)
        users.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.itemView.tvName.text = user.name
        holder.itemView.tvEmail.text = user.email
        holder.itemView.tvPhone.text = user.phone
    }


    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}