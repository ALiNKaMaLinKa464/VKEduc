package com.hfad.vkeduc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hfad.vkeduc.presentation.appdetails.AppDetails
import com.hfad.vkeduc.presentation.appdetails.AppDetailsContent
import com.hfad.vkeduc.presentation.appdetails.Category
import com.hfad.vkeduc.ui.theme.VKEducTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKEducTheme {
                AppNavHost(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
