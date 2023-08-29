package com.example.objclistbinding.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.objclistbinding.Demo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView(
                        listOf(
                            Demo.getListOfStringsBox1().value.joinToString(" "),
                            Demo.getListOfStringsBox2().value.joinToString(" "),
                            Demo.getStringBox().value,
                            Demo.getBoxOfStringBoxes().value.value,
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingView(greetings: List<String>) {
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        greetings.forEach { GreetingRow(text = it) }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun GreetingRow(text: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, fontSize = 21.sp)
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView(listOf("Hello, Android!"))
    }
}
