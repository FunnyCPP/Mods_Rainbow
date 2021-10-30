package com.kiienkoromaniuk.mods32

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import com.iammert.tileprogressview.TiledProgressView

class SplashActivityMods32fig : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_mods32fig)
        val progressBarMods32fig: TiledProgressView =findViewById(R.id.progressBar_mods32fig)
        progressBarMods32fig.setColorRes(R.color.white)
        progressBarMods32fig.setLoadingColorRes(R.color.blue)


        var iMods32fig=0
        val handlerMods32fig: Handler = Handler()
        Thread(Runnable {
            while (iMods32fig < 100) {
                iMods32fig++
                handlerMods32fig.post {
                    progressBarMods32fig.setProgress(iMods32fig.toFloat())
                    if(iMods32fig==100)
                    {
                        val intent = Intent(this, HomeActivityMods32fig::class.java)
                        startActivity(intent)
                    }
                }

                try {
                    Thread.sleep(30)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }).start()

    }
}