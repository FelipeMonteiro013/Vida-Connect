package com.example.vidaconnect.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ContentPasteSearch
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vidaconnect.R
import com.example.vidaconnect.ui.theme.Primary
import com.example.vidaconnect.ui.theme.Secondary
import com.example.vidaconnect.ui.theme.TextGray
import com.example.vidaconnect.ui.theme.VidaConnectTheme

@Composable
fun HomeScreen(navController: NavController) {
    VidaConnectTheme {
        Surface {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        Image(
                            painterResource(id = R.drawable.vida_connect_logo),
                            contentDescription = null,

                            Modifier.size(100.dp)
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = null,
                                tint = Primary,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(text = "Olá,", fontSize = 20.sp, color = Secondary)
                        Text(
                            text = "Usuário",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Secondary
                        )
                    }
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        value = "",
                        onValueChange = {},
                        placeholder = {
                            Text(text = "O que você procura hoje?")
                        },
                        maxLines = 1,
                        suffix = {
                            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedSuffixColor = Primary,
                            unfocusedBorderColor = Primary,
                            unfocusedPlaceholderColor = TextGray
                        ),
                        shape = RoundedCornerShape(10.dp)

                    )
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                    ) {
                        item {
                            MenuButton(
                                title = "Histórico médico",
                                icon = Icons.Outlined.ContentPasteSearch,
                                navController

                            )
                        }
                        item {
                            MenuButton(
                                title = "Consultas",
                                icon = Icons.Outlined.CalendarMonth,
                                navController
                            )
                        }
                        item {
                            MenuButton(
                                title = "Exames",
                                icon = Icons.Outlined.Task,
                                navController
                            )
                        }
                        item {
                            MenuButton(
                                title = "Agenda",
                                icon = Icons.Outlined.Event,
                                navController
                            )
                        }
                    }
                }
                Row {


                    NavigationBar(
                        containerColor = Primary,
                        modifier = Modifier.clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))
                    ) {
                        NavigationBarItem(
                            icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
                            label = { Text("Início") },
                            selected = true,
                            onClick = { },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                unselectedIconColor = Secondary,
                                selectedTextColor = Color.White,
                                unselectedTextColor = Secondary,
                                indicatorColor = Primary

                            )
                        )
                        NavigationBarItem(
                            icon = { Icon(Icons.Outlined.SupportAgent, contentDescription = null) },
                            label = { Text("Atendimento") },
                            selected = false,
                            onClick = { }
                        )
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    Icons.Outlined.AccountCircle,
                                    contentDescription = null
                                )
                            },
                            label = { Text("Perfil") },
                            selected = false,
                            onClick = { }
                        )
                    }
                }
            }
        }

    }
}


@Composable
fun MenuButton(title: String, icon: ImageVector, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(140.dp)
            .padding(10.dp)
            .clickable {
                navController.navigate("appointment")
            },

    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Primary.copy(alpha = 0.15f))
        ) {
            Icon(
                icon,
                contentDescription = "",
                tint = Secondary,

                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )

        }

        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = Secondary
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}