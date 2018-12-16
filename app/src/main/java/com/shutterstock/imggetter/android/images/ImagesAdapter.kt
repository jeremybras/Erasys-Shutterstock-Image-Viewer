package com.shutterstock.imggetter.android.images

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.shutterstock.imggetter.R
import com.shutterstock.imggetter.core.images.Image
import com.squareup.picasso.Picasso
import timber.log.Timber

class ImagesAdapter(
    private var images: List<Image>,
    private val listener: ImageListener
) : RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_image, parent, false
        )
        return PhotoViewHolder(view, listener)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(images[position])
    }

    fun updateData(images: List<Image>) {
        this.images = images
        notifyDataSetChanged()
    }
}

class PhotoViewHolder(
    itemView: View,
    private val listener: ImageListener
) : RecyclerView.ViewHolder(itemView) {
    fun bind(image: Image) {
//        Timber.d(image.url)
        val imageView = itemView.findViewById<ImageView>(R.id.shutterStockImageView)

        Picasso.get().load(image.url).into(imageView)

        val ratio = String.format("%s:%s", image.width, image.height)
        Timber.d("Ratio $ratio")
        val set = ConstraintSet()
        set.clone(itemView.findViewById<ConstraintLayout>(R.id.shutterStockConstraintLayout))
        set.setDimensionRatio(imageView.id, ratio)
        set.applyTo(itemView.findViewById(R.id.shutterStockConstraintLayout))
        imageView.setOnClickListener { listener.onImageClicked(image) }
    }
}

interface ImageListener {
    fun onImageClicked(image: Image)
}
