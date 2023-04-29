package com.pyroblinchik.catapi.presentation.breedCard

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.pyroblinchik.catapi.CatApplication
import com.pyroblinchik.catapi.R
import com.pyroblinchik.catapi.databinding.ActivityBreedCardBinding
import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.presentation.base.ViewModelFactory
import com.pyroblinchik.catapi.presentation.menu.MenuActivity
import com.pyroblinchik.catapi.util.files.DownloadManagerForPhotos
import com.pyroblinchik.catapi.util.view.IProgressView
import com.pyroblinchik.catapi.util.view.ISetToolbar
import com.pyroblinchik.catapi.util.view.gone
import com.pyroblinchik.catapi.util.view.visible
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class BreedCardActivity : AppCompatActivity(), ISetToolbar, IProgressView {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as CatApplication).component
    }
    private lateinit var viewModel: BreedCardViewModel
    private lateinit var binding: ActivityBreedCardBinding

    private val progressView: ProgressBar by lazy {
        binding.progressView
    }

    private val toolbar by lazy {
        binding.includeToolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        binding = ActivityBreedCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, viewModelFactory)[BreedCardViewModel::class.java]

        setTheme(R.style.Theme_CatApi)
        setContentView(binding.root)

        initUI()
    }

    override fun setToolbar() {
        setSupportActionBar(toolbar.mainToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setToolbarTitle(breed: Breed) {
        binding.includeToolbar.titleToolbar.text =
            "${breed?.name ?: ""}"
    }

    private fun initUI() {
        setAttributesTitles()

        setToolbar()

        addObservers()

        addClickListeners()
    }

    fun addObservers() {
        viewModel.breed.observe(this) {
            setToolbarTitle(it)
            setAttributes(it)
            setFavoriteButton(it)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is BreedCardUIState.Error -> {
                            Timber.e("error")
                            hideLoading()
                        }
                        is BreedCardUIState.Loading -> {
                            Timber.e("loading")
                            showLoading()
                        }
                        is BreedCardUIState.Loaded -> {
                            hideLoading()
                        }
                        is BreedCardUIState.Finish -> {
                            hideLoading()
                            finish()
                        }
                        else -> {
                            hideLoading()
                        }
                    }
                }
            }
        }
    }

    private fun setFavoriteButton(breed: Breed){
        if (breed.isFavorite){
            binding.addToFavoritesImageButton.setImageDrawable(this.getDrawable(R.drawable.ic_bookmark_dark_green))
        } else{
            binding.addToFavoritesImageButton.setImageDrawable(this.getDrawable(R.drawable.ic_bookmark_empty))
        }
    }

    private fun setAttributesTitles() {
        binding.includeName.nameTextView.text = this.getText(R.string.name)
        binding.includeImperialWeight.nameTextView.text = this.getText(R.string.imperial_weight)
        binding.includeMetricWeight.nameTextView.text = this.getText(R.string.metric_weight)
        binding.includeTemperament.nameTextView.text = this.getText(R.string.temperament)
        binding.includeOrigin.nameTextView.text = this.getText(R.string.origin)
        binding.includeCountryCodes.nameTextView.text = this.getText(R.string.country_codes)
        binding.includeCountryCode.nameTextView.text = this.getText(R.string.country_code)
        binding.includeDescription.nameTextView.text = this.getText(R.string.description)
        binding.includeHeight.nameTextView.text = this.getText(R.string.height)
        binding.includeLifeSpan.nameTextView.text = this.getText(R.string.life_span)
        binding.includeIndoor.nameTextView.text = this.getText(R.string.indoor)
        binding.includeLap.nameTextView.text = this.getText(R.string.lap)
        binding.includeAltNames.nameTextView.text = this.getText(R.string.alt_names)
        binding.includeAdaptability.nameTextView.text = this.getText(R.string.adaptability)
        binding.includeAffectionLevel.nameTextView.text = this.getText(R.string.affection_level)
        binding.includeChildFriendly.nameTextView.text = this.getText(R.string.child_friendly)
        binding.includeDogFriendly.nameTextView.text = this.getText(R.string.dog_friendly)
        binding.includeEnergyLevel.nameTextView.text = this.getText(R.string.energy_level)
        binding.includeGrooming.nameTextView.text = this.getText(R.string.grooming)
        binding.includeHealthIssues.nameTextView.text = this.getText(R.string.health_issues)
        binding.includeIntelligence.nameTextView.text = this.getText(R.string.intelligence)
        binding.includeSheddingLevel.nameTextView.text = this.getText(R.string.shedding_level)
        binding.includeSocialNeeds.nameTextView.text = this.getText(R.string.social_needs)
        binding.includeStrangerFriendly.nameTextView.text = this.getText(R.string.stranger_friendly)
        binding.includeVocalisation.nameTextView.text = this.getText(R.string.vocalisation)
        binding.includeExperimental.nameTextView.text = this.getText(R.string.experimental)
        binding.includeHairless.nameTextView.text = this.getText(R.string.hairless)
        binding.includeNatural.nameTextView.text = this.getText(R.string.natural)
        binding.includeRare.nameTextView.text = this.getText(R.string.rare)
        binding.includeRex.nameTextView.text = this.getText(R.string.rex)
        binding.includeSuppressedTail.nameTextView.text = this.getText(R.string.suppressed_tail)
        binding.includeShortLegs.nameTextView.text = this.getText(R.string.short_legs)
        binding.includeWikipediaUrl.nameTextView.text = this.getText(R.string.wikipedia_url)
        binding.includeHypoallergenic.nameTextView.text = this.getText(R.string.hypoallergenic)

    }

    private fun setAttributes(breed: Breed) {
        binding.includeName.valueTextView.text = breed.name
        binding.includeImperialWeight.valueTextView.text = breed.weight?.imperial
        binding.includeMetricWeight.valueTextView.text = breed.weight?.metric
        binding.includeTemperament.valueTextView.text = breed.temperament
        binding.includeOrigin.valueTextView.text = breed.origin
        binding.includeCountryCodes.valueTextView.text = breed.countryCodes
        binding.includeCountryCode.valueTextView.text = breed.countryCode
        binding.includeDescription.valueTextView.text = breed.description
        binding.includeHeight.valueTextView.text = breed.height
        binding.includeLifeSpan.valueTextView.text = breed.lifeSpan
        binding.includeIndoor.valueTextView.text = "${breed.indoor}/5"
        binding.includeLap.valueTextView.text = "${breed.lap}/5"
        binding.includeAltNames.valueTextView.text = breed.altNames
        binding.includeAdaptability.valueTextView.text = "${breed.adaptability}/5"
        binding.includeAffectionLevel.valueTextView.text = "${breed.affectionLevel}/5"
        binding.includeChildFriendly.valueTextView.text = "${breed.childFriendly}/5"
        binding.includeDogFriendly.valueTextView.text = "${breed.dogFriendly}/5"
        binding.includeEnergyLevel.valueTextView.text = "${breed.energyLevel}/5"
        binding.includeGrooming.valueTextView.text = "${breed.grooming}/5"
        binding.includeHealthIssues.valueTextView.text = "${breed.healthIssues}/5"
        binding.includeIntelligence.valueTextView.text = "${breed.intelligence}/5"
        binding.includeSheddingLevel.valueTextView.text = "${breed.sheddingLevel}/5"
        binding.includeSocialNeeds.valueTextView.text = "${breed.socialNeeds}/5"
        binding.includeStrangerFriendly.valueTextView.text = "${breed.strangerFriendly}/5"
        binding.includeVocalisation.valueTextView.text = "${breed.vocalisation}/5"
        binding.includeExperimental.valueTextView.text = "${breed.experimental}/5"
        binding.includeHairless.valueTextView.text = "${breed.hairless}/5"
        binding.includeNatural.valueTextView.text = "${breed.natural}/5"
        binding.includeRare.valueTextView.text = "${breed.rare}/5"
        binding.includeRex.valueTextView.text = "${breed.rex}/5"
        binding.includeSuppressedTail.valueTextView.text = "${breed.suppressedTail}/5"
        binding.includeShortLegs.valueTextView.text = "${breed.suppressedTail}/5"
        binding.includeWikipediaUrl.valueTextView.text = breed.wikipediaUrl
        binding.includeHypoallergenic.valueTextView.text = "${breed.hypoallergenic}/5"

    }

    private fun addClickListeners() {
        binding.addToFavoritesImageButton.setOnClickListener {
            if (viewModel.breed.value!!.isFavorite){
                viewModel.deleteBreedFromFavorites()
            } else{
                viewModel.addBreedToFavorites()
            }
        }
        binding.downloadImageButton.setOnClickListener {
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
            } else{
                viewModel.downloadBreedPhotoFromNetwork()
            }
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

    override fun onBackPressed() {
        backPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.empty_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent()
                setResult(requestCode, intent)
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
        const val MODE_FROM_NETWORK = 0
        const val MODE_FROM_FAVORITES = 1
        private const val PERMISSION_REQUEST_CODE = 101

        var breedId = "0"
        var requestCode = MODE_FROM_NETWORK


        fun startForResultFromNetwork(
            activity: Activity,
            breedId: String,
            result: ActivityResultLauncher<Intent>,
        ) {
            this.breedId = breedId
            requestCode = MODE_FROM_NETWORK
            createActivityForResult(activity, result)
        }

        fun startForResultFromFavorites(
            activity: Activity,
            breedId: String,
            result: ActivityResultLauncher<Intent>
        ) {
            this.breedId = breedId
            requestCode = MODE_FROM_FAVORITES
            createActivityForResult(activity, result)
        }

        private fun createActivityForResult(
            activity: Activity,
            result: ActivityResultLauncher<Intent>
        ) {
            result.launch(
                Intent(activity, BreedCardActivity::class.java)
            )
        }
    }
}