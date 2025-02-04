package com.xita.chadventmpcs.android.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xita.chadventmpcs.android.R
import com.xita.chadventmpcs.android.helpers.HelperFunctions
import com.xita.chadventmpcs.android.viewModels.ChadventDatabaseViewModel
import com.xita.chadventmpcs.android.viewModels.HomeScreenViewModel

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    chadventDatabaseViewModel: ChadventDatabaseViewModel,
    homeScreenViewModel: HomeScreenViewModel = viewModel()
) {
    val accounts by chadventDatabaseViewModel.allAccounts.collectAsState(initial = emptyList())

    Box(
        Modifier
            .padding(innerPadding)
            .background(Color.Blue.copy(0.2f))
            .fillMaxSize()
    ) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .weight(0.2f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Image(
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .height(70.dp)
                            .width(70.dp),
                        painter = painterResource(R.drawable.man),
                        contentDescription = ""
                    )

                    Column(Modifier.weight(1f)) {
                        Text(
                            "${chadventDatabaseViewModel.loggedInMember?.firstname + " " + chadventDatabaseViewModel.loggedInMember?.lastname}",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        )
                        Text("Total Contributions", modifier = Modifier.padding(top = 5.dp))
                        Text(
                            "₦${HelperFunctions().formatWithCommas(chadventDatabaseViewModel.totalContribution.toString())}",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp)
                        )
                    }

                    Image(
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp),
                        painter = painterResource(R.drawable.notification),
                        contentDescription = ""
                    )
                }
            }

            Box(
                Modifier
                    .weight(0.8f)
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(Color.White)
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                LazyColumn {
                    items(accounts) { account ->
                        AccountSummary(
                            text = "Share Capital",
                            HelperFunctions().formatWithCommas(account.sharecapital)
                        )
                        AccountSummary(
                            text = "Fine",
                            HelperFunctions().formatWithCommas(account.fine)
                        )
                        AccountSummary(
                            text = "Loan",
                            HelperFunctions().formatWithCommas(account.loan)
                        )
                        AccountSummary(
                            text = "Thrift Savings",
                            HelperFunctions().formatWithCommas(account.thriftsavings)
                        )
                        AccountSummary(
                            text = "Commodity Trading",
                            HelperFunctions().formatWithCommas(account.commoditytrading)
                        )
                        AccountSummary(
                            text = "Project Financing",
                            HelperFunctions().formatWithCommas(account.projectfinancing)
                        )
                        AccountSummary(
                            text = "Special Deposit Capital",
                            HelperFunctions().formatWithCommas(account.specialdeposit)
                        )
                    }
//                    items(accounts) { account->
//
//                        AccountSummary()
//
//                    }
                }
            }
        }
    }
}

@Composable
fun AccountSummary(text: String, total: String) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Account type")
                Text(
                    "$text",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
            }

            Text("₦${total}", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))
        }

        HorizontalDivider(Modifier.padding(top = 15.dp, bottom = 15.dp), thickness = 2.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
//    HomeScreen(PaddingValues(0.dp))
}