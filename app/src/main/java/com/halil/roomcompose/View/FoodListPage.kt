package com.halil.roomcompose.View

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.halil.roomcompose.Modal.Food
import com.halil.roomcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoodListPage() {

    Scaffold(
        content = {
            Box(
                modifier = Modifier.fillMaxSize()

            ){

                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(all = 20.dp),
                    onClick = {

                    }) {
                    Icon(painter = painterResource(id = R.drawable.add_jpg), contentDescription = "Ekleme Sayfasına Git")
                }
            }
        }
    )
}

@Composable
fun FoodItem(food: Food ,deleteClick:()-> Unit){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp)
            ){
        Card(
            modifier = Modifier
            .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                    ,text = food.name)

                Icon(
                    modifier = Modifier.clickable {
                       deleteClick()
                    }
                    ,painter = painterResource(id = R.drawable.delete_jpg),
                    contentDescription ="Delete" )
            }
        }
    }
}