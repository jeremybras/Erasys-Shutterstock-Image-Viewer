package com.shutterstock.imggetter.android.images

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
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
    private val onClickListener: ImageListener,
    private val onBottomListener: OnBottomReachedListener
) : androidx.recyclerview.widget.RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_image, parent, false
        )
        return PhotoViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(images[position])
        if (position == images.size - 10) {
            onBottomListener.onBottomReached()
        }
    }

    fun updateData(images: List<Image>) {
        this.images = images
        notifyDataSetChanged()
    }
}

class PhotoViewHolder(
    itemView: View,
    private val listener: ImageListener
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    fun bind(image: Image) {
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

interface OnBottomReachedListener {
    fun onBottomReached()
}
