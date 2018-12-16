package com.shutterstock.imggetter.android.categories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shutterstock.imggetter.R

class CategoriesAdapter(
    private var categories: List<CategoryModel>,
    private val listener: CategoryListener
) : RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_category, parent, false
        )
        return CategoryViewHolder(view, listener)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    fun updateData(categories: List<CategoryModel>) {
        this.categories = categories
        notifyDataSetChanged()
    }
}

class CategoryViewHolder(
    itemView: View,
    private val listener: CategoryListener
) : RecyclerView.ViewHolder(itemView) {
    fun bind(category: CategoryModel) {
        with(itemView.findViewById<TextView>(R.id.categoryTitleTextView)) {
            text = category.name
            setOnClickListener {
                listener.onCategoryClicked(category)
            }
        }
    }
}

interface CategoryListener {
    fun onCategoryClicked(category: CategoryModel)
}
