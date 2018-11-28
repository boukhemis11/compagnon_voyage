package com.example.boukh.compagnon_voyage

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.boukh.compagnon_voyage.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


import kotlinx.android.synthetic.main.fragment_story_list.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    StoriesFragment.OnListFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // initiate the date picker and a button

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        // Set Home fragment by default
        setFragment(HomeFragment())
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
            //Si le fragment liste des stories est affiché: on retourne sur le menu en cas d'action retour arrière android
        } else if (listOfStories.isShown) {
            setFragment(HomeFragment())
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.planning -> {
                setFragment(PlanFragment())
            }
            R.id.carnet -> {
                //Display Carnet
                setFragment(StoriesFragment())
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    /**
     * Change fragment
     */
    private fun setFragment(frag: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.my_content, frag)
            .commit()
    }


}
