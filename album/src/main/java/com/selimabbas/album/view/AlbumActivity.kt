package com.selimabbas.album.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.selimabbas.album.adapter.PhotoAdapter
import kotlinx.android.synthetic.main.activity_album.*
import org.koin.androidx.viewmodel.ext.android.viewModel



class AlbumActivity : AppCompatActivity() {
    private val vm: AlbumViewModel by viewModel()

    private val adapter = PhotoAdapter(

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.selimabbas.album.R.layout.activity_album)
        initRecyclerView()
        observePhotos()
        observeError()
        setupSwipeForRefresh()
    }

    /**
     * Create RecyclerView adapter.
     */
    private fun initRecyclerView() {
        photoRecyclerView.layoutManager = GridLayoutManager(this, 2)
        photoRecyclerView.adapter = adapter
    }

    /**
     * Observe photo list LiveData.
     */
    private fun observePhotos() {
        vm.photos.observe(this, Observer {
            swipeRefresh.isRefreshing = false
            adapter.submitList(it)
        })
    }

    /**
     * Observe error and show SnackBar in case of an error.
     */
    private fun observeError() {
        vm.errorMessage.observe(this, Observer {
            swipeRefresh.isRefreshing = false
            it.getContentIfNotHandled()?.let { textId ->
                Snackbar.make(content, getString(textId), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(com.selimabbas.album.R.string.retry)) { vm.refreshPhotos() }
                    .show()
            }
        })
    }

    /**
     * Setup swipe for refresh listener.
     */
    private fun setupSwipeForRefresh() {
        swipeRefresh.setOnRefreshListener { vm.refreshPhotos() }
    }

}