package com.selimabbas.photoalbum

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.selimabbas.album.AlbumActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startAlbumActivity()
    }

    /**
     * Start an activity showing a photo album.
     */
    private fun startAlbumActivity() {
        startActivity(Intent(this, AlbumActivity::class.java))
        finish()
    }
}
