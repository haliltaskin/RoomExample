package com.halil.roomcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.halil.roomcompose.Modal.Pages
import com.halil.roomcompose.View.FoodAddPage
import com.halil.roomcompose.View.FoodListPage
import com.halil.roomcompose.ui.theme.RoomComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {

    val navController= rememberNavController()
    NavHost(navController =navController , startDestination = Pages.FoodListPage.name){
        composable(route = Pages.FoodListPage.name){
            FoodListPage(navController)
        }

        composable(route = Pages.FoodAddPage.name){
            FoodAddPage(navController)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomComposeTheme {
        App()
    }
}