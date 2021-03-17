package br.personal.project.picturestaken.ui.homePictures

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import br.personal.project.picturestaken.data.model.Picture
import br.personal.project.picturestaken.databinding.FragmentHomePicturesBinding
import br.personal.project.picturestaken.repository.PictureRepository


class HomePicturesFragment : Fragment() {
    private lateinit var binding: FragmentHomePicturesBinding

    private val adapterPicture by lazy {
        HomePictureAdapter()
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this, HomePicturesViewModelFactory(PictureRepository())
        ).get(HomePictureViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePicturesBinding.inflate(
            inflater,
            container,
            false
        )
        binding.adapterPhoto = adapterPicture
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterPicture.setOnclick(this::showName)
        listener()
//        viewModel.findPictureByName("car")

        viewModel.photosLiveData.observe(viewLifecycleOwner, {
            it.let(adapterPicture::addPictures)
        })

    }

    private fun listener() {
        binding.searchImage.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let(viewModel::findPictureByName)
                keyBoardHide()
                return true
            }

            override fun onQueryTextChange(newText: String?) = false
        })
    }

    private fun showName(picture: Picture) {
        Toast.makeText(requireContext(), picture.photographer, Toast.LENGTH_SHORT).show()
    }

    private fun keyBoardHide() {
        this.activity?.currentFocus?.let {
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}