package com.coronaintheworld.ui.activity.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.coronaintheworld.R
import com.coronaintheworld.common.Status
import com.coronaintheworld.common.showErrorKT
import com.coronaintheworld.model.DetailCountryUiModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.* // ktlint-disable

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
        addListener()
        binder()
    }

    private fun binder() {
        detailViewModel.getDetail(slugCountry = slugNation)
        detailViewModel.detail.observe(
            this,
            Observer {
                when (it.status) {
                    Status.SUCCESS -> setupData(it.data)
                    Status.ERROR -> showError()
                    Status.LOADING -> toggleLoading(true)
                }
            }
        )
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

    private fun setupData(data: DetailCountryUiModel?) {
        toggleLoading(false)
        data?.let {
            confirmed.text = it.confirmed
            death.text = it.deaths
            recovered.text = it.recovered
            active.text = it.active
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
