package com.xita.chadventmpcs.android.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.xita.chadventmpcs.android.R
import com.xita.chadventmpcs.android.constants.Constants
import com.xita.chadventmpcs.android.customComponents.CustomFormComposables
import com.xita.chadventmpcs.android.customComponents.UIComponents
import com.xita.chadventmpcs.android.ui.theme.BlueBackground
import com.xita.chadventmpcs.android.viewModels.ChadventDatabaseViewModel
import com.xita.chadventmpcs.android.viewModels.LoginPageViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoginPage(
    navController: NavController,
    loginPageViewModel: LoginPageViewModel = viewModel(),
    chadventDatabaseViewModel: ChadventDatabaseViewModel
) {
    val members by chadventDatabaseViewModel.allMembers.collectAsState(initial = emptyList())

    Scaffold { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(BlueBackground)
            ) {


            }

            Column(
                Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
            ) {
                Box(
                    Modifier
                        .weight(0.3f)
                        .fillMaxWidth()
                ) {

                    GlideImage(
                        R.drawable.chadvent_logo,
                        contentDescription = "",
                        modifier = Modifier
                            .align(
                                Alignment.Center
                            )
                            .fillMaxSize(0.5f)
                    )

                }

                Box(
                    Modifier
                        .weight(0.7f)
                        .clip(RoundedCornerShape(topEnd = 50.dp, topStart = 50.dp))
                        .background(Color.White)

                        .fillMaxWidth()
                ) {
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxSize()
                            .padding(15.dp)
                            .imePadding(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        item {
                            Text(
                                "Login to account",
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 35.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        items(1) {

                            CustomFormComposables().CustomTextField(
                                value = loginPageViewModel.username, onValueChange = { username ->
                                    loginPageViewModel.usernameChange(
                                        username
                                    )
                                },
                                placeholder = "Username",
                                obscureText = false
                            )

                            CustomFormComposables().CustomTextField(
                                value = loginPageViewModel.password, onValueChange = { password ->
                                    loginPageViewModel.passwordChange(
                                        password
                                    )
                                },
                                placeholder = "Password",
                                obscureText = true
                            )

                            CustomFormComposables().CustomButton(
                                onclick = {

                                    loginPageViewModel.logUserIn(
                                        onSuccess = fun() {
                                            chadventDatabaseViewModel.clearTransactions()
                                            chadventDatabaseViewModel.clearMembers()
                                            chadventDatabaseViewModel.totalContribution = 0.00

                                            loginPageViewModel.membersResponse.forEach { member ->
                                                if (member.username == loginPageViewModel.user?.username) {
                                                    chadventDatabaseViewModel.updateLoggedInUser(
                                                        member
                                                    )
                                                } else {
                                                    chadventDatabaseViewModel.addMember(
                                                        member
                                                    )
                                                }
                                            }

                                            loginPageViewModel.accountsResponse.forEach({ account ->
                                                if (account.username == loginPageViewModel.user?.username) {
                                                    chadventDatabaseViewModel.addAccount(account)

                                                    Log.i("MYINFO - ID SAME", "ID Matches")
                                                    account.transactions.forEach({ transaction ->

                                                        if (transaction.account == Constants.Accounts.SHARE_CAPITAL
                                                            || transaction.account == Constants.Accounts.THRIFT_SAVINGS
                                                            || transaction.account == Constants.Accounts.SPECIAL_DEPOSIT
                                                        ) {
                                                            chadventDatabaseViewModel.totalContribution += transaction.amount.toDouble()
                                                        }
                                                        chadventDatabaseViewModel.addTransaction(
                                                            transaction
                                                        )
                                                    })

                                                }
                                            })

                                            loginPageViewModel.isLoading = false

                                            navController.navigate("mainScreen"){
//                                                popUpTo(navController.graph.startDestinationId) { inclusive = true }

                                            }
                                        })

                                },
                                isLoading = loginPageViewModel.isLoading,
                                buttonText = "Login",

                                )


                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(checked = false, onCheckedChange = {})
                                Text("Remember me", style = TextStyle(fontSize = 17.sp))
                            }

                            if (loginPageViewModel.isError) {
                                UIComponents().BottomAlertDialog(
                                    title = "Error",
                                    message = loginPageViewModel.errorMessage,
                                    isError = true
                                ) {
                                    loginPageViewModel.isError = false
                                    loginPageViewModel.errorMessage = ""
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginPage(rememberNavController(), chadventDatabaseViewModel = viewModel())
}