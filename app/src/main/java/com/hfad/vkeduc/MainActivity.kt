package com.hfad.vkeduc

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hfad.vkeduc.ui.theme.VKEducTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKEducTheme {
                FirstActivity()
            }
        }
    }
}

@Composable
fun FirstActivity() {
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(top = 56.dp)
    ){
        TextField(
            value = text,
            onValueChange = { newText -> text = newText},
            label = { Text("Введите текст") }
        )
        Button(
            onClick = {
               var intent = Intent(context ,SecondActivity::class.java).apply {
                   putExtra("EXTRA_TEXT",text)
               }
                context.startActivity(intent)
            }
        ) {
            Text("Открыть вторую активность")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstActivityPreview() {
    VKEducTheme {
        FirstActivity()
    }
}