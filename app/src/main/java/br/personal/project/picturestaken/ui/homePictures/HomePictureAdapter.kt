package br.personal.project.picturestaken.ui.homePictures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.personal.project.picturestaken.data.model.Picture
import br.personal.project.picturestaken.databinding.ItemPhotoBinding

class HomePictureAdapter(
    private val pictures: MutableList<Picture>
) : RecyclerView.Adapter<HomePictureAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val picture = pictures[position]
//        holder.binding.imagePhoto.setImageResource(picture.)
    }

    override fun getItemCount() = pictures.size

}