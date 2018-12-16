package com.shutterstock.imggetter.android.images

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.shutterstock.imggetter.R
import com.shutterstock.imggetter.android.ImgGetterApplication
import com.shutterstock.imggetter.android.ViewModelFactory
import com.shutterstock.imggetter.core.images.Image
import kotlinx.android.synthetic.main.activity_images.*
import javax.inject.Inject


class ImagesActivity : AppCompatActivity(), ImageListener, OnBottomReachedListener {

    @Inject lateinit var factory: ViewModelFactory<ImagesViewModel>
    private lateinit var viewModel: ImagesViewModel
    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var queryAdapter: ImagesAdapter

    companion object {
        private const val DISPLAY_IMAGES = 0
        private const val DISPLAY_QUERY = 1
        private const val EXTRA_CATEGORY = "EXTRA_CATEGORY"

        fun newIntent(context: Context, category: String): Intent {
            return Intent(context, ImagesActivity::class.java).apply {
                putExtra(EXTRA_CATEGORY, category)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImgGetterApplication.getComponent(this).plus(ImagesModule()).inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(ImagesViewModel::class.java)
        setContentView(R.layout.activity_images)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        imagesAdapter = ImagesAdapter(emptyList(), this, this)
        imagesRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        imagesRecyclerView.adapter = imagesAdapter

        queryAdapter = ImagesAdapter(emptyList(), this, this)
        queryRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        queryRecyclerView.adapter = queryAdapter

        intent.getStringExtra(EXTRA_CATEGORY)?.let { category ->
            viewModel.setCategory(category)
            viewModel.loadImages()
        }

        initializeViewModelObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_images, menu)
        val item = menu.findItem(R.id.search)
        (item?.actionView as SearchView).setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                modeViewFlipper.displayedChild = DISPLAY_QUERY
                viewModel.query(query)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
        item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                toolbar.setBackgroundColor(Color.WHITE)
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                toolbar.setBackgroundColor(
                    ContextCompat.getColor(baseContext, R.color.colorPrimary)
                )
                modeViewFlipper.displayedChild = DISPLAY_IMAGES
                viewModel.loadImages(true)
                return true
            }
        })
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onImageClicked(image: Image) {
        Toast.makeText(this, "Clicked on image ${image.id}", Toast.LENGTH_LONG).show()
    }

    override fun onBottomReached() {
        if (isInQueryMode()) {
            viewModel.loadQuery()
        } else {
            viewModel.loadImages()
        }
    }

    private fun initializeViewModelObservers() {
        viewModel.images.observe(this, Observer {
            it?.let { images ->
                if (isInQueryMode()) {
                    queryAdapter.updateData(images)
                } else {
                    imagesAdapter.updateData(images)
                }
            }
        })
    }

    private fun isInQueryMode() = modeViewFlipper.displayedChild == DISPLAY_QUERY
}
