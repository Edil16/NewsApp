package com.example.app41.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app41.databinding.ItemNewsBinding
import com.example.app41.models.News
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val onCLick: ((News) -> Unit)? = null
    var onLongCLick: ((position: Int) -> Unit)? = null

    inner class ViewHolder(private var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.data.text = getTodayDate()
            binding.textTitle.text = news.title
            binding.root.setOnLongClickListener {
                onLongCLick?.invoke(adapterPosition)
                return@setOnLongClickListener true
            }
        }
    }
    fun getTodayDate(): String {
        return SimpleDateFormat("hh:mm,dd MMMM yyyy", Locale.getDefault()).format(Date())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
    fun addItem(news: News) {
        list.add(0, news)
        notifyItemInserted(0)
    }

    fun getItem(pos: Int): News {
        return list[pos]
    }

    private var list = arrayListOf<News>()
    fun setList(one: ArrayList<News>) {
        this.list = one
        notifyDataSetChanged()
    }


}