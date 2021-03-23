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
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.personal.project.picturestaken.R
import br.personal.project.picturestaken.data.model.Picture
import br.personal.project.picturestaken.databinding.FragmentHomePicturesBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomePicturesFragment : Fragment() {
    private lateinit var binding: FragmentHomePicturesBinding
    private val adapterPicture: HomePictureAdapter by inject()
    private val viewModel: HomePictureViewModel by viewModel()

    private lateinit var bottomSheet: BottomSheetBehavior<ConstraintLayout>

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
        bottomSheet = BottomSheetBehavior.from(binding.constraintLayout)
        setStateBottomSheet(BottomSheetBehavior.STATE_HIDDEN)
        setupListener()
        setupObserver()
        viewModel.getPicturesCurated()
    }

    private fun setupObserver() {
        viewModel.photosLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = false
            it.run(adapterPicture::addPictures)
        }

        viewModel.colorLiveData.observe(viewLifecycleOwner) {
            adapterPicture.clear()
            setStateBottomSheet(BottomSheetBehavior.STATE_HIDDEN)
        }

    }

    private fun setupListener() {
        adapterPicture.setOnclick(this::showName)

        binding.recyclerPhotos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerPhotos.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.nextPictures()
                }
            }
        })

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search_color -> {
                    setStateBottomSheet(BottomSheetBehavior.STATE_EXPANDED)
                    true
                }
                else -> false
            }
        }

        binding.searchImage.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapterPicture.clear()
                viewModel.findPictureByName()
                keyBoardHide()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setQuery(newText)
                return false
            }
        })

        binding.swipeRefresh.setOnRefreshListener {
            clearSearchView()
            adapterPicture.clear()
            viewModel.getPicturesCurated()
        }
    }

    private fun clearSearchView() {
        binding.searchImage.apply {
            setQuery("", false)
            clearFocus()
            isIconified = true
        }
    }

    private fun showName(picture: Picture, imageView: ImageView) {
        val extras = FragmentNavigatorExtras(imageView to picture.src.large)
        val action =
            HomePicturesFragmentDirections.actionHomePicturesFragmentToDetailsPictureFragment(
                picture
            )
        findNavController().navigate(action, extras)
    }

    private fun setStateBottomSheet(state: Int) {
        bottomSheet.state = state
    }

    private fun keyBoardHide() {
        this.activity?.currentFocus?.let {
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}