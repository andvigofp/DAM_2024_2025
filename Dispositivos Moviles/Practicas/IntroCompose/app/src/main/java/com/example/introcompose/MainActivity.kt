package com.example.introcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.introcompose.ui.theme.IntroComposeTheme

// Partimos de un proyecto con la plantilla Compose Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita el modo de  pantalla completa
        setContent { // setContent para establecer el contenido de la actividad
            IntroComposeTheme { // Para establecer el tema de la actividad
                    SreenContent(
                        // Greeting para mostrar  un saludo por pantall
                        name = "Android",
                    )
                }
            }
        }
    }

// @Composable para definir  que esto es una función con compasable
// Ejemplo: Si mostramos dos Text juntos:


@Composable
fun GreetingBad(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
    Text(
        text = "Goodbye $name!",
        modifier = modifier,
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier= Modifier) {
    Column { // Para mostrar los elementos en una disposición vertical
        Text(
            text = "Hello $name!",
            modifier = modifier,
        )
        Text(
            text = "Goodbye $name!",
            modifier = modifier,
        )
    }
}

@Composable
fun SreenContent(name: String, modifier: Modifier = Modifier) {
    Row {
        Image(
            contentDescription = "Android logo",
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Gray)
                .padding(16.dp)
        )
        Greeting(name)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewBasic() {
    IntroComposeTheme {
        SreenContent("Android")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    IntroComposeTheme {
        SreenContent("Android")
    }
}