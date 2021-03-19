package br.personal.project.picturestaken.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import br.personal.project.picturestaken.R
import br.personal.project.picturestaken.databinding.FragmentDetailsPictureBinding
import com.squareup.picasso.Picasso

class DetailsPictureFragment : Fragment() {

    private lateinit var binding: FragmentDetailsPictureBinding
    private val args: DetailsPictureFragmentArgs by navArgs()
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uri = args.uri

        binding.imageDetails.apply {
            transitionName = uri
            Picasso.get()
                .load(uri)
                .into(this)
        }


    }


}