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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ndmrzzzv.domain.model.Product
import com.ndmrzzzv.fruitsandvegetablesapp.R

data class ProductsListScreenAction(
    val onItemClick: (product: Product) -> Unit = {},
    val getAllItemsEvent: () -> Unit = {},
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductsListScreen(
    state: ProductsListState,
    actions: ProductsListScreenAction
) {
    var topBarTitleState by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.padding(top = 0.dp),
        topBar = { ProductTopAppBar(topBarTitleState, actions.getAllItemsEvent) }
    ) { padding ->

        Column(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            when (state) {
                is ProductsListState.LoadedData -> {
                    val topBarTitle = state.data.first ?: ""
                    val products = state.data.second ?: listOf()

                    topBarTitleState = topBarTitle
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(products) { item ->
                            ProductItem(item) {
                                actions.onItemClick(it)
                            }
                        }
                    }
                }

                is ProductsListState.Loading -> {
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

                is ProductsListState.LoadingFailed -> {
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
fun ProductItem(
    product: Product,
    onItemClick: (product: Product) -> Unit = {}
) {
    Card(
        onClick = { onItemClick(product) },
        colors = CardDefaults.cardColors(
            containerColor = Color(product.color ?: 0)
        ),
        modifier = Modifier
            .padding(16.dp)
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
                text = product.name ?: "",
                color = Color.White,
            )
            AsyncImage(
                modifier = Modifier
                    .size(80.dp),
                error = painterResource(id = R.drawable.no_photos),
                model = product.image,
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


