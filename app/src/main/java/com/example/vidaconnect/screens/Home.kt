package com.example.vidaconnect.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ContentPasteSearch
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vidaconnect.ui.theme.Primary
import com.example.vidaconnect.ui.theme.Secondary
import com.example.vidaconnect.ui.theme.VidaConnectTheme

@Composable
fun HomeScreen() {
    VidaConnectTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Text(
                    text = "Categorias",
                    color = Secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(20.dp, 10.dp)

                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                ) {
                    item {
                        MenuButton(
                            title = "Histórico médico",
                            icon = Icons.Outlined.ContentPasteSearch
                        )
                    }
                    item {
                        MenuButton(
                            title = "Consultas",
                            icon = Icons.Outlined.CalendarMonth
                        )
                    }
                    item {
                        MenuButton(
                            title = "Exames",
                            icon = Icons.Outlined.Task
                        )
                    }
                    item {
                        MenuButton(
                            title = "Agenda",
                            icon = Icons.Outlined.Event
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun MenuButton(title: String, icon: ImageVector) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(140.dp)
            .padding(10.dp)
            .clickable {
                println("Click!")
            }
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
    HomeScreen()
}