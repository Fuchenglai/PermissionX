package com.lfc.permissionx

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.lfc.mylibrary.PermissionX

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var makeCallBtn = findViewById<Button>(R.id.makeCallBtn)
        makeCallBtn.setOnClickListener {
            PermissionX.request(this,
                android.Manifest.permission.CALL_PHONE) { allGranted, deniedList ->
                if (allGranted) {
                    call()
                } else {
                    Toast.makeText(this, "You denied $deniedList",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun call() {try {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:10086")
        startActivity(intent)
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
    }
}
