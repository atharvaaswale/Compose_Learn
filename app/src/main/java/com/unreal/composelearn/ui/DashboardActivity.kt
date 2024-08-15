package com.unreal.composelearn.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import coil.compose.AsyncImage
import com.unreal.composelearn.IOmodel.WeatherResponse
import com.unreal.composelearn.retrofit.NetworkResponse
import com.unreal.composelearn.ui.theme.UIColor
import com.unreal.composelearn.ui.theme.cardBG
import com.unreal.composelearn.ui.theme.dimFontLightTheme
import com.unreal.composelearn.ui.theme.textDim
import com.unreal.composelearn.ui.theme.textNormal
import com.unreal.composelearn.viewModels.WeatherViewModel

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainUI(this)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUI(ctx: ViewModelStoreOwner) {
    val vm = ViewModelProvider(ctx)[WeatherViewModel::class.java]
    val weatherResult = vm.weatherResultList.observeAsState()
    var searchValue by remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(UIColor)
    ) {
        TopAppBar(
            title = { Text("Weather-it") },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = UIColor, titleContentColor = textNormal)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = searchValue,
                    onValueChange = {
                        searchValue = it
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = TextFieldDefaults.colors(
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = cardBG,
                        unfocusedContainerColor = cardBG,
                        focusedTextColor = textNormal,
                        unfocusedTextColor = textNormal
                    ),
                    placeholder = { Text("Search any location...") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search,
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        keyboardController!!.hide()
                        vm.getWeatherData(searchValue)
                    })
                )
                IconButton(
                    onClick = {
                        vm.getWeatherData(searchValue)
                        keyboardController!!.hide()},
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = textNormal
                    )
                }
            }

            when(val result = weatherResult.value) {
                is NetworkResponse.Error -> {
                    Text(text = result.message)
                }
                NetworkResponse.Loading -> {
                    CircularProgressIndicator()
                }
                is NetworkResponse.Success -> {
                    //Text(text = result.data.toString())
                    Spacer(modifier = Modifier.height(36.dp))
                    WeatherData(result.data)
                }
                null -> {}
            }
        }
    }

}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeatherData(data: WeatherResponse) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ){
            Icon(imageVector = Icons.Default.LocationOn,
                contentDescription = "location pin",
                modifier = Modifier.size(40.dp),
                tint = textNormal
            )
            Text(text = data.location.name, fontSize = 30.sp, color = textNormal )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = data.location.country, fontSize = 20.sp, color = textDim)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${data.current.temp_c} ° C",
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = textNormal
        )
        Spacer(modifier = Modifier.height(16.dp))
        AsyncImage(
            modifier = Modifier.size(150.dp),
            model = "https:${data.current.condition.icon}".replace("64x64", "128x128"),
            contentDescription = "condition icon")
        Text(
            text = data.current.condition.text,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = textDim
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card {
            Column( modifier = Modifier.fillMaxWidth().background(cardBG)) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherkeyValue(key = "${data.current.humidity}", value = "Humidity")
                    WeatherkeyValue(key = "${data.current.wind_kph} km/h", value = "Wind Speed")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherkeyValue(key = "${data.current.uv}", value = "UV")
                    WeatherkeyValue(key = "${data.current.precip_in} in", value = "Precipitation")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherkeyValue(key = data.location.localtime.split(" ")[1], value = "Local Time")
                    WeatherkeyValue(key = data.location.localtime.split(" ")[0], value = "Local Date")
                }
            }
        }
    }
}

@Composable
fun WeatherkeyValue(key: String, value: String) {
    Column (
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = key, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = textNormal)
        Text(text = value, fontWeight = FontWeight.SemiBold, color = textDim)
    }
}