package com.oguzhanaslann.appshortcutssample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.button2).setOnClickListener {
            val shortcut = ShortcutInfoCompat.Builder(this,"MainActivityDynamic")
                .setShortLabel("Dynamic")
                .setLongLabel("Dynamic long")
                .setIcon(IconCompat.createWithResource(this,R.drawable.ic_baseline_dynamic_feed_24))
                .setIntent(
                    Intent(this,SearchActivity::class.java)
                        .apply {
                            action = Intent.ACTION_VIEW
                        }
                )
                .build()

            ShortcutManagerCompat.pushDynamicShortcut(this,shortcut)
        }

        findViewById<View>(R.id.button3).setOnClickListener {
            ShortcutManagerCompat.removeDynamicShortcuts(this, listOf("MainActivityDynamic"))
        }

        findViewById<View>(R.id.button4).setOnClickListener {
           val dynamicShortcut =  ShortcutManagerCompat.getDynamicShortcuts(this).find { it.id == "MainActivityDynamic" }
            Log.e("TAG", "onCreate: ${ShortcutManagerCompat.getDynamicShortcuts(this)}")
            dynamicShortcut?.let {
                ShortcutManagerCompat.enableShortcuts(this, listOf(dynamicShortcut))
            } ?: kotlin.run { Toast.makeText(this, "NULL shortcut", Toast.LENGTH_SHORT).show() }
        }

        findViewById<View>(R.id.button5).setOnClickListener {
            Log.e("TAG", "onCreate: ${ShortcutManagerCompat.getDynamicShortcuts(this)}")
            ShortcutManagerCompat.disableShortcuts(this, listOf("MainActivityDynamic"),"Disabled feture")
        }
    }
}
