package com.jkfort.aarv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jkfort.aarv.ui.MarqueeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMarquee.setOnClickListener {
            MarqueeActivity.start(this)
        }
    }
}