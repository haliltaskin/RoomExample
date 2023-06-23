package com.halil.roomcompose.View

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.halil.roomcompose.Modal.Food
import com.halil.roomcompose.ViewModal.FoodAddViewModal
import com.halil.roomcompose.ViewModalFactory.FoodAddFactory
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodAddPage(navController: NavController) {

    val name= remember { mutableStateOf("") }
    val protein= remember { mutableStateOf("") }
    val yag= remember { mutableStateOf("") }

    val context= LocalContext.current as Activity
    val viewModal:FoodAddViewModal= viewModel(factory = FoodAddFactory(context))

    var snackbarHostState= remember { SnackbarHostState() }
    var scope= rememberCoroutineScope()

    Scaffold(
        snackbarHost={
            SnackbarHost(hostState = snackbarHostState)
        },
        
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                    , shape = RoundedCornerShape(40.dp)
                    , label = { Text(text = "Besin İsmi")}
                    ,value = name.value,
                    onValueChange ={
                        name.value=it
                    },)

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,shape = RoundedCornerShape(40.dp)
                    , label = { Text(text = "Besin Protein Değeri")}
                    ,value = protein.value
                    , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    , onValueChange ={
                        protein.value=it
                    })

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,shape = RoundedCornerShape(40.dp)
                    , label = { Text(text = "Besin Yağ Değeri")}
                    ,value = yag.value
                    ,keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    ,onValueChange ={
                        yag.value=it
                    })

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {

                        var result= check(name.value, protein.value,yag.value)

                        if (result){
                            var food=Food(name.value, protein.value.toDouble(),yag.value.toDouble())
                            viewModal.addFood(food)

                            scope.launch {
                                snackbarHostState
                                    .showSnackbar("Besin Eklendi")
                            }
                            name.value=""
                            protein.value=""
                            yag.value=""
                        }
                        else{
                            scope.launch {
                                snackbarHostState
                                    .showSnackbar("Girdiğiniz değeler boş!")
                            }
                        }

                    }) {
                    Text(text = "Ekle")
                }
            }
        }
    ) 
    
}

fun check(name:String,protein:String,yag:String):Boolean{
    if(name.isBlank()){
        return false
    }

    if(protein.isBlank()){
        return false
    }

    if(yag.isBlank()){
        return false
    }


    return true
}