package com.ndmrzzzv.fruitsandvegetablesapp.activity

import androidx.navigation.NavController
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail.DetailsOfItemScreenAction
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.detail.DetailsOfItemViewModel
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.MainItemsScreenAction
import com.ndmrzzzv.fruitsandvegetablesapp.ui.screens.main.MainItemsViewModel

class ActionsBuilder {

    companion object {

        fun getActions(
            navController: NavController,
            viewModel: MainItemsViewModel
        ): MainItemsScreenAction {
            return MainItemsScreenAction(
                onItemClick = {},
                getAllItemsEvent = {
                    viewModel.getAllItems()
                }
            )
        }

        fun getActions(
            viewModel: DetailsOfItemViewModel
        ): DetailsOfItemScreenAction {
            return DetailsOfItemScreenAction(
                loadItemAgainEvent = {}
            )
        }

    }

}