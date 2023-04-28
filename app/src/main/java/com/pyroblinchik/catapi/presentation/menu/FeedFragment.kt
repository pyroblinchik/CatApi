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
import com.pyroblinchik.catapi.databinding.FragmentFeedBinding
import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.BreedShort
import com.pyroblinchik.catapi.presentation.base.BaseFragment
import com.pyroblinchik.catapi.presentation.menu.view.BreedsAdapter

class FeedFragment : BaseFragment<FragmentFeedBinding>() {
    private val viewModel by activityViewModels<MenuViewModel>()

    lateinit var breedsAdapter: BreedsAdapter

    val emptyResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        }

    override fun constructViewBinding(): ViewBinding =
        FragmentFeedBinding.inflate(layoutInflater)

    override fun init(viewBinding: ViewBinding, savedInstanceState: Bundle?) {
        initUI()
    }

    fun initUI() {
        setBreeds()
        addObservers()
    }

    fun addObservers() {
        viewModel.breeds.observe(this) { list ->
            breedsAdapter.submitList(list)
        }
    }

    override fun onResume() {
        viewModel.updateActiveTab(0)
        super.onResume()
    }

    private fun setBreeds() {
        val onItemClickListener: ((breed: BreedShort) -> Unit) = {
//            BreedCardActivity.startForResult(requireActivity(),it,emptyResult)
        }

        breedsAdapter = BreedsAdapter(
            onItemClickListener
        )
        getViewBinding().breedsListView.adapter = breedsAdapter
    }
}