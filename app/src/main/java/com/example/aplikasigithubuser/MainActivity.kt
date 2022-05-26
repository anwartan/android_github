package com.example.aplikasigithubuser

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasigithubuser.databinding.ActivityMainBinding
import com.example.aplikasigithubuser.helper.PreferencesViewModelFactory


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var settingViewModel : SettingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val pref = SettingPreferences.getInstance(getDataStore())
        settingViewModel = ViewModelProvider(this,PreferencesViewModelFactory(pref)).get(SettingViewModel::class.java)
        setContentView(binding.root)
        val toolbar: Toolbar = binding.toolbarLayoutNormal
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, UserListFragment()).commit()


        settingViewModel.getThemeSettings().observe(this,{

            if(it){

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
        })

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu1 -> {
                val i = Intent(this, FavoriteActivity::class.java)
                startActivity(i)
                true
            }
            R.id.menu2 -> {

                val i = Intent(this, SettingActivity::class.java)
                startActivity(i)
                true
            }



            else -> super.onOptionsItemSelected(item)
        }

    }





}