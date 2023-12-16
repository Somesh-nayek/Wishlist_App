package com.example.my_wishlist_app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.my_wishlist_app.data.Wish
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id:Long,
    viewModel: WishViewModel,
    navController:NavController
){

    val snackMessage= remember{
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    val ScaffoldState = rememberScaffoldState()
    if (id != 0L){
        val wish =viewModel.getAllwishById(id).collectAsState(initial = Wish(id= 0L, title = "", description = ""))
        viewModel.wishtitleState=wish.value.title
        viewModel.wishDescriptionState=wish.value.description
    }else{
        viewModel.wishtitleState=""
        viewModel.wishDescriptionState=""
    }
    Scaffold(

        topBar = { AppBarView(title =
        if(id!=0L) stringResource(id = R.string.Update_wish)
        else stringResource(id = R.string.Add_wish))

        {navController.navigateUp()}
        },

        scaffoldState=ScaffoldState


    ) {
            Column (modifier= Modifier
                .padding(it)
                .wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                ){
                Spacer(modifier = Modifier.height(10.dp))

                WishTextFeild(label = "Title",
                    value = viewModel.wishtitleState,
                    onValueChange = {
                        viewModel.onWishtitleChanges(it)
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                WishTextFeild(label = "Description",
                    value = viewModel.wishDescriptionState,
                    onValueChange = {
                        viewModel.onWishDescriptionChanges(it)
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick =
                    {
                        if(viewModel.wishtitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
                           if(id!=0L){
                                //TODO Update wish
                               viewModel.UpdateWish(
                                   Wish(
                                       title = viewModel.wishtitleState.trim(),
                                       description = viewModel.wishDescriptionState.trim()
                                   )
                               )
                               snackMessage.value="Wish has been Updated"

                           }else{
                               //TODO add wish
                               viewModel.addWish(
                                   Wish(
                                       title = viewModel.wishtitleState.trim(),
                                       description = viewModel.wishDescriptionState.trim()
                                   )
                               )
                               snackMessage.value="Wish has been created"
                           }

                        }else{
                            snackMessage.value="Enter field to create a wish"
                        }
                        scope.launch {
                            ScaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                            navController.navigateUp()
                        }
                    }
                ){
                    Text(
                        text = if(id != 0L) stringResource(id = R.string.Update_wish) else stringResource(
                            id = R.string.Add_wish
                        ),
                        style = TextStyle(
                            fontSize = 18.sp
                        )
                    )
                }

            }
    }
}

@Composable
fun WishTextFeild(
    label:String,
    value: String,
    onValueChange:(String)->Unit
){
    OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label= {Text(text = label, color = Color.Black)},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = colorResource(id = R.color.black),
                unfocusedBorderColor = colorResource(id = R.color.black),
                cursorColor = colorResource(id = R.color.black),
                focusedLabelColor = colorResource(id = R.color.black),
                unfocusedLabelColor = colorResource(id = R.color.black),

            )

        )
}
@Preview
@Composable
fun WishTextFeildPreview(){
    WishTextFeild(label = "text", value = "text", onValueChange = {})
}