package com.oguzhanaslann.appshortcutssample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        intent.extras?.get(SearchTextExtra)?.let {
            findViewById<EditText>(R.id.editTextTextPersonName).setText(it.toString())
        }
    }

    companion object {
        private const val SearchTextExtra = "shortcut_search_extra"

    }
}
