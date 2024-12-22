package com.example.odev5.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.odev5.R
import com.example.odev5.ui.theme.Odev5Theme
import com.example.odev5.ui.theme.PrimaryBlue
import com.example.odev5.ui.theme.SelectiveYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight / 6).dp).background(PrimaryBlue),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopAppBar()
                TopBarChipsSection()
            }
        })
    { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) { }
    }
}

@Composable
@Preview
fun MainScreenPrev() {
    Odev5Theme {
        MainScreen()
    }
}

@Composable
fun TopBarChipsSection() {
    val state = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(state),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TopBarChipItem(title = "Stays", R.drawable.app_bar_bed)
        TopBarChipItem(title = "Car Rental", R.drawable.app_bar_car_rental)
        TopBarChipItem(title = "Taxi", R.drawable.app_bar_taxi)
        TopBarChipItem(title = "Attractions", R.drawable.app_bar_attractions)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryBlue),
        modifier = Modifier.fillMaxWidth(),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(R.drawable.logo_second),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(48.dp),
                    alignment = Alignment.TopEnd
                )
                Row(modifier = Modifier.padding(start = 16.dp))
                {
                    IconButton(onClick = {}, content = {
                        Image(
                            painter = painterResource(R.drawable.app_bar_chat),
                            contentDescription = ""
                        )
                    })
                    IconButton(onClick = {}, content = {
                        Image(
                            painter = painterResource(R.drawable.app_bar_notification),
                            contentDescription = ""
                        )
                    })
                }

            }

        })
}

@Composable
fun TopBarChipItem(title: String, resourcePath: Int) {
    var selectedChip by remember { mutableStateOf(false) }

    FilterChip(
        onClick = { selectedChip = !selectedChip },
        label = { Text(text = title) },
        selected = selectedChip,
        leadingIcon = {
            Icon(
                    painter = painterResource(resourcePath),
                    contentDescription = "Localized description"
                )
            }
         ,
        border = BorderStroke(width = 2.dp, color = if (selectedChip) Color.White else PrimaryBlue), colors = FilterChipDefaults.filterChipColors(labelColor = Color.White, iconColor = Color.White, selectedLeadingIconColor = SelectiveYellow, selectedLabelColor = SelectiveYellow, selectedContainerColor = PrimaryBlue),
        modifier = Modifier.padding(horizontal = 4.dp)
    )
}