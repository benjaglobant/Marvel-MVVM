package com.globant.marvelmvvm.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.globant.marvelmvvm.R
import com.globant.domain.util.Constants.CHARACTER_ID
import com.globant.domain.util.Constants.ZERO
import com.globant.marvelmvvm.view.dialogfragment.InsertCharacterIdDialog
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_main.activity_main_toolbar
import kotlinx.android.synthetic.main.activity_main.activity_main_drawer_layout
import kotlinx.android.synthetic.main.activity_main.activity_main_navigation_view

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

    private fun showFragment(fragment: Int, args: Bundle? = null) {
        val navBuilder = NavOptions.Builder().setPopUpTo(R.id.allCharactersFragment, false).build()
        Navigation.findNavController(this, R.id.activity_main_active_fragment).navigate(fragment, args, navBuilder)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.allCharactersFragment -> {
                showFragment(R.id.allCharactersFragment)
            }
            R.id.specificCharacterFragment -> {
                val args = Bundle()
                val dialog = InsertCharacterIdDialog {
                    args.putString(CHARACTER_ID, it)
                    showFragment(R.id.specificCharacterFragment, args)
                }
                dialog.show(supportFragmentManager, DIALOG_TAG)
            }
        }
        activity_main_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        const val DIALOG_TAG = "Dialog Character ID"
    }
}