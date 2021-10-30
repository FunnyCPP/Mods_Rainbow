package com.kiienkoromaniuk.mods32

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*
import java.nio.charset.Charset
import java.util.*

private const val POSITIONMods32fig = "position"

class DetailFragmentMods32fig : Fragment() {
    private var positionMods32fig: Int = 0
    private var positionListMods32fig: Int = 0
    private lateinit var mPagerMods32fig: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            positionMods32fig = it.getInt(POSITIONMods32fig)

        }
        positionListMods32fig=positionMods32fig
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_mods32fig, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var modsListMods32fig: MutableList<ModMods32fig> = getModsMods32fig()
        var NUM_PAGESMods32fig:Int =1
        HomeActivityMods32fig.posMods32fig = positionListMods32fig
        mPagerMods32fig = requireView().findViewById(R.id.pager_mods32fig)
        val pagerAdapterMods32fig = ScreenSlidePagerAdapterMods32fig(requireFragmentManager(), NUM_PAGESMods32fig)
        mPagerMods32fig.adapter = pagerAdapterMods32fig
        val txtTitleMods32fig: TextView = requireView().findViewById(R.id.txtDetailTitle_mods32fig)
        var txtDescMods32fig: TextView =requireView().findViewById(R.id.txtDetailDesc_mods32fig)
        var btnAddMods32fig: ImageButton = requireView().findViewById(R.id.imgBtnAdd_mods32fig)
        var currentModMods32fig: ModMods32fig=modsListMods32fig[positionListMods32fig]
        val imgBackMods32fig: ImageButton =requireView().findViewById(R.id.imgBack_mods32fig)

        imgBackMods32fig.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                HomeActivityMods32fig.modsPositionMods32fig=0
                findNavController().navigateUp()
            }
        })
        if(SelectedDBMods32fig.checkModMods32fig(currentModMods32fig))
        {
            btnAddMods32fig.setImageResource(R.drawable.ic_add_selected_mods32fig)
        }
            txtTitleMods32fig.text=modsListMods32fig[positionListMods32fig].title_defMods32fig
            txtDescMods32fig.text=modsListMods32fig[positionListMods32fig].desc_defMods32fig

        var btnMods32fig: Button =requireView().findViewById(R.id.btnDownload_mods32fig)
        btnMods32fig.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                Log.d("Click","click")
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 1)
                    Log.d("Permissions"," API30 ")
                }
                else
                {
                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 1);
                    Log.d("Permissions","API29")
                }

                if (btnMods32fig.text == getText(R.string.download_mods32fig)) {
                    btnMods32fig.text = getText(R.string.downloading_mods32fig)
                    Handler().postDelayed(
                        {
                            try{ btnMods32fig.text = getText(R.string.install_mods32fig) }
                            catch (e:Exception){}
                        },
                        2000 // value in milliseconds
                    )


                } else {
                    Log.d("text~~~~~~~~~~~~~~","!~~~~~~~~~~~~~~~~~~~~~~~~~~")
                    val bfileMods32fig = File(requireContext().getExternalFilesDir(""),modsListMods32fig[positionListMods32fig].fileMods32fig )
                    Log.d("bFile: ", bfileMods32fig.canonicalPath)

                    var inStreamMods32fig: InputStream? = null
                    var outStreamMods32fig: OutputStream? = null

                    inStreamMods32fig = requireContext().assets.open("mods/"+modsListMods32fig[positionListMods32fig].fileMods32fig)
                    outStreamMods32fig = FileOutputStream(bfileMods32fig)

                    val bufferMods32fig = ByteArray(1024)
                    var lengthMods32fig = inStreamMods32fig.read(bufferMods32fig)
                    while (lengthMods32fig    > 0 )

                    {
                        outStreamMods32fig.write(bufferMods32fig, 0, lengthMods32fig)
                        lengthMods32fig = inStreamMods32fig.read(bufferMods32fig)
                    }

                    inStreamMods32fig.close()
                    outStreamMods32fig.close()
                    val fileUriMods32fig: Uri? = try {
                        FileProvider.getUriForFile(
                                requireContext(),
                                "com.kiienkoromaniuk.mods32.fileprovider",
                                bfileMods32fig)
                    } catch (e: IllegalArgumentException) {
                        Log.e("File Selector",
                                e.toString())
                        null
                    }
                    var intentMods32fig: Intent = Intent(Intent.ACTION_VIEW)
                   /* intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.setData(("minecraft://?=import=$fileUri").toUri())*/
                    intentMods32fig.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
                    intentMods32fig.`package`  = "com.mojang.minecraftpe"
                    intentMods32fig.data = (fileUriMods32fig)
                    Log.d("PATH",("minecraft://?=import=$fileUriMods32fig").toUri().toString())
                    try {
                        startActivity(intentMods32fig)
                    }
                    catch (e:Exception)
                    {
                        val urlMods32fig = "https://play.google.com/store/apps/details?id=com.mojang.minecraftpe"
                        val iMods32fig = Intent(Intent.ACTION_VIEW)
                        iMods32fig.data = Uri.parse(urlMods32fig)
                        startActivity(iMods32fig)
                    }
                }
            }
        })
        btnAddMods32fig.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if(SelectedDBMods32fig.checkModMods32fig(currentModMods32fig))
                {
                    SelectedDBMods32fig.removeItemMods32fig(currentModMods32fig)
                    btnAddMods32fig.setImageResource(R.drawable.ic_add_mods32fig)
                }
                else
                {
                    SelectedDBMods32fig.addItemMods32fig(currentModMods32fig)
                    btnAddMods32fig.setImageResource(R.drawable.ic_add_selected_mods32fig)
                }
            }
        })

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
        val gsonMods32fig = Gson()
        val jsonMods32fig= loadJSONFromAssetMods32fig(requireContext())
        val itemTypeMods32fig = object : TypeToken<MutableList<ModMods32fig>>() {}.type
        var modsListMods32fig: MutableList<ModMods32fig>
        modsListMods32fig = gsonMods32fig.fromJson<MutableList<ModMods32fig>>(jsonMods32fig, itemTypeMods32fig).toMutableList()


        return  modsListMods32fig
    }
    private inner class ScreenSlidePagerAdapterMods32fig(fmMods32fig: FragmentManager, NUM_PAGESMods32fig: Int): FragmentStatePagerAdapter(
        fmMods32fig,
        NUM_PAGESMods32fig
    )  {
        var NUM_PAGESMods32fig:Int=NUM_PAGESMods32fig
        override fun getCount(): Int = NUM_PAGESMods32fig

        override fun getItem(position: Int): Fragment = ImageFragmentMods32fig()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(position: Int) =
            DetailFragmentMods32fig().apply {
                arguments = Bundle().apply {
                    putInt(POSITIONMods32fig, position)
                }
            }
    }
}