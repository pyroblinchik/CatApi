package com.pyroblinchik.catapi.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.pyroblinchik.catapi.databinding.FragmentFavoritesBinding
import com.pyroblinchik.catapi.presentation.base.BaseFragment

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>()  {
    private val viewModel by activityViewModels<MenuViewModel>()

    override fun constructViewBinding(): ViewBinding =
        FragmentFavoritesBinding.inflate(layoutInflater)

    override fun init(viewBinding: ViewBinding, savedInstanceState: Bundle?) {
        getViewBinding().textView.text = "Favorites"
    }

    override fun onResume() {
        viewModel.updateActiveTab(1)
        super.onResume()
    }
}