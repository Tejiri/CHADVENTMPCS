package com.xita.chadventmpcs.android.pages

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xita.chadventmpcs.android.R
import com.xita.chadventmpcs.android.viewModels.ChadventDatabaseViewModel
import com.xita.chadventmpcs.android.viewModels.ContactsScreenViewModel

@Composable
fun ContactsScreen(
    innerPadding: PaddingValues,
    chadventDatabaseViewModel: ChadventDatabaseViewModel,
    contactsScreenViewModel: ContactsScreenViewModel = viewModel()
) {
    val members by chadventDatabaseViewModel.allMembers.collectAsState(initial = emptyList())
    val context = LocalContext.current


    // Launcher to request permissions
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        contactsScreenViewModel.contactPermissionGranted = isGranted
    }

    LaunchedEffect(Unit) {
        // Check initial permission state
        contactsScreenViewModel.contactPermissionGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED
    }


    Column(Modifier.padding(innerPadding)) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(30))
                .padding(start = 10.dp, end = 10.dp)

        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
//            Icon(Icons.Default.sear, contentDescription = "")

                Image(
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp),
                    painter = painterResource(R.drawable.search),
                    contentDescription = "",
                )
                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent),
                    onValueChange = { value -> contactsScreenViewModel.onSearchFieldChanged(value) },
                    textStyle = TextStyle(color = Color.Black, fontSize = 20.sp),
                    value = contactsScreenViewModel.searchText,
                    placeholder = {
                        Text(
                            "Search", style = TextStyle(fontSize = 20.sp, color = Color.Black)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

        }

        LazyColumn(
            Modifier
                .background(Color.Blue.copy(0.2f))
                .padding(5.dp)
                .weight(1f)
//                .fillMaxSize()
        ) {
            items(members) { member ->

                if (
                    member.firstname.lowercase()
                        .contains(contactsScreenViewModel.searchText.lowercase()) ||
                    member.lastname.lowercase()
                        .contains(contactsScreenViewModel.searchText.lowercase())
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_CALL).apply {
                                    data = Uri.parse("tel:${member.phonenumber}")
                                }
                                if (ContextCompat.checkSelfPermission(
                                        context, Manifest.permission.CALL_PHONE
                                    ) == PackageManager.PERMISSION_GRANTED
                                ) {
                                    context.startActivity(intent)
                                } else {
                                    permissionLauncher.launch(Manifest.permission.CALL_PHONE)
                                    // Handle permission request or show an error
                                }
                            }
                            .background(Color.White.copy(alpha = 0.9f))
                            .border(1.dp, Color.Black)
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {

                        Image(
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp),

                            painter = painterResource(if (member.gender == "Male") R.drawable.man else R.drawable.woman),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,

                            )
//                }

                        Column(
                            Modifier
                                .weight(1f)
                                .padding(start = 7.dp)
                        ) {
                            Text(
                                member.title + " " + member.firstname + " " + member.lastname,
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp)
                            )
                            Text(member.phonenumber)

                        }

                        Icon(Icons.Default.Phone, contentDescription = "")
                    }
                } else {
                    Box { }
                }

            }
        }
    }


}

@Composable
fun ContactsScreenPreview() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(30))
            .padding(start = 10.dp, end = 10.dp)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
//            Icon(Icons.Default.sear, contentDescription = "")

            Image(
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp),
                painter = painterResource(R.drawable.search),
                contentDescription = "",
            )
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent), onValueChange = {},
//                textStyle = TextStyle(color = Color.Black, fontSize = 30.sp),
                value = "", placeholder = {
                    Text(
                        "Search", style = TextStyle(fontSize = 20.sp, color = Color.Black)
                    )
                }, colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewContactsScreen() {
    Box(Modifier.fillMaxSize()) {
        ContactsScreenPreview()
    }

//    TextField(onValueChange = {}, value = "")

//    ContactsPage(PaddingValues(0.dp), chadventDatabaseViewModel = viewModel())
}