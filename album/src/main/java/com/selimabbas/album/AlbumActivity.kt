package com.selimabbas.album

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumActivity : AppCompatActivity() {
    /*
        private val snackBar by lazy {
            SnackBar
        }
    */
    private val vm: AlbumViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observePhotos()
    }

    private fun observePhotos() {
        vm.photos.observe(this, Observer {
        })
    }


}
