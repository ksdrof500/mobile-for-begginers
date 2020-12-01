package com.coronaintheworld.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.coronaintheworld.R
import com.coronaintheworld.common.Status
import com.coronaintheworld.common.ViewState
import com.coronaintheworld.common.showErrorKT
import com.coronaintheworld.model.CountryUiModel
import com.coronaintheworld.ui.activity.detail.DetailActivity.Companion.launchIntent
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), HomeListener {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.countries.observe(viewLifecycleOwner, observer)
    }

    private fun setupRecycler(countryList: List<CountryUiModel>?) {
        countryList?.let {
            recycler.adapter = HomeAdapter(homeListener = this, countryList = countryList)
        }
    }

    private val observer = Observer<ViewState<List<CountryUiModel>>> {
        when (it.status) {
            Status.SUCCESS -> {
                setupRecycler(it.data)
                toggleLoading(false)
            }
            Status.ERROR -> {
                showError()
                toggleLoading(false)
            }
            Status.LOADING -> toggleLoading(true)
        }
    }

    private fun showError() {
        context?.run {
            showErrorKT(getString(R.string.error_default))
        }
    }

    private fun toggleLoading(isShow: Boolean) {
        progressBar.isVisible = isShow
    }

    override fun onClickItem(slugNation: String, idFlag: String) {
        context?.let { startActivity(launchIntent(context = it, slugNation = slugNation, idFlag = idFlag)) }
    }
}
