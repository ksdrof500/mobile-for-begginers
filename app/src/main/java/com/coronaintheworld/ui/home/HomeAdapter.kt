package com.coronaintheworld.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.coronaintheworld.R
import com.coronaintheworld.model.CountryUiModel
import kotlinx.android.synthetic.main.nation_item.view.*

class HomeAdapter(
    val homeListener: HomeListener,
    val countryList: List<CountryUiModel>
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nation_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(country: CountryUiModel) = with(itemView) {
            name.text = country.nameCountry

            addFlag(this, flag, country.idFlag)

            setOnClickListener {
                homeListener.onClickItem(country.slugCountry, country.idFlag)
            }
        }

        private fun addFlag(view: View, flag: ImageView, idFlag: String) {
            Glide.with(view.context)
                .load("https://www.countryflags.io/$idFlag/shiny/64.png")
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .into(flag)
        }
    }
}
