package com.example.introcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.introcompose.ui.theme.IntroComposeTheme

// Partimos de un proyecto con la plantilla de Compose Activity

private val names = listOf(
    FullName("Iago", "Aspas"),
    FullName("Vicente", "Guaita"),
    FullName("Tasos", "Douvikas"),
    FullName("Hugo","Álvarez"),
    FullName("Williot","Swedberg"),
    FullName("Hugo","Sotelo"),
    FullName("Fran","Beltrán"),
    FullName("Borja","Iglesias"),
    FullName("Pablo","Durán"),
    FullName("Carlos","Domínguez"),
    FullName("Óscar","Mingueza"),
    FullName("Alexander","Mostovoi"),
    FullName("Vlado","Gudelj"),
    FullName("Valery","Karpin"),
    FullName("Jose Manuel", "Pinto"),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita el modo de pantalla completa
        setContent { // setContent para establecer el contenido de la actividad
            /**
             * Los temas también son elementos componibles, por lo que podemos envolver cualquier elemento componible con un tema
             * y también hay que tenerlo en cuenta en la jerarquía de componibles
             * Los temas están alojados en la carpeta ui/theme y se crean con la plantilla de Compose Theme
             * En la carpeta podéis encontrar el archivo Theme.kt con el tema de la aplicación
             * También el archivo Typography.kt con la tipografía de la aplicación
             * Y el archivo Color.kt con los colores de la aplicación
             * En el archivo Theme.kt, se definen los temas claro y oscuro
             * Y la función TutorialComposeTheme para establecer el tema de la aplicación
             * En el archivo Typography.kt, se definen los estilos de texto de la aplicación
             * En el archivo Color.kt, se definen los colores de la aplicación
             * El tema por defecto hereda de MaterialTheme que es el tema de Material Design, pero se puede cambiar
             * MaterialDesign es un sistema de diseño creado por Google que se utiliza en Android y en la web
             * Esto es importante para mantener la coherencia visual en la aplicación pero es un tema muy extenso
             */
            
            IntroComposeTheme { // Para establecer el tema de la activiadad
                   ScreenContent(
                        names
                    )
                }
            }
        }
    }

    // @Composable para definir que esto es una función composable
    // Si mostramos dos Tesxt juntos:
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
fun Greeting(fullName: FullName) {
  Column (
         modifier = Modifier.padding(8.dp)
  ){ // Para mostar los elkementos en una dispositiva
      CustomText(
          text = "Hello ${fullName.name}!",
          color = MaterialTheme.colorScheme.primary,
          style = MaterialTheme.typography.titleLarge,
      )
      Spacer(modifier = Modifier.height(8.dp))
      CustomText(
          text = "Goodbye ${fullName.surname}!",
          color = MaterialTheme.colorScheme.onBackground
      )

  }
}

/**
 * Los modificadores son una forma de cambiar la apariencia de los elementos componibles
 * Se pueden aplicar a cualquier elemento componible
 * Hacemos que el customText tenga un color y un estilo de texto personalizado
 * Pero para la tipografía le damos un valor por defecto
 */
@Composable
fun CustomText(text: String, color: Color, style: TextStyle = TextStyle.Default) {
    Text(
        text = text,
        color = color, // Se suele dejar la coma ·,· al final de las propiedades para poder añadir más propiedades
        style = style
    )
}

/**
 * Data class para almacenar el nombre y el apellido de una persona
 * @param name nombre de la persona
 * @param surname apellido de la persona
 * Así podemos pasar un objeto de tipo FullName a la función Greeting
 */
data class FullName(val name: String, val surname: String)

@Composable
fun RowContent(fullName: FullName) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .fillMaxSize()
    ){
        Image(
            contentDescription = "Android logo",
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .size(64.dp)
                .clip(shape = CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        )
        Greeting(fullName)
    }
}

@Composable
fun ScreenContent(names: List<FullName>) {
    /** Utilizaremos LazyColumn para mostrar una lista de elementos de forma eficiente
     * LazyColumn es un componente que solo renderiza los elementos que son visibles en la pantalla
     * y los que están cerca de la pantalla para poder desplazar la lista de forma eficiente
     * Sustituye a Column y a ScrollView
     */

    LazyColumn {//LazyColumn para mostar una lista de elementos de forma eficiente
        items(names) {
            RowContent(it)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RowAsListPreview() {
    val scrollState = rememberScrollState()
   Column(
       modifier = Modifier.verticalScroll(scrollState)
   ) {
       RowContent(names[0])
       RowContent(names[1])
       RowContent(names[2])
       RowContent(names[3])
       RowContent(names[4])
       RowContent(names[5])
       RowContent(names[6])
       RowContent(names[1])
       RowContent(names[0])
       RowContent(names[2])
       RowContent(names[8])
       RowContent(names[9])
       RowContent(names[0])
       RowContent(names[1])
       RowContent(names[5])
   }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    IntroComposeTheme {
        ScreenContent(names)
    }
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true)
@Composable
fun GreetingPreviewDark() {
    IntroComposeTheme {
        ScreenContent(names)
    }
}