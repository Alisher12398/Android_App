package com.android.android_app.Fragments.Main


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.android.android_app.R
import com.android.android_app.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val textview_fragment_settings : TextView = view.findViewById(R.id.textview_fragment_settings)
        val button_fragment_settings : Button = view.findViewById(R.id.button_fragment_settings)
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        textview_fragment_settings.text = "Hello, ${currentUser?.email}"

        button_fragment_settings.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        return view
    }


}
