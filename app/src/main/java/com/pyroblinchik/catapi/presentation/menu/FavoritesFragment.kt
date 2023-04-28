package com.pyroblinchik.catapi.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.pyroblinchik.catapi.databinding.FragmentFavoritesBinding
import com.pyroblinchik.catapi.domain.base.models.BreedShort
import com.pyroblinchik.catapi.presentation.base.BaseFragment
import com.pyroblinchik.catapi.presentation.menu.view.BreedsAdapter

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>()  {
    private val viewModel by activityViewModels<MenuViewModel>()


    lateinit var breedsFavoritesAdapter: BreedsAdapter

    val emptyResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        }

    override fun constructViewBinding(): ViewBinding =
        FragmentFavoritesBinding.inflate(layoutInflater)

    override fun init(viewBinding: ViewBinding, savedInstanceState: Bundle?) {
        initUI()
    }


    fun initUI() {
        setBreedsFavorites()
        addObservers()
    }

    fun addObservers() {
        viewModel.breedsFavorites.observe(this) { list ->
            breedsFavoritesAdapter.submitList(list)
        }
    }

    override fun onResume() {
        viewModel.updateActiveTab(1)
        super.onResume()
    }


    private fun setBreedsFavorites() {
        val onItemClickListener: ((breed: BreedShort) -> Unit) = {
//            BreedCardActivity.startForResult(requireActivity(),it,emptyResult)
        }

        breedsFavoritesAdapter = BreedsAdapter(
            onItemClickListener
        )
        getViewBinding().breedsFavoritesListView.adapter = breedsFavoritesAdapter
    }
}