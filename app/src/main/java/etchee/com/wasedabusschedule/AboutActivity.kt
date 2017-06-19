package etchee.com.wasedabusschedule

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_about.*

/**
 * Created by rikutoechigoya on 2017/06/19.
 */
class AboutActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_about)

        val imageView = findViewById(R.id.about_imageView) as ImageView
        Glide.with(applicationContext)
                .load(R.mipmap.ic_launcher_round)
                .into(imageView)

        setSupportActionBar(about_toolBar)
    }
}