package com.example.tubmate.Constants

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.example.tubmate.ErrorPage
import com.google.android.material.floatingactionbutton.FloatingActionButton

class webViewClient(var ctx : Context, var progressBar: ProgressBar, var downloadBtn: FloatingActionButton) : WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        //Loads while page is loading
        //show progress dialog
        progressBar.visibility = View.VISIBLE


    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        //loads after page is finished loading
        //dismiss progress dialog
        progressBar.visibility = View.GONE
        downloadBtn.visibility = View.VISIBLE


    }

    override fun onReceivedError(
        view: WebView?,
        errorCode: Int,
        description: String?,
        failingUrl: String?
    ) {
        super.onReceivedError(view, errorCode, description, failingUrl)
        //show error like internet error && dismiss progress bar
        progressBar.visibility = View.GONE
        downloadBtn.visibility = View.GONE

        ctx.startActivity(Intent(ctx, ErrorPage::class.java))


    }

}
//done 45