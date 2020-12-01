package com.coronaintheworld.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coronaintheworld.R
import com.coronaintheworld.local.NotificationModel
import kotlinx.android.synthetic.main.notification_item.view.*

class NotificationAdapter(val notificationList: List<NotificationModel>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notificationList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(notificationModel: NotificationModel) = with(itemView) {
            title.text = notificationModel.title
            message.text = notificationModel.text
        }
    }
}
