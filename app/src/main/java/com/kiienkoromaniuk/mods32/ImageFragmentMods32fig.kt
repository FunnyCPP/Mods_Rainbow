package com.kiienkoromaniuk.mods32

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class ImageFragmentMods32fig() : Fragment() {
    var positionListMods32fig: Int=HomeActivityMods32fig.posMods32fig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_mods32fig, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var modsListMods32fig: MutableList<ModMods32fig> = getModsMods32fig()
        var imgMods32fig: ImageView = view.findViewById(R.id.imgModFragment_mods32fig)
        var pathMods32fig: String="file:///android_asset/mods/"+modsListMods32fig[positionListMods32fig].imagesMods32fig

        Glide.with(requireContext())
            .load(Uri.parse(pathMods32fig))
            .into(imgMods32fig);
        super.onViewCreated(view, savedInstanceState)
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
        if(jsonMods32fig != null)
            Log.d("VladDe", "json get")
        else
        {
            Log.d("VladDe", "json not get")
        }
        return jsonMods32fig
    }
    fun getModsMods32fig():MutableList<ModMods32fig>{
        var mContextMods32fig: Context = requireContext()
        val gsonMods32fig = Gson()
        val jsonMods32fig= loadJSONFromAssetMods32fig(mContextMods32fig)
        val itemTypeMods32fig = object : TypeToken<MutableList<ModMods32fig>>() {}.type
        var modsListMods32fig: MutableList<ModMods32fig>
        modsListMods32fig = gsonMods32fig.fromJson<MutableList<ModMods32fig>>(jsonMods32fig, itemTypeMods32fig).toMutableList()


        return  modsListMods32fig
    }

}