package com.example.lab2

import NetworkService
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {

    var id1: Int = -1
    var country: String=""
    var price: String=""
    var inform: String=""
    var image: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fragment=ListFragment()
        fragmentReplace(fragment)
    }

    fun fragmentReplace(fragment: Fragment)
    {
        var fm: FragmentManager =supportFragmentManager
        var ft: FragmentTransaction =fm.beginTransaction()
        ft.replace(R.id.mainLayout, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }


}
