package com.xita.chadventmpcs.android.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xita.chadventmpcs.android.R
import com.xita.chadventmpcs.android.models.entities.TransactionEntity
import com.xita.chadventmpcs.android.viewModels.ChadventDatabaseViewModel
import com.xita.chadventmpcs.android.viewModels.TransactionsScreenViewModel

@Composable
fun TransactionsScreen(
    innerPadding: PaddingValues,
    chadventDatabaseViewModel: ChadventDatabaseViewModel,
    transactionsScreenViewModel: TransactionsScreenViewModel = viewModel()
) {
    val transactions by chadventDatabaseViewModel.allTransactions.collectAsState(initial = emptyList())
    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(10.dp)
    ) {
        Text(
            "Transactions List",
            modifier = Modifier.padding(bottom = 15.dp),
            style = TextStyle(fontSize = 25.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        )

        LazyRow(Modifier.padding(start = 10.dp, end = 10.dp)) {
            item {
                TransactionsTab(
                    selected = transactionsScreenViewModel.allTabSelected,
                    text = "All",
                    onClick = { transactionsScreenViewModel.allTabClicked() })


                TransactionsTab(
                    selected = transactionsScreenViewModel.shareCapitalTabSelected,
                    text = "Share Capital",
                    onClick = { transactionsScreenViewModel.shareCapitalTabClicked() })

                TransactionsTab(
                    selected = transactionsScreenViewModel.thriftSavingsTabSelected,
                    text = "Thrift Savings",
                    onClick = { transactionsScreenViewModel.thriftSavingsTabClicked() })

                TransactionsTab(
                    selected = transactionsScreenViewModel.specialDepositTabSelected,
                    text = "Special Deposit",
                    onClick = { transactionsScreenViewModel.specialDepositTabClicked() })

                TransactionsTab(
                    selected = transactionsScreenViewModel.commodityTradingTabSelected,
                    text = "Commodity Trading",
                    onClick = { transactionsScreenViewModel.commodityTradingTabClicked() })

                TransactionsTab(
                    selected = transactionsScreenViewModel.fineTabSelected,
                    text = "Fine",
                    onClick = { transactionsScreenViewModel.fineTabClicked() })

                TransactionsTab(
                    selected = transactionsScreenViewModel.loanTabSelected,
                    text = "Loan",
                    onClick = { transactionsScreenViewModel.loanTabClicked() })

                TransactionsTab(
                    selected = transactionsScreenViewModel.projectFinancingTabSelected,
                    text = "Project Financing",
                    onClick = { transactionsScreenViewModel.projectFinancingTabClicked() })

            }
        }

        LazyColumn(Modifier.padding(top = 30.dp)) {

            items(transactions){transaction->

                if (transactionsScreenViewModel.allTabSelected){
                    SingleTransaction(transaction = transaction)
                } else if (transactionsScreenViewModel.shareCapitalTabSelected && transaction.account == "sharecapital"){
                    SingleTransaction(transaction = transaction)
                }else if (transactionsScreenViewModel.thriftSavingsTabSelected && transaction.account == "thriftsavings"){
                    SingleTransaction(transaction = transaction)
                }else if (transactionsScreenViewModel.specialDepositTabSelected && transaction.account == "specialdeposit"){
                    SingleTransaction(transaction = transaction)
                }else if (transactionsScreenViewModel.commodityTradingTabSelected && transaction.account == "commodityTrading"){
                    SingleTransaction(transaction = transaction)
                }else if (transactionsScreenViewModel.loanTabSelected && transaction.account == "loan"){
                    SingleTransaction(transaction = transaction)
                }else if (transactionsScreenViewModel.projectFinancingTabSelected && transaction.account == "projectfinancing"){
                    SingleTransaction(transaction = transaction)
                }else if (transactionsScreenViewModel.fineTabSelected && transaction.account == "fine"){
                    SingleTransaction(transaction = transaction)
                }

            }
//            item {
//                SingleTransaction(credit = true)
//
//                SingleTransaction(credit = true)
//
//                SingleTransaction(credit = true)
//
//                SingleTransaction(credit = false)
//
//                SingleTransaction(credit = false)
//                SingleTransaction(credit = true)
//                SingleTransaction(credit = false)
//                SingleTransaction(credit = true)
//                SingleTransaction(credit = true)
//                SingleTransaction(credit = false)
//
//                SingleTransaction(credit = true)
//
//                SingleTransaction(credit = true)
//                SingleTransaction(credit = true)
//                SingleTransaction(credit = true)
//                SingleTransaction(credit = true)
//                SingleTransaction(credit = true)
//            }
        }
    }


}

@Composable
fun SingleTransaction(transaction: TransactionEntity) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .width(30.dp)
                    .height(30.dp),
                painter = painterResource(if (transaction.transactiontype == "credit") R.drawable.increase else R.drawable.decrease),
                contentDescription = ""
            )

            Column(Modifier.weight(1f)) {
                Text("${transaction.date}")
                Text(
                    "${transaction.narration}", style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 20.sp
                    )
                )
            }

            Text(
                "₦${transaction.amount}", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = if (transaction.transactiontype == "credit") colorResource(R.color.transaction_green) else colorResource(
                        R.color.transaction_red
                    )
                )
            )
        }
        HorizontalDivider(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
    }

}

@Composable
fun TransactionsTab(selected: Boolean, text: String, onClick: () -> Unit) {
    Box(
        Modifier

            .padding(end = 10.dp)
            .clickable { onClick() }
            .border(2.dp, Color.Black, RoundedCornerShape(30))
            .background(
                if (selected) Color.Blue.copy(0.5f) else Color.Transparent, shape = RoundedCornerShape(30)
            )
            .padding(10.dp)

    ) {
        Text(
            text,
            style = if (selected) TextStyle(fontSize = 22.sp, color = Color.White, fontWeight = FontWeight.Bold) else TextStyle(
                fontSize = 22.sp, color = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTransactionsScreen() {
//    TransactionsScreen(PaddingValues(0.dp),)
}