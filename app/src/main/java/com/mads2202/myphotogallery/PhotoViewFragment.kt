package com.mads2202.myphotogallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mads2202.myphotogallery.model.Photo

class PhotoViewFragment:Fragment() {
    companion object{
        fun newInstance(photo: Photo):PhotoViewFragment {
            val args = Bundle()
            args.putString(URI,photo.path.toString())
            val fragment = PhotoViewFragment()
            fragment.arguments = args
            return fragment
        }
        const val URI="URI"
    }
    lateinit var imageView:AppCompatImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.photo_view,container, false)
        imageView=view.findViewById(R.id.photo)
        imageView.setOnTouchListener(MyScaleGestures(requireActivity()))
        Glide.with(requireActivity()).load(arguments?.getString(URI)).into(imageView)
        return view
    }

}