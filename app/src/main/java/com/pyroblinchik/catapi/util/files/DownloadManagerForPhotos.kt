package com.pyroblinchik.catapi.util.files

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.FileProvider
import com.pyroblinchik.catapi.domain.base.models.Breed
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class DownloadManagerForPhotos(
    val context: Context,
    private val breed: Breed
) {

    fun downloadImage(imageUrl: String) {
        val request = DownloadManager.Request(Uri.parse(imageUrl))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle("${breed.name} Cat Image Download")
        request.setDescription("Downloading")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,  "${breed.name} cat image.jpg")

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }
}