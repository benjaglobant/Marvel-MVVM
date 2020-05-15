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
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_main.nav_view
import kotlinx.android.synthetic.main.activity_main.drawer_layout

class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, ZERO, ZERO)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navController = Navigation.findNavController(this, R.id.fragment)
        when (item.itemId) {
            R.id.allCharactersFragment -> {
                toolbar.title = ALL_CHARACTERS
                Toast.makeText(this, getString(R.string.string_all_characters_message), Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.allCharactersFragment)
            }
            R.id.specificCharacterFragment -> {
                toolbar.title = SPECIFIC_CHARACTER
                Toast.makeText(this, getString(R.string.string_specific_character_message), Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.specificCharacterFragment)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        const val ALL_CHARACTERS = "All Characters"
        const val SPECIFIC_CHARACTER = "Specific Character"
        const val ZERO = 0
    }
}