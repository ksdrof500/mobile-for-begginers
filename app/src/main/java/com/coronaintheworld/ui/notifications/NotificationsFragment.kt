package com.coronaintheworld.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.coronaintheworld.R
import com.coronaintheworld.local.NotificationModel
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : Fragment() {

    private val notificationsViewModel: NotificationsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetch()
    }

    private fun setupRecycler(notificationList: List<NotificationModel>) {
        recycler.adapter = NotificationAdapter(notificationList)
    }

    private fun fetch() {
        lifecycleScope.launch {
            notificationsViewModel.getAll().observe(viewLifecycleOwner, Observer {
                if(it.isNotEmpty())
                    setupRecycler(it)
                else
                    empty.isVisible = true

            })
        }
    }
}
