package com.kiienkoromaniuk.mods32

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset


class FirstFragmentMods32fig : Fragment() {

    var checkMods32fig: Boolean=false
    lateinit var recyclerViewMods32fig: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var modsListMods32fig: MutableList<ModMods32fig> = getModsMods32fig()
        checkMods32fig=true
        recyclerViewMods32fig = requireView().findViewById(R.id.recyclerView_mods32fig)
        setAdapterEnMods32fig(modsListMods32fig)
        //recyclerView.scrollToPosition(HomeActivity.modsPosition)
            Log.d("Scroll", recyclerViewMods32fig.scrollState.toString())
        recyclerViewMods32fig.smoothScrollBy(0,HomeActivityMods32fig.modsPositionMods32fig)
        requireView().findViewById<TextView>(R.id.txtFavorites_mods32fig).setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    override fun onPause() {
        HomeActivityMods32fig.modsPositionMods32fig = recyclerViewMods32fig.computeVerticalScrollOffset()
        super.onPause()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mod_mods32fig, container, false)
    }
    fun loadJSONFromAssetMods32fig(contextMods32fig: Context): String? {
        var jsonMods32fig: String? = null
        jsonMods32fig = try {
            val `isMods32fig`: InputStream = contextMods32fig.assets.open("data.json")
            val sizeMods32fig: Int = `isMods32fig`.available()
            val bufferMods32fig = ByteArray(sizeMods32fig)
            `isMods32fig`.read(bufferMods32fig)
            `isMods32fig`.close()
            String(bufferMods32fig, Charset.defaultCharset())

        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        if (jsonMods32fig != null)
            Log.d("VladDe", "json get")
        else {
            Log.d("VladDe", "json not get")
        }
        return jsonMods32fig
    }

    fun getModsMods32fig(): MutableList<ModMods32fig> {
        var contextMods32fig: Context =requireContext()
        val gsonMods32fig = Gson()
        val jsonMods32fig = loadJSONFromAssetMods32fig(contextMods32fig)
        val itemTypeMods32fig = object : TypeToken<MutableList<ModMods32fig>>() {}.type
        var modsListMods32fig: MutableList<ModMods32fig>
        modsListMods32fig = gsonMods32fig.fromJson<MutableList<ModMods32fig>>(jsonMods32fig, itemTypeMods32fig).toMutableList()


        return modsListMods32fig
    }

    fun setAdapterEnMods32fig(modsListMods32fig: MutableList<ModMods32fig>) {
        var contextMods32fig: Context =requireContext()
        var adapterMods32fig = HomeAdapterMods32fig(contextMods32fig, modsListMods32fig, findNavController())
        adapterMods32fig.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        val layoutManagerMods32fig: RecyclerView.LayoutManager =
            LinearLayoutManager(contextMods32fig, LinearLayoutManager.VERTICAL, false)
        recyclerViewMods32fig.adapter = adapterMods32fig
        recyclerViewMods32fig.layoutManager = layoutManagerMods32fig


    }

}