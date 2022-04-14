package com.example.githubusersandconverter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusersandconverter.databinding.CurrenciesItemRvBinding
import com.example.githubusersandconverter.interfaces.CurrenciesItemClick
import com.example.githubusersandconverter.models.Currencies

class CurrenciesAdapter(
    private val list: List<Currencies>,
    val flagsList: ArrayList<Int>,
    val itemClick: CurrenciesItemClick,
) :
    RecyclerView.Adapter<CurrenciesAdapter.VH>() {

    inner class VH(var binding: CurrenciesItemRvBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(CurrenciesItemRvBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list[position]
        val flags = flagsList[position]
        holder.binding.flagImage.setImageResource(flags)
        holder.binding.nameOfCourse.text = item.CcyNm_EN
        holder.binding.course.text = item.Rate

        holder.binding.root.setOnClickListener {
            itemClick.openBottomSheetDialog(item, flags)
        }
    }

    override fun getItemCount(): Int = list.size
}
