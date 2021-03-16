package br.personal.project.picturestaken.ui.homePictures

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.personal.project.picturestaken.R
import br.personal.project.picturestaken.data.model.Picture
import br.personal.project.picturestaken.databinding.FragmentHomePicturesBinding
import br.personal.project.picturestaken.repository.PictureRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomePicturesFragment : Fragment() {
    companion object {
        const val REQUEST_CODE = 1000
    }

    private lateinit var binding: FragmentHomePicturesBinding

    val viewModel by lazy {
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
        )        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        val repository = PictureRepository()
        GlobalScope.launch {
            repository.findPictureByName("people")
        }
    }


    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.INTERNET
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            shouldShowRequestPermissionRationale(Manifest.permission.INTERNET) -> {
            }

            else -> {
                requestPermissions(
                    arrayOf(Manifest.permission.INTERNET),
                    REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
            }
            else -> {

            }
        }
    }

}