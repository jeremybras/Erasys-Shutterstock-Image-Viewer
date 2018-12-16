package com.shutterstock.imggetter.android.categories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.shutterstock.imggetter.R
import com.shutterstock.imggetter.android.ImgGetterApplication
import com.shutterstock.imggetter.android.ViewModelFactory
import com.shutterstock.imggetter.android.images.ImagesActivity
import kotlinx.android.synthetic.main.activity_categories.*
import javax.inject.Inject

class CategoriesActivity : AppCompatActivity(), CategoryListener {

    companion object {
        private const val DISPLAY_CATEGORIES = 1
        private const val DISPLAY_ERROR = 2
    }

    @Inject lateinit var factory: ViewModelFactory<CategoriesViewModel>
    private lateinit var viewModel: CategoriesViewModel
    private lateinit var adapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        ImgGetterApplication.getComponent(this).plus(CategoriesModule()).inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(CategoriesViewModel::class.java)
        setContentView(R.layout.activity_categories)

        adapter = CategoriesAdapter(emptyList(), this)
        categoriesRecyclerView.layoutManager = LinearLayoutManager(this)
        categoriesRecyclerView.adapter = adapter

        initializeViewModelObservers()
    }

    override fun onCategoryClicked(category: CategoryModel) {
        Toast.makeText(this, "Displaying category #${category.id}", Toast.LENGTH_LONG).show()
        startActivity(ImagesActivity.newIntent(this, category.id))
    }

    private fun initializeViewModelObservers() {
        viewModel.categories.observe(this, Observer { categories ->
            categories?.let {
                contentsViewFlipper.displayedChild = DISPLAY_CATEGORIES
                adapter.updateData(it)
                Toast.makeText(this, "${it.size} categories", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.error.observe(this, Observer { it ->
            errorTextView.text = it?.let {
                resources.getString(it)
            } ?: resources.getString(R.string.repository_exception)
            contentsViewFlipper.displayedChild = DISPLAY_ERROR
        })
    }
}
