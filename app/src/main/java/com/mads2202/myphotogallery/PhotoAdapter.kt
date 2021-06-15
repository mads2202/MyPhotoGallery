package com.mads2202.myphotogallery

import android.content.Context
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mads2202.myphotogallery.model.Photo
import com.mads2202.myphotogallery.model.PhotoLab

class PhotoAdapter(var photoArrayList:ArrayList<Photo>, var context: Context):RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    var itemClickListener:OnItemClickListener?=null
    public interface OnItemClickListener{
        fun onItemClick(view:View, position:Int)
    }
    inner class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView:AppCompatImageView
        init{
            imageView=itemView.findViewById(R.id.photo)
            imageView.setOnClickListener{v->
                itemClickListener?.onItemClick(v,adapterPosition)
            }
        }
        fun bind(photo:Photo){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                imageView.setImageBitmap(context.contentResolver.loadThumbnail(photo.path, Size(640,480),null))}
                else{
                    Glide.with(context).load(photo.path).thumbnail(0.2f).into(imageView)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(context).inflate(R.layout.photo_view,parent,false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(PhotoLab.photos[position])
    }

    override fun getItemCount(): Int {
        return photoArrayList.size
    }
}
