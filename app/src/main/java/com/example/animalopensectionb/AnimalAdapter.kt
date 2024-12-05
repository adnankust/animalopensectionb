package com.example.animalopensectionb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(private val animalImages: List<Int>, private val context: Context) : RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {

    private var onItemClickListener: ((Int) -> Unit) ?= null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener{
                onItemClickListener?.invoke(adapterPosition)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_animal, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: AnimalAdapter.ViewHolder, position: Int) {
        holder.imageView.setImageResource(animalImages[position])
    }

    override fun getItemCount(): Int {
        return animalImages.size
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

}
