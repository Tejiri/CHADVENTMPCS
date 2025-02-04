package com.xita.chadventmpcs.android.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TransactionsScreenViewModel : ViewModel() {
    var allTabSelected by mutableStateOf(true)
    var shareCapitalTabSelected by mutableStateOf(false)
    var thriftSavingsTabSelected by mutableStateOf(false)
    var specialDepositTabSelected by mutableStateOf(false)
    var commodityTradingTabSelected by mutableStateOf(false)
    var fineTabSelected by mutableStateOf(false)
    var loanTabSelected by mutableStateOf(false)
    var projectFinancingTabSelected by mutableStateOf(false)


    fun allTabClicked() {
         allTabSelected = true
         shareCapitalTabSelected = false
         thriftSavingsTabSelected = false
         specialDepositTabSelected = false
         commodityTradingTabSelected = false
         fineTabSelected = false
         loanTabSelected = false
         projectFinancingTabSelected = false
    }

    fun shareCapitalTabClicked() {
         allTabSelected = false
         shareCapitalTabSelected = true
         thriftSavingsTabSelected = false
         specialDepositTabSelected = false
         commodityTradingTabSelected = false
         fineTabSelected = false
         loanTabSelected = false
         projectFinancingTabSelected = false
    }

    fun thriftSavingsTabClicked() {
         allTabSelected = false
         shareCapitalTabSelected = false
         thriftSavingsTabSelected = true
         specialDepositTabSelected = false
         commodityTradingTabSelected = false
         fineTabSelected = false
         loanTabSelected = false
         projectFinancingTabSelected = false
    }

    fun specialDepositTabClicked() {
         allTabSelected = false
         shareCapitalTabSelected = false
         thriftSavingsTabSelected = false
         specialDepositTabSelected = true
         commodityTradingTabSelected = false
         fineTabSelected = false
         loanTabSelected = false
         projectFinancingTabSelected = false
    }

    fun commodityTradingTabClicked() {
         allTabSelected = false
         shareCapitalTabSelected = false
         thriftSavingsTabSelected = false
         specialDepositTabSelected = false
         commodityTradingTabSelected = true
         fineTabSelected = false
         loanTabSelected = false
         projectFinancingTabSelected = false
    }

    fun fineTabClicked() {
         allTabSelected = false
         shareCapitalTabSelected = false
         thriftSavingsTabSelected = false
         specialDepositTabSelected = false
         commodityTradingTabSelected = false
         fineTabSelected = true
         loanTabSelected = false
         projectFinancingTabSelected = false
    }

    fun loanTabClicked() {
         allTabSelected = false
         shareCapitalTabSelected = false
         thriftSavingsTabSelected = false
         specialDepositTabSelected = false
         commodityTradingTabSelected = false
        fineTabSelected = false
         loanTabSelected = true
         projectFinancingTabSelected = false
    }

    fun projectFinancingTabClicked() {
         allTabSelected = false
         shareCapitalTabSelected = false
         thriftSavingsTabSelected = false
         specialDepositTabSelected = false
         commodityTradingTabSelected = false
         fineTabSelected = false
         loanTabSelected = false
         projectFinancingTabSelected = true
    }

}