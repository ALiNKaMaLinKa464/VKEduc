package com.hfad.vkeduc

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            label = { Text("Введите текст")},
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
        Button(
            onClick = {
                if(checkNumder(text)){
                    val telText = "tel:$text"
                    var intent = Intent(Intent.ACTION_DIAL, Uri.parse(telText))
                    context.startActivity(intent)
                }
                else
                {
                    //Проверка самая простая на количество цифр и все
                    Toast.makeText(context,"Не правильно набран номер!", Toast.LENGTH_SHORT).show()
                }

            }
        ) {
            Text("Позвонить другу")
        }
        Button(
            onClick = {
                var intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, text)
                }
                context.startActivity(Intent.createChooser(intent,"Поделиться через"))
            }
        ) {
            Text("Поделеться текстом")
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

fun checkNumder(text: String): Boolean{
    // только цифры, +, -, пробелы, скобки; минимум 5 символов
    val cleaned = text.replace(Regex("[\\s\\-()]"), "")
    return Regex("^\\+?\\d{5,15}$").matches(cleaned)
}