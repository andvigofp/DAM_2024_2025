package com.example.pantalla

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
                    var context = LocalContext.current

                    //Titulo
                    Text(
                        modifier = Modifier
                            .padding(20.dp, 10.dp, 0.dp, 30.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.ExtraBold,
                        text = "Bienvenidos",
                        fontSize = 30.sp,
                    )

                    Divider(
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                            .height(2.dp)
                            .width(300.dp),
                        color = Color.Gray
                    )

                    //Imagen o logotipo
                    Image(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .size(200.dp),
                        painter = painterResource(id = R.drawable.cafe_negro),
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )

                    //Tipo de cafe
                    OutlinedTextField(
                        label = { Text(text = "Tipo de café que deseas") },
                        value = tipoCafe.value,
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

                    Row(modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                        Text(text = "hola")
                        Text(text = "Aqui andamos")
                        Text(text = "Perfecto")
                    }//Row
                    Text(text = "Aquí seguimos")
                }//Column
            }//Theme
        }//Content
    }//onCreate
} //MainAtciviti

