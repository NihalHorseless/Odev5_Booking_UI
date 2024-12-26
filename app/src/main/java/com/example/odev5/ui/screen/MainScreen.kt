package com.example.odev5.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odev5.R
import com.example.odev5.data.Hotel
import com.example.odev5.ui.theme.Cerulean
import com.example.odev5.ui.theme.DoveGrey
import com.example.odev5.ui.theme.LightSqueeze
import com.example.odev5.ui.theme.LighterDark
import com.example.odev5.ui.theme.Odev5Theme
import com.example.odev5.ui.theme.PrimaryBlue
import com.example.odev5.ui.theme.SelectiveYellow
import com.example.odev5.ui.theme.roboto_medium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    val choosenBottomBar = remember { mutableStateOf(-1) }
    val state = rememberScrollState()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight / 7 + 10).dp)
                    .background(PrimaryBlue),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopAppBar()
                TopBarChipsSection()
            }
        }, bottomBar = {
            BottomAppBarSection(choosenBottomBar,screenHeight)
        })
    { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(LightSqueeze)
                .verticalScroll(state),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            SearchFieldSection()
            HotelsSection(modifier = Modifier.padding(bottom = 12.dp).fillMaxWidth().height((screenHeight/3 + 20).dp),screenWidth)
        }
    }
}

@Composable
fun HotelsSection(modifier: Modifier,screenWidth:Int) {
    Column(modifier = modifier ) {
Text(text = "Last-minute Ideas",modifier = Modifier.padding(start = 8.dp), fontSize = 20.sp, fontFamily = roboto_medium)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Adjust spacing as needed
        ) {
            items(listOf(
                Hotel("Max Royale - Belek", "200 km", "250 €", R.drawable.hotel_one),
                Hotel("Gloria Resort - Belek", "190 km", "150 €", R.drawable.hotel_two),
                Hotel("Ali Bey Club", "195 km", "150 €", R.drawable.hotel_third),
                Hotel("Royal Taj Mahal", "140 km", "100 €", R.drawable.hotel_fourth)
            )) { hotel ->
                HotelItem(
                    hotelName = hotel.name,
                    hotelDistance = hotel.distance,
                    hotelPrice = hotel.price,
                    imageRes = hotel.imageRes,
                    modifier = Modifier.width((screenWidth / 2 + 20).dp)
                )
            }
        }
    }

}
@Composable
fun HotelItem(hotelName:String,hotelPrice:String,hotelDistance:String,imageRes:Int,modifier: Modifier) {
Column(modifier = modifier
    .padding(all = 4.dp)
    .fillMaxHeight().background(LightSqueeze),
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.SpaceEvenly) {
Image(contentScale = ContentScale.FillBounds,modifier = Modifier.weight(7f).fillMaxWidth().clip(shape = RoundedCornerShape(12.dp)), alignment = Alignment.TopCenter,painter = painterResource(imageRes), contentDescription = "")
    Text(text = hotelName, fontFamily = roboto_medium,modifier = Modifier.weight(1f).fillMaxWidth(), textAlign = TextAlign.Start)
    Text(text = hotelDistance, color = DoveGrey, modifier = Modifier.weight(1f).fillMaxWidth(), textAlign = TextAlign.Start)
    Text(text = hotelPrice, fontFamily = roboto_medium, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth().weight(1f), textAlign = TextAlign.End)
}
}
@Composable
fun SearchFieldSection() {
    Column(
        modifier = Modifier
            .padding(all = 12.dp)
            .fillMaxWidth()
            .height(240.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TextFieldComponent(
            label = "Kapsula Hotel Warszawa",
            R.drawable.text_field_search,
            modifier = Modifier.weight(1f)
        )
        TextFieldComponent(
            label = "Fri, Dec 20 - Sat, Dec 21",
            R.drawable.text_field_calendar,
            modifier = Modifier.weight(1f)
        )
        TextFieldComponent(
            label = "1 room - 1 adult - 0 children",
            R.drawable.text_field_person,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .border(width = 4.dp, color = SelectiveYellow)
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(size = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Cerulean),
                onClick = {},
                content = { Text(text = "Search") })
        }
    }
}

@Composable
fun TextFieldComponent(label: String, resourcePath: Int, modifier: Modifier) {
    Box(
        modifier = modifier
            .border(width = 2.dp, color = SelectiveYellow) // Adjust thickness here
            .padding(2.dp) // Adds padding between the border and the text field
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(size = 8.dp),
            onValueChange = {},
            value = "",
            label = {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(painter = painterResource(resourcePath), contentDescription = "")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = label)
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedIndicatorColor = SelectiveYellow
            )
        )
    }
}

@Composable
fun BottomAppBarSection(choosenBottomBar: MutableState<Int>,screenHeight:Int) {

    val bottomBarItemColors = NavigationBarItemDefaults.colors(
        selectedIconColor = PrimaryBlue,
        selectedTextColor = PrimaryBlue,
        unselectedIconColor = LighterDark,
        unselectedTextColor = LighterDark,
        indicatorColor = Color.Transparent
    )
    BottomAppBar(modifier = Modifier.fillMaxWidth().background(Color.White),content = {
        Row(modifier = Modifier.fillMaxSize().background(Color.White), horizontalArrangement = Arrangement.SpaceEvenly) {
            NavigationBarItem(selected = choosenBottomBar.value == 0, onClick = {
                choosenBottomBar.value = 0
            }, label = {
                Text(text = "Search")
            }, icon = {
                Icon(
                    painter = painterResource(R.drawable.text_field_search),
                    contentDescription = ""
                )
            }, interactionSource = remember { MutableInteractionSource() }, colors = bottomBarItemColors)
            NavigationBarItem(selected = choosenBottomBar.value == 1, onClick = {
                choosenBottomBar.value = 1
            }, label = {
                Text(text = "Saved")
            }, icon = {
                Icon(
                    painter = painterResource(R.drawable.bottom_app_bar_fav),
                    contentDescription = ""
                )
            }, colors = bottomBarItemColors)
            NavigationBarItem(selected = choosenBottomBar.value == 2, onClick = {
                choosenBottomBar.value = 2
            }, label = {
                Text(text = "Bookings")
            }, icon = {
                Icon(
                    painter = painterResource(R.drawable.bottom_app_bar_bookings),
                    contentDescription = ""
                )
            }, colors = bottomBarItemColors)
            NavigationBarItem(selected = choosenBottomBar.value == 3, onClick = {
                choosenBottomBar.value = 3
            }, label = {
                Text(text = "Profile")
            }, icon = {
                Icon(
                    painter = painterResource(R.drawable.text_field_person),
                    contentDescription = ""
                )
            }, colors = bottomBarItemColors)
        }

    }
    )
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
            .fillMaxHeight()
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(64.dp))
                Image(
                    painter = painterResource(R.drawable.logo_second),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(40.dp),
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
        },
        border = BorderStroke(width = 2.dp, color = if (selectedChip) Color.White else PrimaryBlue),
        colors = FilterChipDefaults.filterChipColors(
            labelColor = Color.White,
            iconColor = Color.White,
            selectedLeadingIconColor = Color.White,
            selectedLabelColor = Color.White,
            selectedContainerColor = PrimaryBlue
        ),
        modifier = Modifier.padding(horizontal = 4.dp,vertical = 4.dp),
        shape = RoundedCornerShape(size = 16.dp)
    )
}