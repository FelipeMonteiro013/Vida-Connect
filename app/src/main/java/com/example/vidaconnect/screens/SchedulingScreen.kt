package com.example.vidaconnect.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vidaconnect.ui.theme.BackgroundColor
import com.example.vidaconnect.ui.theme.Primary
import com.example.vidaconnect.ui.theme.Secondary


@Preview
@Composable
private fun SchedulingScreenPreview() {
    val navController = rememberNavController()
    SchedulingScreen(navController)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SchedulingScreen(navController: NavController) {

    val step by remember {
        mutableStateOf("dados_pessoais")
    }

    var shareMedicalHistory by remember {
        mutableStateOf(true)
    }

    Surface {
        LazyColumn {
            item {


                if (step == "dados_pessoais") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                        ) {

                            Text(
                                text = "Dados Pessoais",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Primary,
                                modifier = Modifier.padding(16.dp)
                            )
                            Divider()
                            for (i in 1..5) Row(
                                modifier = Modifier.padding(
                                    horizontal = 16.dp, vertical = 4.dp
                                )
                            ) {
                                Text(
                                    text = "Nome:",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Secondary,
                                    modifier = Modifier.padding(end = 6.dp)

                                )
                                Text(
                                    text = "Felipe Monteiro da Silva Morais",
                                    fontSize = 16.sp,
                                )
                            }
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                        ) {

                            Text(
                                text = "Informações de Saúde",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Primary,
                                modifier = Modifier.padding(16.dp)
                            )
                            Divider()

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {

                                var isExpanded by remember {
                                    mutableStateOf(false)
                                }

                                val listOfMedicalSpecialties = listOf(
                                    "Cardiología",
                                    "Neurología",
                                    "Oncología",
                                    "Pediatría",
                                    "Dermatología"
                                )

                                var selectedSpecialties by remember {
                                    mutableStateOf(listOfMedicalSpecialties[0])
                                }

                                ExposedDropdownMenuBox(
                                    expanded = isExpanded,
                                    onExpandedChange = { isExpanded = !isExpanded }) {
                                    OutlinedTextField(value = selectedSpecialties,
                                        modifier = Modifier
                                            .menuAnchor()
                                            .fillMaxWidth(),
                                        label = {
                                            Text(text = "Especialidade")
                                        },
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = isExpanded
                                            )
                                        },

                                        onValueChange = {})
                                    ExposedDropdownMenu(
                                        expanded = isExpanded,
                                        onDismissRequest = { isExpanded = false }) {
                                        listOfMedicalSpecialties.forEachIndexed { index, s ->
                                            DropdownMenuItem(
                                                text = { Text(text = s) },
                                                onClick = {
                                                    selectedSpecialties =
                                                        listOfMedicalSpecialties[index]
                                                    isExpanded = false
                                                },
                                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                            )

                                        }

                                    }
                                }





                                OutlinedTextField(value = "", label = {
                                    Text(text = "Motivo da consulta")
                                }, modifier = Modifier.fillMaxWidth(), onValueChange = {})
                                OutlinedTextField(value = "", label = {
                                    Text(text = "Sintomas")
                                }, modifier = Modifier.fillMaxWidth(), onValueChange = {})

                                Text(text = "Compartilhar seu histórico medico?")
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = shareMedicalHistory,
                                        onClick = { shareMedicalHistory = true },
                                    )
                                    Text(text = "Sim", fontSize = 18.sp)
                                    RadioButton(
                                        selected = shareMedicalHistory == false,
                                        onClick = { shareMedicalHistory = false },
                                    )
                                    Text(text = "Não", fontSize = 18.sp)
                                }
                            }
                        }

                    }
                } else {
                    Text(text = "Else")
                }


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
                                    Icon(
                                        imageVector = Icons.Outlined.LocationOn,
                                        contentDescription = ""
                                    )
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
                            for (i in 1..3) Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                        rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis() - 1000)
                    DatePicker(
                        state = datePickerState, modifier = Modifier.padding(horizontal = 16.dp),

                        showModeToggle = false, title = {
                            Text(
                                text = "Selecionar data",
                                fontSize = 18.sp,
                                color = Secondary,
                                fontWeight = FontWeight.Bold
                            )
                        }, colors = DatePickerDefaults.colors(selectedDayContainerColor = Secondary)
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
    }
}