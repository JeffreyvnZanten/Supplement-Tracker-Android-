package com.jefvaza.supplementtracker;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    private lateinit var supplementViewModel: SupplementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = SupplementsDatabase.getDatabase(applicationContext).supplementDao()
        val supplementsRepository = SupplementRepository(dao)
        supplementViewModel = SupplementViewModel(supplementsRepository)

        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SupplementApp(supplementViewModel)
        }
    }
}