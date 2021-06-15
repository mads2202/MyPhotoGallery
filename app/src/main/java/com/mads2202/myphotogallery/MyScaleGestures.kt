package com.mads2202.myphotogallery

import android.content.Context
import android.graphics.Point
import android.view.*
import android.view.GestureDetector.OnDoubleTapListener
import android.view.ScaleGestureDetector.OnScaleGestureListener
import android.view.View.OnTouchListener


class MyScaleGestures(c: Context?) : OnTouchListener,
    GestureDetector.OnGestureListener, OnDoubleTapListener, OnScaleGestureListener {
    private var view: View? = null
    private val gesture: GestureDetector
    private val gestureScale: ScaleGestureDetector
    private var scaleFactor = 1f
    private var inScale = false
    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        this.view = view
        gesture.onTouchEvent(event)
        gestureScale.onTouchEvent(event)
        return true
    }

    override fun onDown(event: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(event1: MotionEvent?, event2: MotionEvent?, x: Float, y: Float): Boolean {
        return true
    }

    override fun onLongPress(event: MotionEvent?) {}
    override fun onScroll(event1: MotionEvent?, event2: MotionEvent?, x: Float, y: Float): Boolean {
        var newX = view!!.x
        var newY = view!!.y
        if (!inScale) {
            newX -= x
            newY -= y
        }
        val wm = view!!.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val d = wm.defaultDisplay
        val p = Point()
        d.getSize(p)
        if (newX > (view!!.width * scaleFactor - p.x) / 2) {
            newX = (view!!.width * scaleFactor - p.x) / 2
        } else if (newX < -((view!!.width * scaleFactor - p.x) / 2)) {
            newX = -((view!!.width * scaleFactor - p.x) / 2)
        }
        if (newY > (view!!.height * scaleFactor - p.y) / 2) {
            newY = (view!!.height * scaleFactor - p.y) / 2
        } else if (newY < -((view!!.height * scaleFactor - p.y) / 2)) {
            newY = -((view!!.height * scaleFactor - p.y) / 2)
        }
        if(view!!.scaleX!=1.0f && view!!.scaleY!=1.0f){
        view!!.x = newX
        view!!.y = newY}
        return true
    }

    override fun onShowPress(event: MotionEvent?) {}
    override fun onSingleTapUp(event: MotionEvent?): Boolean {
        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
//        view!!.scaleX = 1f
//        view!!.scaleY = 1f как раместить картинку после этого как она была?
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        return true
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        return true
    }

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        scaleFactor *= detector.scaleFactor
        scaleFactor =
            if (scaleFactor < 1) 1f else scaleFactor // prevent our image from becoming too small
        scaleFactor = (scaleFactor * 100).toInt()
            .toFloat() / 100 // Change precision to help with jitter when user just rests their fingers //
        view!!.scaleX = scaleFactor
        view!!.scaleY = scaleFactor
        onScroll(null, null, 0f, 0f) // call scroll to make sure our bounds are still ok //
        return true
    }

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        inScale = true
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector) {
        inScale = false
        // call scroll to make sure our bounds are still ok //
    }

    init {
        gesture = GestureDetector(c, this)
        gestureScale = ScaleGestureDetector(c, this)
    }
}