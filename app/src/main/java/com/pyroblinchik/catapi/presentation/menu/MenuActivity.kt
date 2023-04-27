package com.pyroblinchik.catapi.presentation.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pyroblinchik.catapi.R
import com.pyroblinchik.catapi.databinding.ActivityMenuBinding
import com.pyroblinchik.catapi.util.view.gone
import com.pyroblinchik.catapi.util.view.visible
import com.pyroblinchik.catapi.util.view.IProgressView
import com.pyroblinchik.catapi.util.view.ISetToolbar
import timber.log.Timber

class MenuActivity : AppCompatActivity(), ISetToolbar, IProgressView {

    private lateinit var binding: ActivityMenuBinding

    private val progressView: ProgressBar by lazy {
        binding.progressView
    }

    private val toolbar by lazy {
        binding.includeToolbar
    }

    private val navigation by lazy {
        binding.navView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)

        setTheme(R.style.Theme_CatApi)
        setContentView(binding.root)

        initUI()
    }

    override fun setToolbar() {
        setSupportActionBar(toolbar.mainToolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        binding.includeToolbar.titleToolbar.gone()
//        binding.includeToolbar.mainToolbar.setLogo(R.drawable.ic_logo_horizontal)
    }

    private fun initUI() {
        setToolbar()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            changeToolbar(destination.displayName)
        }
        addObservers()
        addClickListeners()
    }

    fun addObservers() {
//        viewModel.activeTab.observe(this) {
//            invalidateOptionsMenu()
//        }
//
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.uiState.collect { state ->
//                    when (state) {
//                        is MenuUIState.Error -> {
//                            Timber.e("error")
////                            checkStateForTimeout(state.message)
//                            hideLoading()
//                        }
//                        is MenuUIState.Loading -> {
//                            Timber.e("loading")
//                            showLoading()
//                        }
//                        is MenuUIState.Finish -> {
//                            finish()
//                        }
//                        else -> {
//                            hideLoading()
//                        }
//                    }
//                }
//            }
//        }
    }

    private fun addClickListeners() {

    }

    private fun changeToolbar(nameOfBlock: String) {
        Timber.d(nameOfBlock)
        binding.includeToolbar.titleToolbar.text = ""
        when {
            nameOfBlock.contains("feed") -> {
//                binding.includeToolbar.titleToolbar.text = getString(R.string.feed)
            }
            nameOfBlock.contains("favorites") -> {
//                binding.includeToolbar.titleToolbar.text = getString(R.string.favorites)
            }
            else -> {
//                binding.includeToolbar.titleToolbar.text = ""
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.empty_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        backPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                backPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun backPressed() {
        finish()
    }

    override fun showLoading() {
        progressView.visible()
    }

    override fun hideLoading() {
        progressView.gone()
    }

    companion object {
        private const val MODE_UNKNOWN = 0

        var requestCode = MODE_UNKNOWN


        fun startForResult(
            activity: Activity,
            result: ActivityResultLauncher<Intent>,
            view: View,
        ) {
            createActivityForResult(activity, result, view)
        }

        private fun createActivityForResult(
            activity: Activity,
            result: ActivityResultLauncher<Intent>,
            view: View,
        ) {
            result.launch(
                Intent(activity, MenuActivity::class.java),
            )
        }

    }
}