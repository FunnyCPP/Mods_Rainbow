package com.kiienkoromaniuk.mods32

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class SecondFragmentMods32fig : Fragment() {

    var checkMods32fig: Boolean=false
    var modsListMods32fig: MutableList<ModMods32fig> = LinkedList()
    lateinit var recyclerViewMods32fig: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selected_mods32fig, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        modsListMods32fig = SelectedDBMods32fig.getModsMods32fig()
        recyclerViewMods32fig =requireView().findViewById(R.id.recyclerView_mods32fig)
        setAdapterEnMods32fig(modsListMods32fig)
        checkMods32fig=true
        recyclerViewMods32fig.smoothScrollBy(0,HomeActivityMods32fig.selectedPositionMods32fig)
        requireView().findViewById<TextView>(R.id.txtMods_mods32fig).setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
    fun setAdapterEnMods32fig(modsListMods32fig: MutableList<ModMods32fig>){
        val contextMods32fig: Context = requireContext()
            var adapterMods32fig = FavoritesAdapterMods32fig(contextMods32fig, modsListMods32fig, findNavController())
            adapterMods32fig.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            val layoutManagerMods32fig: RecyclerView.LayoutManager =
                LinearLayoutManager(contextMods32fig, LinearLayoutManager.VERTICAL, false)
            recyclerViewMods32fig.adapter = adapterMods32fig
            recyclerViewMods32fig.layoutManager = layoutManagerMods32fig
        recyclerViewMods32fig.scrollToPosition(HomeActivityMods32fig.selectedPositionMods32fig)
    }

    override fun onPause() {
        HomeActivityMods32fig.selectedPositionMods32fig = recyclerViewMods32fig.computeVerticalScrollOffset()
        super.onPause()

    }
}