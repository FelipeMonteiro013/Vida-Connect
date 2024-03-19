package com.example.vidaconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
                    items(3) { index -> AppointmentCard() }
                }

            }
        }
    }

}

@Composable
fun AppointmentCard() {
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
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Outlined.ArrowForwardIos,
                    contentDescription = "",
                    tint = TextDarkGray,

                    )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppointmentScreenPreview() {
    val navController = rememberNavController()
    AppointmentScreen(navController)
}

@Preview(showBackground = false)
@Composable
private fun CardAppointmentPreview() {
    AppointmentCard()
}
