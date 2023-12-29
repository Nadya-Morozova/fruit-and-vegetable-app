@file:OptIn(ExperimentalMaterial3Api::class)

package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ndmrzzzv.domain.model.Item
import com.ndmrzzzv.fruitsandvegetablesapp.R
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.state.ItemsState

data class MainItemsScreenAction(
    val onItemClick: (code: String) -> Unit = {},
    val getAllItemsEvent: () -> Unit = {},
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainItemsScreen(
    state: ItemsState,
    actions: MainItemsScreenAction
) {

    Scaffold(
        modifier = Modifier.padding(top = 0.dp),
        topBar = { ProductTopAppBar("app", actions.getAllItemsEvent) }
    ) { padding ->

        Column(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            when (state) {
                is ItemsState.LoadedData -> {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val listOfProducts = state.titleAndItems.second ?: listOf()
                        items(listOfProducts) { item ->
                            ProductItem(item)
                        }
                    }
                }

                is ItemsState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color(red = 205, green = 21, blue = 133)
                        )
                    }
                }

                is ItemsState.LoadingFailed -> {
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        text = state.message,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
            }
        }
    }

}

@Composable
fun ProductItem(item: Item) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(item.color ?: 0)
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(120.dp)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                fontSize = 20.sp,
                text = item.name ?: "",
                color = Color.White,
            )
            AsyncImage(
                modifier = Modifier
                    .size(80.dp),
                error = painterResource(id = R.drawable.no_photos),
                model = "https://test-task-server.mediolanum.f17y.com${item.image}",
                contentDescription = stringResource(id = R.string.image_of_product)
            )
        }
    }

}

@Composable
fun ProductTopAppBar(title: String, getAllItemsEvent: () -> Unit = {}) {
    TopAppBar(
        colors = topAppBarColors(
            containerColor = Color(red = 205, green = 21, blue = 133)
        ),
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                text = title,
                color = Color.White
            )
        },
        actions = {
            IconButton(onClick = { getAllItemsEvent() }) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = stringResource(id = R.string.refresh_the_list),
                    tint = Color.White
                )
            }
        }
    )
}


//@Preview
//@Composable
//fun Preview() {
//    ProductItem(
//        item = Item(
//            "orange",
//            "Orange",
//            "https://test-task-server.mediolanum.f17y.com/images/orange.png",
//            ""
//        )
//    )
//}