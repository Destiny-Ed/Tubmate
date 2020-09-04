package com.example.tubmate.Constants.Receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if (DownloadManager.ACTION_NOTIFICATION_CLICKED == intent.action){
            //cancel download
        }
    }
}
