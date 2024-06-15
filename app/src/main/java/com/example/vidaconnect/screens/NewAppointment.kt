package com.example.vidaconnect.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vidaconnect.model.Clinic
import com.example.vidaconnect.model.Doctor
import com.example.vidaconnect.model.MedicalAppointment
import com.example.vidaconnect.model.NewMedicalAppointment
import com.example.vidaconnect.model.User
import com.example.vidaconnect.service.RetrofitFactory
import com.example.vidaconnect.ui.theme.Secondary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NewAppointment(navController: NavController, user: User) {

    var clinic = Clinic(

        id = "",
        corporateReason = "Clínica São José",
        cnpj = "12.345.678/0001-90",
        address = "Rua das Flores, 123, Centro, São Paulo, SP",
        phone = "(11) 1234-5678",
        email = "contato@clinicasaojose.com",
        status = true

    )
    var doctor = Doctor(

        id = "",
        crm = "Clínica São José",
        name = "Rua das Flores, 123, Centro, São Paulo, SP",
        phone = "(11) 1234-5678",
        email = "contato@clinicasaojose.com",
        status = true

    )

    var reasonState by remember {
        mutableStateOf("")
    }

    var symptomsState by remember {
        mutableStateOf("")
    }

    var dateState by remember {
        mutableStateOf("")
    }

    var medicalHistoryState by remember {
        mutableStateOf("")
    }

    var patientIdState by remember {
        mutableStateOf("")
    }
    var clinicIdState by remember {
        mutableStateOf("")
    }
    var doctorIdState by remember {
        mutableStateOf("")
    }


//    val openAlertDialog = remember { mutableStateOf(false) }
//
//    // Formatter para parsear a data de entrada
//    val inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
//
//    // Formatter para formatar a data no formato desejado
//    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'00:00:00.000'Z'")
//
//    // Parseando a data de entrada
//    val localDate = LocalDate.parse(dtNascState, inputFormatter)
//
//    // Formatar a data no formato desejado
//    val formattedDate = localDate.format(outputFormatter)




    fun Agendar () {
        val newAppointment = NewMedicalAppointment(
            symptoms = symptomsState,
            medicalHistory = medicalHistoryState,
            date = dateState,
            reason = reasonState,
            patientId = user.id!!,
            clinicId = "00000",
            doctorId = "00000",
            status = "Agendada"
        )

        val call = RetrofitFactory().medicalAppointment().createAppointment(newAppointment)
//
        call.enqueue(object : Callback<MedicalAppointment> {
            override fun onResponse(
                call: Call<MedicalAppointment>,
                response: Response<MedicalAppointment>
            ) {
                Log.i("TESTE", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<MedicalAppointment>, t: Throwable) {
                Log.e("TESTE", t.message.toString())
            }
        })
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            IconButton(onClick = {
                navController.navigate("login")
            }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "voltar para tela anterior",
                    tint = Secondary,
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        Text(
            modifier = Modifier.padding(bottom = 15.dp),
            text = "Nova Consulta",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Secondary
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            value = reasonState,
            onValueChange = { reasonState = it },
            label = {
                Text(text = "Motivo da consulta")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            value = symptomsState,
            onValueChange = { symptomsState = it },
            label = {
                Text(text = "Sintomas")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            )
        )



        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            value = dateState,
            onValueChange = { dateState = it },
            label = {
                Text(text = "Data do agendamento")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )




        Button(onClick = {
            Agendar()

        }) {
            Text(text = "Agendar")
        }
    }
}