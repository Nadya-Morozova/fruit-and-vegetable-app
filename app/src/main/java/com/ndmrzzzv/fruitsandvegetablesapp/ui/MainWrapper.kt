package com.ndmrzzzv.fruitsandvegetablesapp.ui

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
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail.DetailProductScreen
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail.DetailProductViewModel
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.ProductsListScreen
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.ProductsListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductsApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "items"
    ) {
        composable(route = "items") {
            val viewModel = koinViewModel<ProductsListViewModel>()
            val actions = ActionsBuilder.getActions(navController, viewModel)
            ProductsListScreen(state = viewModel.products.collectAsState().value, actions = actions)
        }

        composable(
            route = "items/{item}",
            arguments = listOf(navArgument("item") {
                type = NavType.StringType
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
            val viewModel = koinViewModel<DetailProductViewModel>()
            val actions = ActionsBuilder.getActions(navController, viewModel)
            DetailProductScreen(state = viewModel.product.collectAsState().value, action = actions)
        }
    }
}