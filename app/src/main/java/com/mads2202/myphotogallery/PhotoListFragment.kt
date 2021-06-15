package com.mads2202.myphotogallery

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mads2202.myphotogallery.model.PhotoLab

class PhotoListFragment : Fragment() {
    companion object {
        fun newInstance(): PhotoListFragment {
            val args = Bundle()
            val fragment = PhotoListFragment()
            fragment.arguments = args
            return fragment
        }

        const val TAG = "PhotoListFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.photo_list_fragment, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        var adapter = PhotoAdapter(PhotoLab.photos, requireActivity())
        adapter.itemClickListener = object : PhotoAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                requireActivity().supportFragmentManager.beginTransaction().addToBackStack("PagerFragment")
                    .replace(R.id.fragment_container, PagerFragment.newInstance(position)).commit()
            }

        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), 3)
        val dividerItemDecoration =
            DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider, null))
        recyclerView.addItemDecoration(dividerItemDecoration)
        return view
        Log.d(TAG, "onCreateView")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}