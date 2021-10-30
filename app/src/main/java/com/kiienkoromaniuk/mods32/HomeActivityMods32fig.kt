package com.kiienkoromaniuk.mods32

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import io.paperdb.Paper

class HomeActivityMods32fig : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_mods32fig)
        Paper.init(this)
        val navControllerMods32fig = Navigation.findNavController(this, R.id.nav_host_mods32fig)
        // navController.navigate(R.id.action_modFragment_to_selectedFragment)
    }

    override fun onBackPressed() {
        val navControllerMods32fig = Navigation.findNavController(this,R.id.nav_host_mods32fig)
        if(navControllerMods32fig.currentDestination!!.label.toString() == "FirstFragment"  || navControllerMods32fig.currentDestination!!.label.toString() =="SecondFragment")
            finishAffinity()
        if(navControllerMods32fig.currentDestination!!.label.toString() == "DetailFragment")
            HomeActivityMods32fig.modsPositionMods32fig=0
        Log.d("Fragments",navControllerMods32fig.currentDestination!!.label.toString())

        super.onBackPressed()
    }
    companion object{
        var modsPositionMods32fig = 0
        var selectedPositionMods32fig = 0
        var posMods32fig = 0
    }
}