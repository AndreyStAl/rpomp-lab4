package com.example.lab2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_element.*
import kotlinx.android.synthetic.main.fragment_list.*


class ElementFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_element, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.with(activity as MainActivity)
            .load((activity as MainActivity).image)
            .into(imageView2)
        text_country.setText((activity as MainActivity).country)
        text_price.setText((activity as MainActivity).price)
        textView5.setText((activity as MainActivity).inform)
    }


}
