package com.ndmrzzzv.fruitsandvegetablesapp.ui

import android.content.ClipData.Item
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
    NavHost(
        navController = navController,
        startDestination = "items"
    ) {
        composable(route = "items") {
            val viewModel = koinViewModel<MainItemsViewModel>()
            val actions = ActionsBuilder.getActions(navController, viewModel)
            MainItemsScreen(state = viewModel.products.collectAsState().value, actions = actions)
        }

        composable(
            route = "items/{item}",
            arguments = listOf(navArgument("item") {
                type = NavType.ParcelableType(Item::class.java)
            }),
            enterTransition = {
                fadeIn(animationSpec = tween(300, easing = LinearEasing)) +
                        slideIntoContainer(
                            animationSpec = tween(300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start
                        )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300, easing = LinearEasing)) +
                        slideOutOfContainer(
                            animationSpec = tween(300, easing = EaseOut),
                            towards = AnimatedContentTransitionScope.SlideDirection.End
                        )
            }

        ) {
            val viewModel = koinViewModel<DetailsOfItemViewModel>()
            val actions = ActionsBuilder.getActions(navController, viewModel)
            DetailsOfItemScreen(state = viewModel.product.collectAsState().value, action = actions)
        }
    }
}