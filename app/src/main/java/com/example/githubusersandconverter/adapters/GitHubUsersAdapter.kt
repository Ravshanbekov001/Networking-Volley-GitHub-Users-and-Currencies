package com.example.githubusersandconverter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusersandconverter.databinding.GitHubUsersItemRvBinding
import com.example.githubusersandconverter.models.GitHubUsers

class GitHubUsersAdapter(private val list: List<GitHubUsers>) :
    RecyclerView.Adapter<GitHubUsersAdapter.VH>() {

    inner class VH(var binding: GitHubUsersItemRvBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(GitHubUsersItemRvBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list[position]
        holder.binding.nameOfUser.text = item.login
        Glide.with(holder.binding.root).load(item.avatar_url).into(holder.binding.userLogoImage)

    }

    override fun getItemCount(): Int = list.size
}
