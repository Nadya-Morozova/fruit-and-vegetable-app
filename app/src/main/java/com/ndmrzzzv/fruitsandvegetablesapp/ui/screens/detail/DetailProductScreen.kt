package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ndmrzzzv.domain.model.DetailProduct
import com.ndmrzzzv.fruitsandvegetablesapp.R

data class DetailProductScreenAction(
    val loadItemAgainEvent: () -> Unit = {},
    val backToMainScreen: () -> Unit = {}
)

@Composable
fun DetailProductScreen(
    action: DetailProductScreenAction,
    state: DetailProductState
) {
    var titleOfTopBar by remember { mutableStateOf("") }

    Scaffold(
        topBar = { ProductTopAppBar(title = titleOfTopBar, action.backToMainScreen) }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            when (state) {
                is DetailProductState.LoadedData -> {
                    val product = state.product
                    titleOfTopBar = product?.name ?: ""
                    ProductBlock(item = product)
                }

                is DetailProductState.LoadingFailed -> {
                    RetryBlock(message = state.message) {
                        action.loadItemAgainEvent
                    }
                }

                is DetailProductState.Loading -> {
                    LoadingBlock()
                }
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTopAppBar(title: String, backToMainScreen: () -> Unit = {}) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
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
        navigationIcon = {
            IconButton(onClick = { backToMainScreen() }) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.refresh_the_list),
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun ProductBlock(item: DetailProduct?) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(item?.color ?: 0)
        ),
        modifier = Modifier
            .padding(16.dp)
            .height(250.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .padding(top = 8.dp),
                error = painterResource(id = R.drawable.no_photos),
                model = "${item?.image}",
                contentDescription = stringResource(id = R.string.image_of_product)
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                fontSize = 18.sp,
                text = item?.text ?: "",
                color = Color.White,
            )
        }
    }
}

@Composable
fun LoadingBlock() {
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

@Composable
fun RetryBlock(message: String, loadItemAgainEvent: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = message,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Button(
            onClick = { loadItemAgainEvent() },
            colors = ButtonDefaults.buttonColors(
                Color(
                    red = 205,
                    green = 21,
                    blue = 133
                )
            ),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_retry))
        }
    }
}

@Preview
@Composable
fun Preview() {

}