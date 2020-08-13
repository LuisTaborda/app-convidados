package com.e.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.convidados.R
import com.e.convidados.view.adapter.GuestAdapter
import com.e.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private val mAdapter = GuestAdapter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel =
            ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_all, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        allGuestsViewModel.load()

    }

    private fun observer(){
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}