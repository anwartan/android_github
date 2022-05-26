package com.example.aplikasigithubuser

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasigithubuser.databinding.ActivitySettingBinding
import com.example.aplikasigithubuser.helper.PreferencesViewModelFactory

class SettingActivity : BaseActivity() {
    private lateinit var settingViewModel : SettingViewModel
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbarLayoutNormal
        setSupportActionBar(toolbar)
        val switchTheme = binding.switchTheme
        val pref = SettingPreferences.getInstance(getDataStore())
        settingViewModel = ViewModelProvider(this, PreferencesViewModelFactory(pref)).get(SettingViewModel::class.java)
        settingViewModel.getThemeSettings().observe(this,{

            if(it){

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked=true
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked=false
            }
        })
        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingViewModel.saveThemeSetting(isChecked)
        }

    }
}