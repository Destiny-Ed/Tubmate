package com.example.tubmate.Constants

import android.annotation.SuppressLint
import android.content.Context
import android.util.SparseArray
import android.widget.Toast
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile

class youtubeExtractorClass {

    fun download(url : String, ctx : Context, title : String) {
        @SuppressLint("StaticFieldLeak")
        val ytExtractor = object : YouTubeExtractor(ctx) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {

                if(ytFiles != null) {
                    try {
                        val downloadUrl = ytFiles.get(22).url

                        DownloadManagerClass(ctx).download(title, "Downloading...", downloadUrl)
                        Toast.makeText(ctx, "Video is downloading... Slide down to see progress", Toast.LENGTH_SHORT).show()
                    }catch (e:Exception){
                        Toast.makeText(ctx, "Can't download this video", Toast.LENGTH_SHORT).show()
                    }


                }else{
                    Toast.makeText(
                        ctx,
                        "Click again after clicking a video",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
        ytExtractor.extract(url, true, true)
    }
}