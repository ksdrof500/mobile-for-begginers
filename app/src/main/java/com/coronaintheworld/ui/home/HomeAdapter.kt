package com.coronaintheworld.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coronaintheworld.R
import com.coronaintheworld.remote.dto.Country
import kotlinx.android.synthetic.main.nation_item.view.*


class HomeAdapter(
    val homeListener: HomeListener,
    val countryList: List<Country>
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
        fun bind(country: Country) = with(itemView) {
            name.text = country.nameCountry
            setOnClickListener {
                homeListener.onClickItem(country.slugCountry, country.idFlag)
            }
        }
    }
}