package br.personal.project.picturestaken.ui.homePictures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.personal.project.picturestaken.data.model.Picture
import br.personal.project.picturestaken.databinding.ItemPhotoBinding

class HomePictureAdapter : RecyclerView.Adapter<HomePictureAdapter.ViewHolder>() {

    private var pictures = mutableListOf<Picture>()
    private var onClick: ((Picture) -> Unit)? = null

    fun addPictures(photos: MutableList<Picture>) {
        pictures = photos
        notifyDataSetChanged()
    }

    fun setOnclick(action: (Picture) -> Unit){
        onClick = action
    }

    class ViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(picture: Picture) {
            binding.picture = picture
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val picture = pictures[position]
        holder.binding.imagePhoto.setOnClickListener {
            onClick?.invoke(picture)
        }
        holder.bind(picture)
    }

    override fun getItemCount() = pictures.size

}