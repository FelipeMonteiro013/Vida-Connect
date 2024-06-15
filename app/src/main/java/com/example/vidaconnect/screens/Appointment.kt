package com.example.vidaconnect.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidaconnect.model.MedicalAppointment
import com.example.vidaconnect.model.User
import com.example.vidaconnect.service.RetrofitFactory
import com.example.vidaconnect.ui.theme.Primary
import com.example.vidaconnect.ui.theme.Secondary
import com.example.vidaconnect.ui.theme.TextDarkGray
import com.example.vidaconnect.ui.theme.VidaConnectTheme
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(navController: NavController, user: User) {
    val userId by remember {
        mutableStateOf(user.id)
    }

    val appointmentList = listOf<MedicalAppointment>()

    var listAppointment by remember {
        mutableStateOf(appointmentList)
    }

    val call = RetrofitFactory().medicalAppointment().getMedicalAppointmentByPatientId(userId!!)

    call.enqueue(object : Callback<List<MedicalAppointment>> {
        override fun onResponse(
            call: Call<List<MedicalAppointment>>,
            response: Response<List<MedicalAppointment>>
        ) {
            listAppointment = response.body()!!
        }

        override fun onFailure(call: Call<List<MedicalAppointment>>, t: Throwable) {
            Log.e("TESTE", t.message.toString())
        }

    })

    VidaConnectTheme(darkTheme = false) {
        Surface {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Consultas",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Primary,
                            maxLines = 1
                        )
                    },
                    navigationIcon = {
                        val userJson = Gson().toJson(user)
                        IconButton(onClick = { navController.navigate("home/${userJson}") }) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "Back button",
                                tint = Primary
                            )
                        }
                    },
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp), horizontalArrangement = Arrangement.End
                ) {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(Primary),
                        contentAlignment = Alignment.Center


                    ) {
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier
//                                .padding(horizontal = 15.dp)
//                                .clickable {
//                                    val userJson = Gson().toJson(user)
//                                    navController.navigate("new_appointment/${userJson}")
//                                }
//                        ) {
//
//                            Icon(
//                                Icons.Outlined.Add,
//                                contentDescription = "",
//                                tint = Color.White,
//                                modifier = Modifier.size(25.dp)
//                            )
//                            Text(
//                                text = "Nova consulta",
//                                color = Color.White,
//                                fontWeight = FontWeight.Bold
//                            )
//                        }
                    }
                }
                Text(
                    text = "Consultas Agendadas",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Primary,
                )

                Divider(modifier = Modifier.padding(vertical = 10.dp))
                if (listAppointment.isEmpty()) {
                    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                    }
                } else {


                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        items(listAppointment) { appointment ->
                            AppointmentCard(
                                navController,
                                appointment
                            )
                        }
                    }
                }
            }

        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppointmentCard(
    navController: NavController,
    appointment: MedicalAppointment,
) {

    var showDetails by remember {
        mutableStateOf(false)
    }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),

        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 20.dp)
        ) {
            Icon(
                Icons.Outlined.MedicalServices,
                contentDescription = "",
                tint = Primary,
                modifier = Modifier.size(40.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(20.dp, 10.dp)
                    .fillMaxHeight()

            ) {
                Text(
                    text = appointment.clinicId!!.corporateReason,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDarkGray
                )
                Text(
                    text = appointment.doctorId.name, fontSize = 17.sp, color = TextDarkGray
                )

                // Data no formato original
                val dataOriginal = appointment.date

                // Converter para um objeto ZonedDateTime
                val zonedDateTime = ZonedDateTime.parse(dataOriginal)

                // Definir o formato desejado
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")

                // Converter para o formato desejado
                val dataFormatada = zonedDateTime.format(formatter)

                Text(
                    text = dataFormatada, fontSize = 18.sp, color = Secondary
                )
                Text(
                    text = "Status: ${appointment.status}", fontSize = 18.sp, color = Secondary
                )
            }
            IconButton(onClick = {
                showDetails = !showDetails
            }) {
                if (showDetails) {
                    Icon(
                        Icons.Outlined.ArrowDropUp,
                        contentDescription = "",
                        tint = TextDarkGray,

                        )
                } else {
                    Icon(
                        Icons.Outlined.ArrowDropDown,
                        contentDescription = "",
                        tint = TextDarkGray,

                        )
                }
            }
        }
        if (showDetails) {


            Divider()
            Column(modifier = Modifier.padding(10.dp)) {

                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Informações da consulta",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Secondary
                    )
                    Divider(modifier = Modifier.padding(vertical = 10.dp), color = Secondary)
                    Text(text = "Paciente: ${appointment.patientId.name}")

                    // Data no formato original
                    val dataOriginal = appointment.patientId.dtNasc

                    // Converter para um objeto ZonedDateTime
                    val zonedDateTime = ZonedDateTime.parse(dataOriginal)

                    // Definir o formato desejado
                    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

                    // Converter para o formato desejado
                    val dataFormatada = zonedDateTime.format(formatter)

                    Text(text = dataFormatada)
                    Text(text = appointment.reason)
                    Text(text = appointment.symptoms)
                    Text(text = appointment.medicalHistory)
                    Text(
                        text = "Local da consulta",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Secondary,
                        modifier = Modifier.padding(top = 30.dp)
                    )
                    Divider(modifier = Modifier.padding(vertical = 10.dp), color = Secondary)
                    Text(text = appointment.clinicId!!.corporateReason)
                    Text(text = appointment.clinicId.address)
                    Text(text = appointment.clinicId.email)
                    Text(text = appointment.clinicId.phone)
                    Text(
                        text = "Médico",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Secondary,
                        modifier = Modifier.padding(top = 30.dp)
                    )
                    Divider(modifier = Modifier.padding(vertical = 10.dp), color = Secondary)
                    Text(text = appointment.doctorId.name)
                    Text(text = appointment.doctorId.crm)
                    Text(text = appointment.doctorId.email)
                }
            }
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun AppointmentScreenPreview() {
//    val navController = rememberNavController()
//    AppointmentScreen(navController)
//}
//
//@Preview(showBackground = false)
//@Composable
//private fun CardAppointmentPreview() {
//    AppointmentCard()
//}

