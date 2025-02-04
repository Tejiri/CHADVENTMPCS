package com.xita.chadventmpcs.android.pages

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xita.chadventmpcs.android.viewModels.MembersPostsViewModel

@Composable
fun MembersPostsScreen(innerPadding:PaddingValues,navController: NavController,membersPostsViewModel: MembersPostsViewModel = viewModel()){

}

@Preview(showBackground = true)
@Composable
fun MembersPostsScreenPreview(){
    MembersPostsScreen(PaddingValues(0.dp), rememberNavController())
}