package com.example.app41

import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app41.databinding.PagerBoardBinding

class BoardAdapter(private val onCLickStart: () -> Unit) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    private val image = arrayOf(R.raw.lottieone,R.raw.lottieone,R.raw.lottieone)
    private val titles = arrayOf("Салам", "Привет", "Hello")

    inner class ViewHolder(private var binding: PagerBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.imageView.setAnimation(image[position])
            binding.textTitle.text = titles[position]
            if (position == titles.size - 1)
                binding.btnStart.visibility = View.VISIBLE
            else
                binding.btnStart.visibility = View.INVISIBLE
            binding.btnStart.setOnClickListener{
                onCLickStart.invoke()
            }

            binding.textTitle.text = titles[position]
            if (position == titles.size - 1)
                binding.textSkip.visibility = View.INVISIBLE
            else
                binding.textSkip.visibility = View.VISIBLE
            binding.textSkip.setOnClickListener{

            }
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                PagerBoardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount() = titles.size

    }
