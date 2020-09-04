package com.example.tubmate.Constants

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.widget.Toast


class DownloadManagerClass(var ctx: Context) {

    var manager = ctx.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    private var downloadID : Long? = null

    fun download(nTitle: String, nDescription: String, nUrl: String){

        //get the manager

       var request = DownloadManager.Request((Uri.parse(nUrl))).apply {

            //set notification definition
            setDescription(nDescription)
            setTitle(nTitle)

           allowScanningByMediaScanner()
           setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

           setVisibleInDownloadsUi(true)
           setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$nTitle.mp4")
       }

        downloadID = manager.enqueue(request)





    }

}