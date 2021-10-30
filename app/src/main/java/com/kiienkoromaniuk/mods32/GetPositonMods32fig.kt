package com.kiienkoromaniuk.mods32

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

object GetPositonMods32fig {
    fun getPositionFromTitleMods32fig(titleMods32fig: String, contextMods32fig: Context): Int{
        val dataMods32fig = getModsMods32fig(contextMods32fig)
        for((j, i) in dataMods32fig.withIndex())
        {
            if(i.title_defMods32fig == titleMods32fig)
                return j
        }
        return 0
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

    fun getModsMods32fig(contextMods32fig: Context): MutableList<ModMods32fig> {
        val gsonMods32fig = Gson()
        val jsonMods32fig = loadJSONFromAssetMods32fig(contextMods32fig)
        val itemTypeMods32fig = object : TypeToken<MutableList<ModMods32fig>>() {}.type
        var modsListMods32fig: MutableList<ModMods32fig>
        modsListMods32fig = gsonMods32fig.fromJson<MutableList<ModMods32fig>>(jsonMods32fig, itemTypeMods32fig).toMutableList()


        return modsListMods32fig
    }
}