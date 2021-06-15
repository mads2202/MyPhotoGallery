package com.mads2202.myphotogallery

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mads2202.myphotogallery.model.PhotoLab
import java.text.FieldPosition

class PagerFragment : Fragment() {
    companion object {
        fun newInstance(position:Int): PagerFragment {
            val args = Bundle()
            args.putInt(POSITION,position)
            val fragment = PagerFragment()
            fragment.arguments = args
            return fragment
        }
        const val POSITION="Position"
    }

    lateinit var viewPager: ViewPager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pager_fragment, container, false)
        viewPager = view.findViewById(R.id.pager)
        var adapter=object : FragmentStatePagerAdapter(requireActivity().supportFragmentManager){
            override fun getCount(): Int {
                return PhotoLab.photos.size
            }

            override fun getItem(position: Int): Fragment {
                return PhotoViewFragment.newInstance(PhotoLab.photos[position])
            }

        }
        viewPager.adapter=adapter
        arguments?.getInt(POSITION)?.let { viewPager.currentItem = it }
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.pager_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share->{
                val intent=Intent()
                intent.action=Intent.ACTION_SEND
                intent.setDataAndType(PhotoLab.photos[viewPager.currentItem].path,"image/*")
                intent.putExtra(Intent.EXTRA_STREAM,PhotoLab.photos[viewPager.currentItem].path)
                startActivity(Intent.createChooser(intent, "Share image with"));
                return true
            }
            else->return super.onOptionsItemSelected(item)
        }

    }
}