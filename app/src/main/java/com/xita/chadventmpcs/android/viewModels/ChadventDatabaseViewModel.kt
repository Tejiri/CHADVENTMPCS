package com.xita.chadventmpcs.android.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xita.chadventmpcs.android.dataSource.local.daos.AccountDao
import com.xita.chadventmpcs.android.dataSource.local.daos.MemberDao
import com.xita.chadventmpcs.android.dataSource.local.daos.TransactionDao
import com.xita.chadventmpcs.android.models.Account
import com.xita.chadventmpcs.android.models.Member
import com.xita.chadventmpcs.android.models.Transaction
import com.xita.chadventmpcs.android.models.entities.AccountEntity
import com.xita.chadventmpcs.android.models.entities.MemberEntity
import com.xita.chadventmpcs.android.models.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ChadventDatabaseViewModel(
    private val memberDao: MemberDao,
    private val transactionDao: TransactionDao,
    private val accountDao: AccountDao
) : ViewModel() {

    val allMembers: Flow<List<MemberEntity>> = memberDao.getAllMembers()
    val allTransactions: Flow<List<TransactionEntity>> = transactionDao.getTransactions()
    val allAccounts: Flow<List<AccountEntity>> = accountDao.getAllAccounts()

    //    var loggedInUser: User? = null
    var loggedInMember by mutableStateOf<Member?>(null)
    var totalContribution by mutableStateOf(0.00)

    fun updateLoggedInUser(member: Member?) {
        loggedInMember = member
    }

//    fun getAccount(username:String):Flow<AccountEntity>{
//        return accountDao.getAccountByUsername(username)
//    }

    fun clearTransactions() {
        viewModelScope.launch {
            transactionDao.clearTransactions()
        }
    }

    fun clearMembers() {
        viewModelScope.launch {
            memberDao.clearMembers()
        }

    }

    fun addAccount(account: Account) {
        viewModelScope.launch {
            accountDao.insert(
                AccountEntity(
                    _id = account._id,
                    username = account.username,
                    fine = account.fine,
                    commoditytrading = account.commoditytrading,
                    sharecapital = account.sharecapital,
                    thriftsavings = account.thriftsavings,
                    projectfinancing = account.projectfinancing,
                    specialdeposit = account.specialdeposit,
                    loan = account.loan
                )
            )
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {

            transactionDao.insert(
                TransactionEntity(
                    _id = transaction._id,
                    transactiontype = transaction.transactiontype,
                    account = transaction.account,
                    amount = transaction.amount,
                    narration = transaction.narration,
                    date = transaction.date
                )
            )
        }
    }

    fun addMember(member: Member) {
        viewModelScope.launch {
            memberDao.insert(
                MemberEntity(
                    _id = member._id,
                    username = member.username,
                    title = member.title,
                    firstname = member.firstname,
                    lastname = member.lastname,
                    middlename = member.middlename,
                    position = member.position,
                    membershipstatus = member.membershipstatus,
                    loanapplicationstatus = member.loanapplicationstatus,
                    phonenumber = member.phonenumber,
                    email = member.email,
                    address = member.address,
                    gender = member.gender,
                    occupation = member.occupation,
                    nextofkin = member.nextofkin,
                    nextofkinaddress = member.nextofkinaddress,
                    bank = member.bank,
                    accountnumber = member.accountnumber,
                    __v = member.__v
                )
            )
        }
    }
}