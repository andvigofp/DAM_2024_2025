package com.example.tarjetapeliculasseries

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tarjetapeliculasseries.ui.theme.TarjetaPeliculasSeriesTheme
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TarjetaPeliculasSeriesTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "loading") {
                    composable("loading") {
                        LoadingScreen(onTimeout = {
                            navController.navigate("main") {
                                popUpTo("loading") { inclusive = true }
                            }
                        })
                    }
                    composable("main") {
                        MainScreen(
                            onNavigateToPeliculas = { navController.navigate("movies") },
                            onNavigateToSeries = { navController.navigate("series") }
                        )
                    }
                    composable("movies") {
                        MoviesScreen(onNavigateBack = { navController.navigateUp() })
                    }
                    composable("series") {
                        SeriesScreen(onNavigateBack = { navController.navigateUp() })
                    }
                }
            }
        }
    }
}

// Pantalla de carga
@Composable
fun LoadingScreen(onTimeout: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_peliculas_series), // Asegúrate que el nombre del recurso sea correcto
                contentDescription = "Logo Películas y Series",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp) // Cambia el tamaño según sea necesario
                    .clip(shape = CircleShape) // Recortar en forma redonda
            )
            Spacer(modifier = Modifier.height(16.dp)) // Espacio entre el logo y el texto
            Text(
                text = "Películas y Series",
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 28.sp), // Texto grande
                color = Color(0xFF0000FF), // Color azul
                textAlign = TextAlign.Center
            )
        }
    }

    // Simula la pantalla de carga y navega tras 2 segundos
    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()  // Navegar a la siguiente pantalla
    }
}

// Pantalla principal que maneja la navegación entre pantallas
@Composable
fun MainScreen(onNavigateToPeliculas: () -> Unit, onNavigateToSeries: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_peliculas_series), // Asegúrate que el nombre del recurso sea correcto
            contentDescription = "Logo Películas y Series",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(300.dp) // Cambia el tamaño según sea necesario
                .clip(shape = CircleShape) // Recortar en forma redonda
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
        )

        Button(onClick = onNavigateToPeliculas) {
            Text(text = "Ver Películas")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateToSeries) {
            Text(text = "Ver Series")
        }
    }
}

// Pantalla de Películas
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoviesScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Películas") },
                actions = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(painter = painterResource(id = R.drawable.ic_volver), contentDescription = "Volver")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFEFEF)), // Cambiar el color de fondo
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Películas",
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 30.sp), // Texto más grande
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp), // Espaciado
                textAlign = TextAlign.Center // Centrar texto
            )

            val movies = listOf(
                MediaItem("El Irlandés", "Netflix"),
                MediaItem("El Hombre de tu Vida", "Amazon Prime"),
                MediaItem("Matrix Resurrecciones", "Max"),
                MediaItem("Avatar: El Camino del Agua", "Disney Plus"),
                MediaItem("Roma", "Netflix")
            )

            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(movies) { movie ->
                    MovieItem(movie)
                }
            }
        }
    }
}

// Pantalla de Series
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SeriesScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Series") },
                actions = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(painter = painterResource(id = R.drawable.ic_volver), contentDescription = "Volver")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFEFEF)), // Cambiar el color de fondo
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Series",
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 30.sp), // Texto más grande
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp), // Espaciado
                textAlign = TextAlign.Center // Centrar texto
            )

            val series = listOf(
                MediaItem("La Casa de Papel", "Netflix"),
                MediaItem("La Rueda del Tiempo", "Amazon Prime"),
                MediaItem("The Sopranos", "Max"),
                MediaItem("Loki", "Disney Plus"),
                MediaItem("Stranger Things", "Netflix"),
                MediaItem("Black List", "Netflix")
            )

            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(series) { serie ->
                    SeriesItem(serie)
                }
            }
        }
    }
}

// Modelo de datos para películas y series
data class MediaItem(
    val title: String,
    val platform: String
)

// Composable para mostrar una película
@Composable
fun MovieItem(movie: MediaItem) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleMedium.copy(color = Color.Black) // Cambiar color a negro
        )
        Text(
            text = "Plataforma: ${movie.platform}",
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray) // Cambiar color a gris
        )
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

// Composable para mostrar una serie
@Composable
fun SeriesItem(series: MediaItem) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = series.title,
            style = MaterialTheme.typography.titleMedium.copy(color = Color.Black) // Cambiar color a negro
        )
        Text(
            text = "Plataforma: ${series.platform}",
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray) // Cambiar color a gris
        )
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

// Previsualización de la pantalla de carga
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    TarjetaPeliculasSeriesTheme {
        LoadingScreen(onTimeout = {})
    }
}

// Previsualización de la pantalla de películas
@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    TarjetaPeliculasSeriesTheme {
        MoviesScreen(onNavigateBack = {})
    }
}

// Previsualización de la pantalla de series
@Preview(showBackground = true)
@Composable
fun SeriesScreenPreview() {
    TarjetaPeliculasSeriesTheme {
        SeriesScreen(onNavigateBack = {})
    }
}

// Previsualización de la pantalla principal
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TarjetaPeliculasSeriesTheme {
        MainScreen(onNavigateToPeliculas = {}, onNavigateToSeries = {})
    }
}
