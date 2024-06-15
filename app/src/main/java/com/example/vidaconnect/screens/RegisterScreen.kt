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
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedIconButton
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vidaconnect.components.AlertDialogExample
import com.example.vidaconnect.model.User
import com.example.vidaconnect.service.RetrofitFactory
import com.example.vidaconnect.ui.theme.Secondary
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RegisterScreen(navController: NavHostController) {

    var nameState by remember {
        mutableStateOf("")
    }

    var emailState by remember {
        mutableStateOf("")
    }

    var dtNascState by remember {
        mutableStateOf("")
    }

    var documentState by remember {
        mutableStateOf("")
    }

    var passwordState by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val openAlertDialog = remember { mutableStateOf(false) }

    fun register() {

        // Formatter para parsear a data de entrada
        val inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        // Formatter para formatar a data no formato desejado
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'00:00:00.000'Z'")

        // Parseando a data de entrada
        val localDate = LocalDate.parse(dtNascState, inputFormatter)

        // Formatar a data no formato desejado
        val formattedDate = localDate.format(outputFormatter)

        val user = User(
            name = nameState,
            document = documentState,
            dtNasc = formattedDate,
            email = emailState,
            password = passwordState
        )

        val call = RetrofitFactory().userService().createUser(user)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
//                TODO Fazer uma mensagem de sucesso!
//
                openAlertDialog.value = true

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("TESTE", t.message.toString())
            }

        })

    }

    when {
        openAlertDialog.value -> {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    openAlertDialog.value = false
                    navController.navigate("login")},
                dialogTitle = "Cadastro realizado com sucesso!",
                dialogText = "Faça autenticação na tela de login para acesar o aplicativo.",
                icon = Icons.Default.CheckCircleOutline,
            )
        }
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
            text = "Cadastro",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Secondary
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            value = nameState,
            onValueChange = { nameState = it },
            label = {
                Text(text = "Nome")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            value = emailState,
            onValueChange = { emailState = it },
            label = {
                Text(text = "E-mail")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
            )
        )



        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            value = dtNascState,
            onValueChange = { dtNascState = it },
            label = {
                Text(text = "Data de nascimento")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            value = documentState,
            onValueChange = { documentState = it },
            label = {
                Text(text = "CPF")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
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


        Button(onClick = { register() }) {
            Text(text = "Cadastrar")
        }
    }
}