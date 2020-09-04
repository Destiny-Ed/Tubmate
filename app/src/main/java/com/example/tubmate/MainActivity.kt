package com.example.tubmate

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.widget.Toast
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.tubmate.Constants.DownloadManagerClass
import com.example.tubmate.Constants.Links
import com.example.tubmate.Constants.webViewClient
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var backPressed = 0L


    @SuppressLint("SetJavaScriptEnabled") // disable ssl vulnerability warning
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Remove actionBar
        supportActionBar!!.hide()


        webView.loadUrl(Links.youTubeLink)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        //Enable webViewClient to load current page url inside the app
        webView.webViewClient = webViewClient(this, progress, fabDownload)


        if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(true)){
            Toast.makeText(this, "Notification clicked", Toast.LENGTH_SHORT).show()
        }


        fabCancel.setOnClickListener {
            AlertDialog.Builder(this).apply {
                var root = layoutInflater.inflate(R.layout.about, null, false)

                setView(root)

            }.create().show()
        }



        //download Video
        fabDownload.setOnClickListener {

            val title = webView.title
            var url = webView.url

            @SuppressLint("StaticFieldLeak")
            val ytExtractor = object : YouTubeExtractor(this) {
                override fun onExtractionComplete(
                    ytFiles: SparseArray<YtFile>?,
                    videoMeta: VideoMeta?
                ) {
//1536
                    if(ytFiles != null) {

                        // 720, 1080, 480
//                        var iTags = listOf(22, 137, 18);

                            val downloadUrl = ytFiles.get(18).url

                            DownloadManagerClass(baseContext).download(title!!, "Downloading...", downloadUrl)
                            Toast.makeText(baseContext, "Video is downloading... Slide down to see progress", Toast.LENGTH_LONG).show()

                        fabCancel.visibility = View.VISIBLE
                        //enable cancel btn


                    }else{
                        Toast.makeText(
                            baseContext,
                            "Please select a video to download",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }
            ytExtractor.extract(url, true, true)

        }

    }

    override fun onBackPressed() {
        if (webView.canGoBack())
        {
            webView.goBack()
        }
        else
        {
            //show confirmation before exits
            if (backPressed + 2000 > System.currentTimeMillis()){
                super.onBackPressed()
                return
            }
            else{
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            }
            //reset current time
            backPressed = System.currentTimeMillis()

        }
    }
}