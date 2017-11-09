package com.androidevlinux.percy.adltools.Activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.androidevlinux.percy.adltools.Fragment.About
import com.androidevlinux.percy.adltools.Fragment.Main
import com.androidevlinux.percy.adltools.Fragment.Settings
import com.androidevlinux.percy.adltools.R
import com.androidevlinux.percy.adltools.Utils.GlobalUtils
import kotlinx.android.synthetic.main.activity_launch.*
import kotlinx.android.synthetic.main.app_bar_main.*

/**
 * Created by percy on 10/11/2017.
 */
class Launch : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var fragment: Fragment? = null
    private var globalUtils: GlobalUtils = GlobalUtils()
    private val ACTION_SETTINGS = "com.androidevlinux.percy.adltools.Activity.SETTINGS"
    private val ACTION_ABOUT = "com.androidevlinux.percy.adltools.Activity.ABOUT"
    private val tag = this.javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        nav_view.itemIconTintList = null
        when {
            ACTION_SETTINGS == intent.action -> // Invoked via the manifest shortcut.
                displaySelectedScreen(R.id.nav_settings)
            ACTION_ABOUT == intent.action -> // Invoked via the manifest shortcut.
                displaySelectedScreen(R.id.nav_about)
            else -> displaySelectedScreen(R.id.nav_main)
        }
        txtTitle.text = this.resources?.getString(R.string.app_name)

        if (globalUtils.checkRooted()) {
            Log.i(tag,"Rooted Device")
        } else {
            Log.i(tag,"Not Rooted Device")
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        displaySelectedScreen(item.itemId)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun displaySelectedScreen(itemId: Int) {
        when (itemId) {
            R.id.nav_main -> {
                fragment = Main()
            }
            R.id.nav_settings -> {
                fragment = Settings()
            }
            R.id.nav_about -> {
                fragment = About()
            }
        }

        //replacing the fragment
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.container, fragment)
            ft.addToBackStack(null).commit()
        }

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
    }
}