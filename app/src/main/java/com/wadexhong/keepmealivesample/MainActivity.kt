package com.wadexhong.keepmealivesample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wadexhong.keepmealive.Helper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Helper.isCustomPermissionSettingSupport(this)) {
            if (Helper.launchPermissionSettingPage(this)) {
                Toast.makeText(
                    this,
                    "Success",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                this,
                "This devices is not support by this library",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}