package com.kiienkoromaniuk.mods32

import io.paperdb.Paper

class SelectedDBMods32fig {
    companion object {

        fun addItemMods32fig(modMods32fig: ModMods32fig) {
            val modsMods32fig = SelectedDBMods32fig.getModsMods32fig()
            modsMods32fig.add(modMods32fig)
            SelectedDBMods32fig.saveModsMods32fig(modsMods32fig)

        }

        fun removeItemMods32fig(modMods32fig: ModMods32fig) {

            val modsMods32fig = SelectedDBMods32fig.getModsMods32fig()
            modsMods32fig.remove(modMods32fig)
            SelectedDBMods32fig.saveModsMods32fig(modsMods32fig)

        }

        fun saveModsMods32fig(modMods32figs: MutableList<ModMods32fig>) {
            Paper.book().write("mod", modMods32figs)
        }

        fun getModsMods32fig(): MutableList<ModMods32fig> {
            return Paper.book().read("mod", mutableListOf())
        }
        fun checkModMods32fig(modMods32fig: ModMods32fig): Boolean{
            val modsMods32fig = SelectedDBMods32fig.getModsMods32fig()
            return modsMods32fig.contains(modMods32fig)
        }
    }
}