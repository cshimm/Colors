package com.example.colors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.colors.ui.theme.ColorsTheme
import android.graphics.Color.parseColor
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ColorsApp()
                }
            }
        }
    }
}

@Composable
fun ColorsApp() {
    val red = remember { mutableStateOf(TextFieldValue()) }
    val green = remember { mutableStateOf(TextFieldValue()) }
    val blue = remember { mutableStateOf(TextFieldValue()) }
    val color = remember { mutableStateOf("#FF00FF".toColor()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create an RGB Color",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
            )
        Text(text = "Add two hexadecimal characters between 0-9, A-F or a-f without the '#' for each channel")
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ColorTextField(label = "Red", textState = red)
            ColorTextField(label = "Green", textState = green)
            ColorTextField(label = "Blue", textState = blue)
        }
        Button(
            onClick = { color.value = "#${red.value.text}${green.value.text}${blue.value.text}".toColor() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        ) {
            Text(text = "CREATE RGB COLOR")
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = color.value)
                .height(38.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Created color display panel",
                textAlign = TextAlign.Center,
            )
        }
    }
}
@Composable
fun ColorTextField(label : String, textState : MutableState<TextFieldValue>) {
    OutlinedTextField(
        value = textState.value,
        onValueChange = {
            if (it.text.length <= 2 && it.text.uppercase() <= "FF") {
                textState.value = it
            } },
        label = {
            Text(text = "$label Channel")
        },
        modifier = Modifier.fillMaxWidth()
    )
}

fun String.toColor() = Color(parseColor(this))

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ColorsTheme {
        ColorsApp()
    }
}