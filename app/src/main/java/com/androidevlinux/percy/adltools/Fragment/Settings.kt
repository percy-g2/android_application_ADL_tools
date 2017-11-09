package com.androidevlinux.percy.adltools.Fragment

import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.widget.AppCompatTextView
import android.util.Log
import android.view.View
import com.androidevlinux.percy.adltools.R

/**
 * Created by percy on 10/11/2017.
 */
class Settings : PreferenceFragmentCompat() {
    private val txtTitle by lazy { activity!!.findViewById<View>(R.id.txtTitle) as AppCompatTextView }
    private val title_pref by lazy { findPreference(title_pref_keys)  }
    private var title_pref_keys = "title_pref_keys"
    private val TAG = this.javaClass.simpleName
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        txtTitle.text = activity?.resources?.getString(R.string.action_settings)
        try {
            android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(activity)
        } catch (e: Exception) {
            Log.i("test", e.message)
        }

        title_pref.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            Log.i(TAG,"OnPreferenceClickListener true")
            true
        }
    }
}
