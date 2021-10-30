package com.kiienkoromaniuk.mods32

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import java.io.InputStream

class FavoritesAdapterMods32fig(contextMods32fig: Context, modsListMods32fig: MutableList<ModMods32fig>, navControllerMods32fig: NavController): RecyclerView.Adapter<FavoritesAdapterMods32fig.FavoritesEnViewHolderMods32fig>() {

    var contextMods32fig: Context = contextMods32fig
    var modsListMods32fig: MutableList<ModMods32fig> = modsListMods32fig
    val navControllerMods32fig= navControllerMods32fig
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesEnViewHolderMods32fig {
        val viewMods32fig: View =
            LayoutInflater.from(contextMods32fig).inflate(R.layout.mod_cell_mods32fig, parent, false)
        return FavoritesEnViewHolderMods32fig(viewMods32fig)
    }

    override fun onBindViewHolder(holderMods32fig: FavoritesEnViewHolderMods32fig, position: Int) {
        holderMods32fig.setIsRecyclable(false)
        var pathMods32fig: String="file:///android_asset/mods/"+modsListMods32fig[position].imagesMods32fig
        var currentModMods32fig: ModMods32fig =modsListMods32fig[position]
        holderMods32fig.txtTitleMods32fig.text=modsListMods32fig[position].title_defMods32fig
        holderMods32fig.txtDescMods32fig.text=modsListMods32fig[position].desc_defMods32fig
        if(SelectedDBMods32fig.checkModMods32fig(currentModMods32fig))
        {
            holderMods32fig.btnAddMods32fig.setImageResource(R.drawable.ic_add_selected_mods32fig)
        }
        try {
            val imsMods32fig: InputStream = contextMods32fig.assets.open("mods/"+modsListMods32fig[position].imagesMods32fig)
            val dMods32fig = Drawable.createFromStream(imsMods32fig, null)
            holderMods32fig.imgModMods32fig.setImageDrawable(dMods32fig)
        } catch (ex: IOException) {

        }
        holderMods32fig.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var sMods32fig: String="0"
                val aMods32fig: Int=GetPositonMods32fig.getPositionFromTitleMods32fig(modsListMods32fig[position].title_defMods32fig,  contextMods32fig)
                val pairMods32fig=Pair<String, Int>("position",aMods32fig)
                try{
                    navControllerMods32fig.navigate(
                        R.id.action_secondFragment_to_detailFragment,
                        bundleOf(pairMods32fig)
                    )
                }catch (e:Exception){}
            }
        })
        holderMods32fig.btnAddMods32fig.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if(SelectedDBMods32fig.checkModMods32fig(modsListMods32fig[position]))
                {
                    SelectedDBMods32fig.removeItemMods32fig(modsListMods32fig[position])
                    holderMods32fig.btnAddMods32fig.setImageResource(R.drawable.ic_add_mods32fig)
                    removeItemMods32fig(position)

                }
                else
                {
                    SelectedDBMods32fig.addItemMods32fig(modsListMods32fig[position])
                    holderMods32fig.btnAddMods32fig.setImageResource(R.drawable.ic_add_selected_mods32fig)
                }
            }
        })
    }
    fun removeItemMods32fig(positionMods32fig: Int) {
        modsListMods32fig.remove(modsListMods32fig[positionMods32fig])
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return modsListMods32fig.size
    }
    class FavoritesEnViewHolderMods32fig(itemViewMods32fig: View) : RecyclerView.ViewHolder(itemViewMods32fig) {
        var imgModMods32fig: ImageView
        var txtTitleMods32fig: TextView
        var txtDescMods32fig: TextView
        var btnAddMods32fig: ImageView

        init {
            imgModMods32fig = itemViewMods32fig.findViewById(R.id.img_mods32fig)

            txtTitleMods32fig = itemViewMods32fig.findViewById(R.id.txtTitle_mods32fig)
            txtDescMods32fig = itemViewMods32fig.findViewById(R.id.txtDesc_mods32fig)
            btnAddMods32fig  = itemViewMods32fig.findViewById(R.id.imgAdd_mods32fig)

        }
    }

}