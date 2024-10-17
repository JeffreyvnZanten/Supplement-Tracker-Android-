package com.jefvaza.supplementtracker;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: SupplementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = SupplementsDatabase.getDatabase(applicationContext).supplementDao()
        viewModel = SupplementViewModel(dao)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SupplementApp(viewModel)
        }
    }
}