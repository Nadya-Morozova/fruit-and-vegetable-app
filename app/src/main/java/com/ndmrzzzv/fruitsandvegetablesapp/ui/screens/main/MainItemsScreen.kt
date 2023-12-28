package com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main

import androidx.compose.runtime.Composable

data class MainItemsScreenAction(
    val onItemClick: (code: String) -> Unit = {},
    val getAllItemsEvent: () -> Unit = {},
)

@Composable
fun MainItemsScreen(
    actions: MainItemsScreenAction
) {
}