package com.example.pantalla

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pantalla.ui.theme.PantallaTheme
import com.example.pantalla.R


data class CoffeData(
    val nombreCafe: String = "",
    val precio: Double = 0.0)

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PantallaTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Green),
                    horizontalAlignment =  Alignment.CenterHorizontally
                ) {


                    var tipoCafe = remember { mutableStateOf("") }
                    var numTazas = remember { mutableStateOf("") }
                    var onzas = remember { mutableStateOf("") }
                    var precioTaza = remember { mutableStateOf("") }
                    var totalPagar = remember { mutableStateOf("0.0") }

                    //Lista
                    val listaCafe = listOf(
                        CoffeData("Americano", 20.0),
                        CoffeData("Americano descafinaod", 25.0),
                        CoffeData("Café de Olla", 23.0),
                        CoffeData("Capuccino", 23.00),
                        CoffeData("Capuccino Canela", 25.00),
                        CoffeData("Capuccino Descalactosado", 25.00),
                        CoffeData("Frappe",30.0),
                        CoffeData("Frappe Oreo", 40.0),
                        CoffeData("Late", 30.0),
                        CoffeData("Moka", 35.0 )
                    )

                    var context = LocalContext.current

                    //Titulo
                    Row ( modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically) {
                        //TEXTO DE BIENVENIDO
                        Text(
                            modifier = Modifier
                                .weight(0.6f)
                                .padding(20.dp, 10.dp, 0.dp, 30.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.ExtraBold,
                            text = "Bienvenidos",
                            fontSize = 30.sp,
                        )

                        //Imagen o logotipo
                        Image(
                            modifier = Modifier
                                .weight(0.4f)
                                .clip(RoundedCornerShape(20.dp))
                                .size(100.dp),
                            painter = painterResource(id = R.drawable.cafe_negro),
                            contentScale = ContentScale.Crop,
                            contentDescription = ""
                        )
                    }

                    Divider(
                        modifier = Modifier
                            .height(2.dp)
                            .width(300.dp),
                        color = Color.Gray
                    )

                    //Tipo de cafe
                    OutlinedTextField(
                        label = { Text(text = "Tipo de café que deseas") },
                        value = tipoCafe.value,
                        enabled =  false,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            keyboardType = KeyboardType.Text
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedTextColor = Color.Blue,
                            containerColor = Color.White
                        ),
                        onValueChange = {
                            if (tipoCafe.value.length <= 30)
                                tipoCafe.value = it
                        })

                   //Menú de cafe disponibles
                   LazyColumn (
                       modifier = Modifier.height(300.dp)
                   ) {
                       itemsIndexed(listaCafe) { pos, cafe ->
                           Column(
                               modifier = Modifier
                                   .clickable {
                                      tipoCafe.value = cafe.nombreCafe
                                      precioTaza.value = cafe.precio.toString()
                                   }
                                   .fillMaxWidth()
                                   .padding(top = 5.dp),
                                   verticalArrangement = Arrangement.Center
                           ) {
                               Text(
                                   modifier = Modifier
                                       .fillMaxWidth()
                                       .padding(horizontal = 30.dp)
                                       .height(40.dp),
                                   textAlign = TextAlign.Start,
                                   text = cafe.nombreCafe)

                               Divider(
                                   modifier = Modifier
                                       .height(2.dp)
                                       .fillMaxWidth(),
                                   color = Color.Gray)
                           }//Column
                       }//itemIndexede
                   }


                    //Datos de la taza de cafe
                    Row(modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 30.dp)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center){
                        //Número de tazas
                        OutlinedTextField(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .weight(0.5f),
                           label = { Text(text = "Número tazas") },
                            value = numTazas.value,
                            onValueChange = {
                                if (numTazas.value.length <=2) {
                                    numTazas.value = it
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = Color.Blue,
                                containerColor = Color.White
                            ))

                        //Precio de la taza
                        OutlinedTextField(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .weight(0.5f),
                            label = { Text(text = "Precio") },
                            enabled = false,
                            value = precioTaza.value,
                            onValueChange = {
                                if (precioTaza.value.length <=3) {
                                    precioTaza.value = it
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedTextColor = Color.Blue,
                                containerColor = Color.White
                            ))
                }//Row de los datos del cafe

                    Button(
                        onClick = {
                            if (numTazas.value.isEmpty() || precioTaza.value.isEmpty()) {
                                Toast.makeText(context, "No deje datos vacíos", Toast.LENGTH_SHORT).show()
                            }else {
                                val  temporal = numTazas.value.toInt() * precioTaza.value.toDouble()
                                totalPagar.value = temporal.toString()
                            }

                        }) {
                        Text(text = "Calacular total pagar")
                    }

                    //Muestra el total a pagar
                    Text(text = "Total a pagar ${totalPagar.value}")

                }//Column
            }//Theme
        }//Content
    }//onCreate
} //MainAtciviti

