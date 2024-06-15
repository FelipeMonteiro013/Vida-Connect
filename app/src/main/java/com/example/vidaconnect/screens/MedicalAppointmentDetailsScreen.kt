package com.example.vidaconnect.screens

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidaconnect.model.MedicalAppointment
import com.example.vidaconnect.ui.theme.BackgroundColor
import com.example.vidaconnect.ui.theme.Primary
import com.example.vidaconnect.ui.theme.Secondary
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalAppointmentDetailsScreen(navController: NavController, appointment: MedicalAppointment) {
    val appointmentJson = Gson().toJson(appointment)
    Log.i("TESTE", "MedicalAppointmentDetailsScreen: $appointmentJson")
    Surface {
        Column(
            modifier = Modifier
                .background(BackgroundColor)
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)

            ) {

                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Informações da consulta",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Secondary
                    )
                    Divider(color = Secondary)
                    Text(text = "patient name")
                    Text(text = "patient dt_nasc")
                    Text(text = "reason")
                    Text(text = "symptoms")
                    Text(text = "medicalHistory")
                    Text(text = "Status")
                    Divider()
                    Text(text = "Local da consulta")
                    Text(text = "corporateReason")
                    Text(text = "address")
                    Text(text = "email")
                    Text(text = "phone")
                    Divider()
                    Text(text = "Médico")
                    Text(text = "name")
                    Text(text = "crm")
                    Text(text = "email")
                    Text(text = "specialty_id")
                    Divider()
                }

                Row(modifier = Modifier.padding(20.dp)) {


                    Icon(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp),
                        imageVector = Icons.Outlined.Photo,
                        contentDescription = ""
                    )
                    Column(verticalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = "Dr. Danilo Oliveira",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Primary
                        )
                        Text(text = "Dermatologista", fontSize = 18.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = "")
                            Text(text = "São Paulo, SP", fontSize = 18.sp)
                        }
                    }
                }
                Divider(modifier = Modifier.padding(horizontal = 20.dp))
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
//                    TODO: Vai virar um componente
                    for (i in 1..3)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Outlined.PeopleOutline,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(color = Primary.copy(alpha = 0.2f))
                                    .padding(10.dp),
                                tint = Secondary

                            )
                            Text(text = "3.500+", fontSize = 18.sp, color = Primary)
                            Text(text = "Pacientes", fontSize = 16.sp)
                        }
                }

            }
            val datePickerState =
                rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
            DatePicker(
                state = datePickerState,
                modifier = Modifier.padding(16.dp),
                showModeToggle = false,
                title = {
                    Text(
                        text = "Selecionar data",
                        fontSize = 18.sp,
                        color = Secondary,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = DatePickerDefaults.colors(selectedDayContainerColor = Secondary)
            )

            Text(
                text = "Selecione um horario",
                fontSize = 18.sp,
                color = Secondary,
                fontWeight = FontWeight.Bold
            )
            LazyRow {
                item {
                    Text(
                        text = "Selecione um horario",
                        fontSize = 18.sp,
                        color = Secondary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
