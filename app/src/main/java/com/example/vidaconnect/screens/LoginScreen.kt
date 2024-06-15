package com.example.vidaconnect.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.vidaconnect.R
import com.example.vidaconnect.components.AlertDialogExample
import com.example.vidaconnect.model.Login
import com.example.vidaconnect.model.User
import com.example.vidaconnect.service.RetrofitFactory
import com.example.vidaconnect.ui.theme.Primary
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(navHostController: NavHostController) {

    var emailState by remember {
        mutableStateOf("felipe@email5.com")
    }

    var passwordState by remember {
        mutableStateOf("123")
    }
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val openAlertDialog = remember { mutableStateOf(false) }

    fun Login() {
        val loginRequest = Login(emailState, passwordState)
        val call = RetrofitFactory().userService().login(loginRequest)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.body()?.id == null) {
                    openAlertDialog.value = true
                } else {
                    val userJson = Gson().toJson(response.body())
                    navHostController.navigate("home/${userJson}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.i("TESTE", t.message.toString())
                openAlertDialog.value = true

            }
        })

    }

    when {
        openAlertDialog.value -> {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    openAlertDialog.value = false
                    println("Confirmation registered") // Add logic here to handle confirmation.
                },
                dialogTitle = "Falha ao autenticar",
                dialogText = "Verifique se o seu e-mail e senha estão corretos",
                icon = Icons.Default.ErrorOutline,
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painterResource(id = R.drawable.vida_connect_logo),
            contentDescription = null,
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            value = emailState, onValueChange = {
                emailState = it
            },
            label = {
                Text(text = "E-mail")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            value = passwordState,
            onValueChange = {
                passwordState = it
            },
            label = {
                Text(text = "Senha")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    if (passwordVisible) {
                        Icon(
                            imageVector = Icons.Outlined.VisibilityOff,
                            contentDescription = "Tornar senha visivel"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Visibility,
                            contentDescription = "Tornar senha visivel"
                        )
                    }

                }
            },
        )
        Button(
            onClick = { Login() },
            modifier = Modifier
                .padding(vertical = 10.dp)
                .width(200.dp)
        ) {
            Text(text = "Entrar", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        }

        Divider(modifier = Modifier.padding(vertical = 30.dp))

        Text(text = "Ainda não tenho conta")
        Text(
            text = "Cadastrar-se",
            modifier = Modifier
                .clickable {
                    navHostController.navigate("register")
                }
                .padding(15.dp),
            fontSize = 20.sp,
            color = Primary,
            fontWeight = FontWeight.Bold
        )


    }

}
