package com.example.vidaconnect

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vidaconnect.model.MedicalAppointment
import com.example.vidaconnect.model.User
import com.example.vidaconnect.screens.AppointmentScreen
import com.example.vidaconnect.screens.HomeScreen
import com.example.vidaconnect.screens.LoginScreen
import com.example.vidaconnect.screens.MedicalAppointmentDetailsScreen
import com.example.vidaconnect.screens.NewAppointment
import com.example.vidaconnect.screens.RegisterScreen
import com.example.vidaconnect.screens.SchedulingScreen
import com.example.vidaconnect.ui.theme.VidaConnectTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            VidaConnectTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("register") { RegisterScreen(navController) }
                    composable("home/{userJson}") { backStackEntry: NavBackStackEntry ->
                        val userJson = backStackEntry.arguments?.getString("userJson")
                        val user = remember {
                            Gson().fromJson(userJson, User::class.java)
                        }
                        HomeScreen(
                            navController,
                            user
                        )
                    }

                    composable("appointment/{userJson}") { backStackEntry: NavBackStackEntry ->
                        val userJson = backStackEntry.arguments?.getString("userJson")
                        val user = remember {
                            Gson().fromJson(userJson, User::class.java)
                        }
                        AppointmentScreen(navController, user)
                    }
                    composable("scheduling_screen") { SchedulingScreen(navController) }
                    composable("new_appointment/{userJson}") { backStackEntry: NavBackStackEntry ->
                        val userJson = backStackEntry.arguments?.getString("userJson")
                        val user = remember {
                            Gson().fromJson(userJson, User::class.java)
                        }
                        NewAppointment(navController, user)
                    }

                }
//            HomeScreen()
//            AppointmentScreen()
            }
        }
    }
}

@Composable
fun VidaConnectScreen(modifier: Modifier = Modifier) {

}