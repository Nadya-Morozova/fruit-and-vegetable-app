package com.ndmrzzzv.fruitsandvegetablesapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ndmrzzzv.fruitsandvegetablesapp.activity.ActionsBuilder
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail.DetailsOfItemScreen
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail.DetailsOfItemViewModel
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.MainItemsScreen
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.MainItemsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FruitAndVegetablesApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "items") {
        composable(route = "items") {
            val viewModel = koinViewModel<MainItemsViewModel>()
            val actions = ActionsBuilder.getActions(navController, viewModel)
            MainItemsScreen(state = viewModel.products.collectAsState().value, actions = actions)
        }

        composable(
            route = "items/{item_id}",
            arguments = listOf(navArgument("country_code") {
                type = NavType.StringType
            })
        ) {
            val viewModel = koinViewModel<DetailsOfItemViewModel>()
            val actions = ActionsBuilder.getActions(viewModel)
            DetailsOfItemScreen(action = actions)
        }
    }
}