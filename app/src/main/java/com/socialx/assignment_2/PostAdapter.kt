package com.socialx.assignment_2

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class PostAdapter(private val postModel: MutableList<PostModel>) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.bindView(postModel[position])
    }

    override fun getItemCount(): Int {
        return 10
    }
}



class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val tvUserId : TextView = itemView.findViewById(R.id.userId)
    private val tvId : TextView = itemView.findViewById(R.id.id)
    private val tvTitle : TextView = itemView.findViewById(R.id.title_tv)
    private val tvBody : TextView = itemView.findViewById(R.id.body_tv)


    fun bindView(postModel: PostModel) {
        tvUserId.text = postModel.userId.toString()
        tvId.text = postModel.id.toString()
        tvTitle.text = postModel.title
        tvBody.text = postModel.body
    }
}