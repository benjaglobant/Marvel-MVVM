package com.globant.marvelmvvm.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import com.globant.marvelmvvm.R
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_main.activity_main_toolbar
import kotlinx.android.synthetic.main.activity_main.activity_main_navigation_view
import kotlinx.android.synthetic.main.activity_main.activity_main_drawer_layout

class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(activity_main_toolbar)

        val toggle = ActionBarDrawerToggle(this, activity_main_drawer_layout, activity_main_toolbar, ZERO, ZERO)
        activity_main_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        activity_main_navigation_view.setNavigationItemSelectedListener(this)
    }

    private fun showFragment(title: Int, message: Int, fragment: Int){
        val navController = Navigation.findNavController(this, R.id.activity_main_active_fragment)
        activity_main_toolbar.title = getString(title)
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
        navController.navigate(fragment)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.allCharactersFragment -> {
                showFragment(R.string.string_all_characters, R.string.string_all_characters_message, R.id.allCharactersFragment)
            }
            R.id.specificCharacterFragment -> {
                showFragment(R.string.string_specific_character, R.string.string_specific_character_message, R.id.specificCharacterFragment)
            }
        }
        activity_main_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        const val ZERO = 0
    }
}