package com.leadercc.lxsvgaplayermaster

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leadercc.lxsvgaplayer.*
import java.io.File
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {

    var animationView: SVGAImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animationView = SVGAImageView(this)
        animationView?.setBackgroundColor(Color.GRAY)
        setContentView(animationView)
        loadAnimation()
    }

    private fun loadAnimation() {
        try {
            val parser = SVGAParser(this)
            parser.decodeFromURL(
                URL("http://192.168.1.5:9000/live/admin_gift/e783674c161d4154e83ccfaf5d5e50cf.svga"),
                object: SVGAParser.ParseCompletion{
                    override fun onComplete(videoItem: SVGAVideoEntity) {
                        val dynamicEntity = SVGADynamicEntity()
                        val drawable = SVGADrawable(videoItem, dynamicEntity)
                        animationView?.setImageDrawable(drawable)
                        animationView?.startAnimation()
                    }

                    override fun onError() {

                    }
                },
                object: SVGAParser.PlayCallback{
                    override fun onPlay(file: List<File>) {

                    }
                }
            )
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
    }
}