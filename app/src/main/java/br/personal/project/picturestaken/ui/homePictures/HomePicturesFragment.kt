package br.personal.project.picturestaken.ui.homePictures

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.personal.project.picturestaken.data.model.Picture
import br.personal.project.picturestaken.databinding.FragmentHomePicturesBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomePicturesFragment : Fragment() {
    private lateinit var binding: FragmentHomePicturesBinding
    private lateinit var adapterPicture: HomePictureAdapter
    private val viewModel: HomePictureViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePicturesBinding.inflate(inflater, container, false)
        with(binding) {
            lifecycleOwner = this@HomePicturesFragment
            viewModelPicture = viewModel
            adapterPhoto = adapterPicture
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupObserver()
//        viewModel.getPicturesCurated()
    }

    private fun setupObserver() {
        viewModel.photosLiveData.observe(viewLifecycleOwner, {
            it.run(adapterPicture::addPictures)
        })
    }

    private fun setupListener() {
        adapterPicture.setOnclick(this::showName)

        binding.recyclerPhotos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerPhotos.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.refreshPictures()
                }
            }
        })

        binding.searchImage.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.findPictureByName()
                keyBoardHide()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setQuery(newText)
                return false
            }
        })
    }

    private fun showName(picture: Picture, imageView: ImageView) {
        val extras = FragmentNavigatorExtras(imageView to picture.src.large)
        val action =
            HomePicturesFragmentDirections.actionHomePicturesFragmentToDetailsPictureFragment(
                picture
            )
        findNavController().navigate(action, extras)
    }

    private fun keyBoardHide() {
        this.activity?.currentFocus?.let {
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}