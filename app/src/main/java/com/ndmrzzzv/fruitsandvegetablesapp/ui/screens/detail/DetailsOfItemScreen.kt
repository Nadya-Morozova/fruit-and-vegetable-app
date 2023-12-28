package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail

import androidx.compose.runtime.Composable

data class DetailsOfItemScreenAction(
    val loadItemAgainEvent: () -> Unit = {}
)

@Composable
fun DetailsOfItemScreen(
    action: DetailsOfItemScreenAction
) {
}