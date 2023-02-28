package com.ersafra.autozap


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView


import java.util.*


@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.run {
            fillMaxSize()
                 .wrapContentSize(Alignment.Center)
        }
    ) {
        WhatsAppCorrect()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun ShareScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        //Chamar aqui a função
    }

}

@Preview(showBackground = true)
@Composable
fun ShareScreenPreview() {
    //Chamar aqui a função
}

@Composable
fun AssessScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        WebAssess()
    }
}

@Preview(showBackground = true)
@Composable
fun AssessScreenPreview() {
    AssessScreen()
}

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
    }

    AboutAutoZap()
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen()
}

@Composable
fun WebAssess() {
    val context = LocalContext.current
    val webIntent: Intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://play.google.com/store/apps/details?id=com.ersafra.autozap")
    )
    context.startActivity(webIntent)
}

@Composable
fun BannerAdView() {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth(),
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.MEDIUM_RECTANGLE)
                adUnitId = "ca-app-pub-2741955499317480/5604436115"
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}

// vamos mudar daqui para baixo--//
@Composable
fun WhatsAppCorrect() {
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") }
    var messageText by remember { mutableStateOf("") }
    var selectedApi by remember { mutableStateOf(WhatsAppApi.WhatsApp) }
    var isError by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { newPhoneNumber ->
                phoneNumber = newPhoneNumber.trim().replace("\\D".toRegex(), "")
                isError = !Regex("\\d{11}").matches(phoneNumber)
            },
            label = { Text(text = "Número do Telefone") },
            placeholder = { Text("Exemplo - 019989380032") },
            leadingIcon = { Icon(Icons.Default.Phone, null) },
            isError = isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = messageText,
            onValueChange = { messageText = it },
            label = { Text(text = "Mensagem(Opcional)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Selecione a Plataforma:")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                RadioButton(
                    selected = selectedApi == WhatsAppApi.WhatsApp,
                    onClick = { selectedApi = WhatsAppApi.WhatsApp },
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(text = "WhatsApp")
                RadioButton(
                    selected = selectedApi == WhatsAppApi.WhatsAppBusiness,
                    onClick = { selectedApi = WhatsAppApi.WhatsAppBusiness },
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(text = "W.Business")
            }
            OutlinedButton(
                onClick = {
                    WhatsAppOpen(
                        selectedApi,
                        phoneNumber,
                        messageText,
                        context
                    )
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
                    .fillMaxWidth()

            ) {
                   Text(text = "Abrir WhatsApp Chat",color = Color.White,)
            }
        }
        BannerAdView()
    }

}
enum class WhatsAppApi { WhatsApp, WhatsAppBusiness }

private fun WhatsAppOpen(
    api: WhatsAppApi,
    phoneNumber: String,
    messageText: String,
    context: Context,
) {
    val apiPackage =
        if (api == WhatsAppApi.WhatsAppBusiness) "com.whatsapp.w4b" else "com.whatsapp"

    val horaAtual = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val messageTextOne = when (horaAtual) {
        in 0..11 -> "Bom dia !"
        in 12..17 -> "Boa tarde !"
        else -> "Boa noite !"
    }
    val url = "https://api.whatsapp.com/send?phone=55$phoneNumber&text=$messageTextOne$messageText"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).setPackage(apiPackage)
    context.startActivity(intent)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AboutAutoZap() {
    val mContext = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.logo_ers),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            textAlign = TextAlign.Center,
            text = "Edson Safra®\n\nDesenvolvimento de Aplicações Android 2023\n\nVersão 3.1"
        )
        OutlinedButton(
            onClick = {val url = "https://api.whatsapp.com/send?phone=5519989380032?text=Olá!"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).setPackage("com.whatsapp")
                mContext.startActivity(intent)
            },
            border = BorderStroke(1.dp, Color.Gray),
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(text = "WhatsApp")

        }
    }
}




