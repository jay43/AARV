package com.jkfort.aarv.custom

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.atomic.AtomicBoolean

class MarqueeRecyclerView : RecyclerView {
    @Volatile
    var thread: Thread? = null
    var shouldContinue = AtomicBoolean(false)
    var mHandler: Handler? = null

    constructor(context: Context) : super(context)
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle)

    private fun init() {
        // main thread handler, used to scroll through the message execution Marquee
        mHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    1 ->                         //MarqueeRecyclerView.this.scrollBy(5, 30);
                        this@MarqueeRecyclerView.smoothScrollBy(5, 20)
                }
            }
        }
        if (thread == null) {
            thread = object : Thread() {
                override fun run() {
                    while (shouldContinue.get()) {
                        try {
                            sleep(200)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        if (mHandler != null) {
                            val msg = (mHandler as Handler).obtainMessage()
                            msg.what = 1
                            msg.sendToTarget()
                        }
                    }
                    mHandler = null
                }
            }
            thread!!.start()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startMarquee()
    }

    fun startMarquee() {
        shouldContinue.set(true)
        init()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopMarquee()
    }

    /**
     * Stop scrolling
     */
    fun stopMarquee() {
        shouldContinue.set(false)
        thread = null
    }
}