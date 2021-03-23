package br.personal.project.picturestaken.ui.details

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import br.personal.project.picturestaken.databinding.FragmentDetailsPictureBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class DetailsPictureFragment : Fragment() {

    private lateinit var binding: FragmentDetailsPictureBinding
    private val args: DetailsPictureFragmentArgs by navArgs()
    private val detailsViewModel: DetailsPictureViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsPictureBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = this@DetailsPictureFragment
            viewModel = detailsViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pictureDetails = args.pictureDetails
        detailsViewModel.setPicture(pictureDetails)
        loadImageDetails(pictureDetails.src.large)
    }

    private fun loadImageDetails(uri: String) {

        binding.cardImageDetails.setCardBackgroundColor(Color.parseColor(args.pictureDetails.avg_color))
        binding.imageDetails.apply {
            transitionName = uri
            Picasso.get()
                .load(args.pictureDetails.src.landscape)
                .fit()
                .into(this, loadedImageCallback())
        }
    }

    private fun loadedImageCallback() = object : Callback {
        override fun onSuccess() {
            detailsViewModel.setShowProgress(false)
        }

        override fun onError(e: Exception?) {
            TODO("Not yet implemented")
        }
    }


}