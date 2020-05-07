package com.coronaintheworld.ui.activity.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.coronaintheworld.R
import com.coronaintheworld.common.showErrorKT
import com.coronaintheworld.remote.common.Status
import com.coronaintheworld.remote.dto.DetailCountry
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailActivity : AppCompatActivity() {
    private val slugNation: String by lazy { intent.getStringExtra(EXTRA_SLUG) }
    private val idFlag: String by lazy { intent.getStringExtra(EXTRA_ID_FLAG) }

    private val detailViewModel: DetailViewModel by viewModel()

    companion object {
        private const val EXTRA_SLUG = "slug"
        private const val EXTRA_ID_FLAG = "idFlag"

        fun launchIntent(context: Context, slugNation: String, idFlag: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_SLUG, slugNation)
            intent.putExtra(EXTRA_ID_FLAG, idFlag)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupHeader()
        addFlag()
        addListener()
        binder()
    }

    private fun binder() {
        detailViewModel.getDetail(slugCountry = slugNation)
        detailViewModel.detail.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> setupData(it.data)
                Status.ERROR -> showError()
                Status.LOADING -> toggleLoading(true)
            }
        })
    }

    private fun setupHeader() {
        toolbar.title = slugNation.toUpperCase(Locale.getDefault())
        setSupportActionBar(toolbar)
    }

    private fun addListener() {
        fab.setOnClickListener { view ->
            detailViewModel.savePreferences(slug = slugNation)
            Snackbar.make(view, getString(R.string.save), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun addFlag(){
        Glide.with(this)
            .load("https://www.countryflags.io/$idFlag/shiny/64.png")
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()
            .into(bgHeader)


    }

    private fun setupData(data: DetailCountry?) {
        toggleLoading(false)
        data?.let {
            confirmed.text = getString(R.string.confirmed, it.confirmed)
            death.text = getString(R.string.death, it.deaths)
            recovered.text = getString(R.string.recovered, it.recovered)
            active.text = getString(R.string.active, it.active)
        }
    }

    private fun showError() {
        toggleLoading(false)
        showErrorKT(getString(R.string.error_default))
    }

    private fun toggleLoading(isShow: Boolean) {
        progressBar.isVisible = isShow
    }

}
