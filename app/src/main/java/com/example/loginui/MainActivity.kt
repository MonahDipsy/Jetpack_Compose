package com.example.loginui

import android.os.Bundle
import android.webkit.WebView.VisualStateCallback
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.Placeholder
import com.example.loginui.ui.theme.LoginUITheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Login()
                    }
                }
            }
        }
    }
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Login() {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.login_bg),
            contentDescription = "Login",
            modifier = Modifier
                .fillMaxSize()
                .blur(6.dp),
            contentScale = ContentScale.Crop

        )

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .alpha(0.6f)
            .clip(
                CutCornerShape(
                    topStart = 8.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 8.dp
                )
            )
            .background(MaterialTheme.colors.background)
                
            )
        Column((Modifier
            .fillMaxSize()
            .padding(48.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround

        ) {
            LoginHeader()
            LoginFields(username, password,
            onUsernameChange = {
            username = it
            }, onPasswordChange = {
            password = it
                },
            onForgotPasswordClick = {

            })
            LoginFooter(
                onSignInClick = {},
                onSignUpClick = {}
            )

            
        }

    }

}

@Composable
fun LoginHeader() {
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        
    }
    Text(text = "Welcome Back!", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold)
    Text(text = "Sign in to continue", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)


}


@Composable
fun ColumnScope.LoginFields(username: String, Password: String,
                onUsernameChange: (String) -> Unit,
                onPasswordChange: (String) -> Unit,
                onForgotPasswordClick: () -> Unit,
){
    Column() {
        LoginField(value = username,
            label = "Username" ,
            placeholder = "Enter your Email",
            onValueChange = onUsernameChange,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            }
        )


        Spacer(modifier = Modifier.height(8.dp))


        LoginField(value = Password,
            label = "Password",
            placeholder = "Enter your Password",
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password")
            }
        )
        TextButton(onClick = onForgotPasswordClick, modifier = Modifier.align(Alignment.End)) {
            Text(text = "Forgot Password?")
        
    }



    }

}


@Composable
fun LoginFooter(
    onSignInClick: () ->Unit,
    onSignUpClick: () ->Unit

){
    Column() {
        TextButton(onClick = onSignUpClick) {
            Text(text = "Don't have an account?, Click here")
        }
        
    }
    Button(onClick = onSignInClick, modifier = Modifier.fillMaxWidth()) {
        Text(text = "Sign In")
    }



}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginField(value: String,
               label: String,
               placeholder: String,
               visualTransformation: VisualTransformation = VisualTransformation.None,
               keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
               leadingIcon: @Composable (() -> Unit)? = null,
               trailingIcon: @Composable (() -> Unit)? = null,
               onValueChange: (String) -> Unit) {
    OutlinedTextField(value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },

        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}
