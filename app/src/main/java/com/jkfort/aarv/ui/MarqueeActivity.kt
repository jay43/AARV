package com.jkfort.aarv.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jkfort.aarv.R
import com.jkfort.aarv.adapters.MarqueeRecyclerAdapter
import kotlinx.android.synthetic.main.activity_marquee.*
import java.util.*

class MarqueeActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val i = Intent(context, MarqueeActivity::class.java)
            context.startActivity(i)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marquee)

        setUpRecyclerView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation =
                LinearLayoutManager.VERTICAL // horizontal or vertical setting is
        rvMarquee.layoutManager = layoutManager
        val adapter = MarqueeRecyclerAdapter(getData()) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
        rvMarquee.adapter = adapter
        rvMarquee.setOnTouchListener { _, event -> // Interpret MotionEvent data
            if (event.action == MotionEvent.ACTION_DOWN) {
                rvMarquee.stopMarquee()
            } else if (event.action == MotionEvent.ACTION_UP) {
                Handler(Looper.getMainLooper()).postDelayed({
                    rvMarquee.startMarquee()
                }, 2000)
            }
            false
        }
    }

    private fun getData(): List<String> {
        val data = ArrayList<String>()
        for (i in 0..9) {
            data.add("$i article")
        }
        return data
    }
}