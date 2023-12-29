package com.ndmrzzzv.fruitsandvegetablesapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.ndmrzzzv.fruitsandvegetablesapp.ui.FruitAndVegetablesApp
import com.ndmrzzzv.fruitsandvegetablesapp.ui.theme.FruitsAndVegetablesAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainWrapper()
        }
    }

    @Composable
    fun MainWrapper() {
        FruitsAndVegetablesAppTheme {
            FruitAndVegetablesApp()
        }
    }
}