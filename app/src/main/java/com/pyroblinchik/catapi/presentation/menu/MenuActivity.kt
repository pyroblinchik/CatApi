package com.pyroblinchik.catapi.presentation.menu

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pyroblinchik.catapi.CatApplication
import com.pyroblinchik.catapi.R
import com.pyroblinchik.catapi.databinding.ActivityMenuBinding
import com.pyroblinchik.catapi.presentation.base.ViewModelFactory
import com.pyroblinchik.catapi.util.view.IProgressView
import com.pyroblinchik.catapi.util.view.ISetToolbar
import com.pyroblinchik.catapi.util.view.gone
import com.pyroblinchik.catapi.util.view.visible
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class MenuActivity : AppCompatActivity(), ISetToolbar, IProgressView {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var viewModel: MenuViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as CatApplication).component
    }


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
        component.inject(this)
        binding = ActivityMenuBinding.inflate(layoutInflater)

        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]

        checkPermissions()
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

    fun checkPermissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            showToast("Need Permission to access storage for Downloading Image")
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("Permission granted")
            } else {
                showToast("Permission denied")
            }
        }
    }

    fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun addObservers() {
        viewModel.activeTab.observe(this) {
            invalidateOptionsMenu()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is MenuUIState.Error -> {
                            Timber.e("error")
//                            checkStateForTimeout(state.message)
                            hideLoading()
                        }
                        is MenuUIState.Loading -> {
                            Timber.e("loading")
                            showLoading()
                        }
                        is MenuUIState.Loaded -> {
                            hideLoading()
                        }
                        else -> {
                            hideLoading()
                        }
                    }
                }
            }
        }
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
        when (viewModel.activeTab.value) {
            0 -> {
                menuInflater.inflate(R.menu.empty_menu, menu)
            }
            1 -> {
                menuInflater.inflate(R.menu.empty_menu, menu)
            }
        }
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

        private const val PERMISSION_REQUEST_CODE = 101

        var requestCode = MODE_UNKNOWN

    }
}