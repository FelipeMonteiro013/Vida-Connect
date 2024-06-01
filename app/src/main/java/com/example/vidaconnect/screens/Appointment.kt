package com.example.vidaconnect.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vidaconnect.ui.theme.BackgroundColor
import com.example.vidaconnect.ui.theme.Primary
import com.example.vidaconnect.ui.theme.Secondary
import com.example.vidaconnect.ui.theme.TextDarkGray
import com.example.vidaconnect.ui.theme.VidaConnectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(navController: NavController) {
    var search by remember { mutableStateOf("") }

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
                        IconButton(onClick = { navController.navigate("home") }) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "Back button",
                                tint = Primary
                            )
                        }
                    },
                )

                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 20.dp),
                ) {
                    OutlinedTextField(
                        placeholder = { Text(text = "Pesquisar consulta") },
                        maxLines = 1,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 10.dp),
                        suffix = {
                            Icon(Icons.Outlined.Search, contentDescription = "")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedLabelColor = Primary,
                            unfocusedBorderColor = Primary,
                            unfocusedSuffixColor = Primary,
                            focusedLabelColor = Secondary,
                            focusedBorderColor = Secondary,
                            focusedSuffixColor = Secondary,

                            ),
                        value = search,
                        onValueChange = {
                            search = it
                        },
                        shape = RoundedCornerShape(10.dp)
                    )


                    Box(
                        modifier = Modifier
                            .width(58.dp)
                            .height(58.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(Primary),
                        contentAlignment = Alignment.Center


                    ) {
                        Icon(
                            Icons.Outlined.CalendarMonth,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 15.dp)
                        ) {

                            Icon(
                                Icons.Outlined.Add,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(25.dp)
                            )
                            Text(
                                text = "Nova consulta",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Text(
                    text = "Consultas Agendadas",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Primary,
                )

                Divider(modifier = Modifier.padding(vertical = 10.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(20) { AppointmentCard(navController) }
                }

            }
        }
    }

}

@Composable
fun AppointmentCard(navController: NavController) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()

            .height(100.dp),

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
                    text = "Hospital Santa Marta",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDarkGray
                )
                Text(
                    text = "Cardiologista", fontSize = 17.sp, color = TextDarkGray
                )
                Text(
                    text = "18/03/2024 - 09:30", fontSize = 18.sp, color = Secondary
                )
            }
            IconButton(onClick = {
                navController.navigate("medical_appointment_details")
            }) {
                Icon(
                    Icons.Outlined.ArrowForwardIos,
                    contentDescription = "",
                    tint = TextDarkGray,

                    )
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MedicalAppointmentDetailsScreenPreview() {
    val navController = rememberNavController()
    MedicalAppointmentDetailsScreen(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicalAppointmentDetailsScreen(navController: NavHostController) {
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
