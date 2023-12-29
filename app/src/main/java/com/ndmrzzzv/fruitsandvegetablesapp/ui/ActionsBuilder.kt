package com.ndmrzzzv.fruitsandvegetablesapp.ui

import androidx.navigation.NavController
import com.google.gson.Gson
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail.DetailProductScreenAction
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail.DetailProductViewModel
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.ProductsListScreenAction
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.ProductsListViewModel
import java.net.URLEncoder

class ActionsBuilder {

    companion object {

        fun getActions(
            navController: NavController,
            viewModel: ProductsListViewModel
        ): ProductsListScreenAction {
            return ProductsListScreenAction(
                onItemClick = { item ->
                    val gson = Gson()
                    val jsonItem = gson.toJson(item)
                    val product = URLEncoder.encode(jsonItem, "utf-8")
                    navController.navigate("items/$product")
                },
                getAllItemsEvent = { viewModel.getAllItems() }
            )
        }

        fun getActions(
            navController: NavController,
            viewModel: DetailProductViewModel
        ): DetailProductScreenAction {
            return DetailProductScreenAction(
                loadItemAgainEvent = { viewModel.getDetailOfProduct() },
                backToMainScreen = { navController.navigateUp() }
            )
        }

    }

}