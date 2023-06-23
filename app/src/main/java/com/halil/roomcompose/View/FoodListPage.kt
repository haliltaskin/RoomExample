package com.halil.roomcompose.View

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.halil.roomcompose.Modal.Food
import com.halil.roomcompose.Modal.Pages
import com.halil.roomcompose.R
import com.halil.roomcompose.ViewModal.FoodListViewModal
import com.halil.roomcompose.ViewModalFactory.FoodListFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoodListPage(navController: NavController) {

    val colorList= listOf<Color>(
        Color(0xFF3AA6B9),
        Color(0xFFFFD0D0),
        Color(0xFFFF9EAA),
        Color(0xFFC1ECE4)
    )

    var context = LocalContext.current as Activity
    var viewModel:FoodListViewModal = viewModel(factory = FoodListFactory(context))
    val foodList=viewModel.foodList.observeAsState()
    var currentIndex= remember { mutableStateOf(-1) }
    var snackbarHostState= remember { SnackbarHostState() }
    val scope= rememberCoroutineScope()

    var showAlertDialog= remember { mutableStateOf(false) }

    LaunchedEffect(key1 =true ){
        viewModel.readAllFood()
    }

    Scaffold(

        snackbarHost = {
                       SnackbarHost(hostState = snackbarHostState)
        }
        ,content = {
            Box(
                modifier = Modifier.fillMaxSize()

            ){

                LazyColumn(){
                    item {
                        foodList.value?.let {
                            it.forEachIndexed { index, food ->
                                var mod=index%4
                                FoodItem(
                                    food = food,
                                    color=colorList[mod],
                                    deleteClick = {
                                    showAlertDialog.value=true
                                        currentIndex.value=index
                                    }
                                )
                            }
                        }


                    }
                }
                if (showAlertDialog.value){
                    AlertDialog(onDismissRequest = {
                        showAlertDialog.value=false
                    },
                    title = {
                        Text(text = "Besin Sil")
                    },
                        text = {
                            Text(text = "Silmek istediğine emin misin?")
                        },
                        confirmButton = {
                            Text(text = "Evet",
                            modifier = Modifier.clickable {
                                if (currentIndex.value!=-1){
                                    var food=foodList.value!![currentIndex.value]
                                    viewModel.deleteFood(food)
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Silindi")
                                    }
                                    showAlertDialog.value=false
                                }

                            })


                        },
                        dismissButton = {
                            Text(text = "Hayır",
                            modifier = Modifier.clickable {
                                showAlertDialog.value=false
                            })
                        }
                    )


                }
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(all = 20.dp),
                    onClick = {
                        navController.navigate(Pages.FoodAddPage.name)
                    }) {
                    Icon(painter = painterResource(id = R.drawable.add_jpg), contentDescription = "Ekleme Sayfasına Git")
                }
            }
        }
    )
}

@Composable
fun FoodItem(food: Food,color: Color ,deleteClick:()-> Unit){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp)
            ){
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = color
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            ){
                Column(modifier = Modifier
                    .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                    )
                {
                    Text(text = food.name)
                    Row() {
                        Text(modifier = Modifier.padding(start = 10.dp, end = 10.dp),text = "Protein ${food.protein}")
                        Text(text = "Yağ ${food.yag}")
                    }

                }



                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable {
                            deleteClick()
                        }
                    ,painter = painterResource(id = R.drawable.delete_jpg),
                    contentDescription ="Delete" )
            }
        }
    }
}