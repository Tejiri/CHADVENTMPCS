package com.xita.chadventmpcs.android.pages

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xita.chadventmpcs.android.R
import com.xita.chadventmpcs.android.customComponents.CustomFormComposables
import com.xita.chadventmpcs.android.customComponents.UIComponents
import com.xita.chadventmpcs.android.viewModels.SettingsScreenViewModel


@Composable
fun SettingsScreen(
    innerPadding: PaddingValues,
    settingsScreenViewModel: SettingsScreenViewModel = viewModel(),
    navController: NavController= rememberNavController(),
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Text(
            "SETTINGS",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp)
        )

        LazyColumn(Modifier.weight(1f)) {

            item {
                SettingsItem(
                    R.drawable.notification,
                    withSwitch = true,
                    text = "Notifications",
                    switchEnabled = settingsScreenViewModel.notificationSwitchEnabled,
                    onSwitchChanged = { newValue ->
                        settingsScreenViewModel.notificationClicked(
                            newValue
                        )
                    },
                )
                SettingsItem(R.drawable.issue,
                    withSwitch = false,
                    text = "Report an issue",
                    settingItemClicked = { settingsScreenViewModel.reportAnIssueClicked() })
                SettingsItem(R.drawable.faq,
                    withSwitch = false,
                    text = "Frequently asked questions",
                    settingItemClicked = { settingsScreenViewModel.frequentlyAskedQuestionsClicked() })

                SettingsItem(R.drawable.light_dark_mode,
                    withSwitch = false,
                    text = "Light / Dark mode",
                    settingItemClicked = { settingsScreenViewModel.frequentlyAskedQuestionsClicked() })
            }
        }

        Row(Modifier.padding(start = 13.dp, end = 13.dp)) {
            CustomFormComposables().CustomButton(
                buttonText = "Log out", onclick = {
                    navController.navigate("login") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                }, isLoading = false, transparent = true
            )

        }


        if (settingsScreenViewModel.isError) {

            UIComponents().BottomAlertDialog(
                title = "Error",
                message = settingsScreenViewModel.errorMessage,
                isError = settingsScreenViewModel.isError
            ) {
                settingsScreenViewModel.isError = false
            }
        }


    }
}

@Composable
fun SettingsItem(
    @DrawableRes image: Int,
    withSwitch: Boolean,
    text: String,
    switchEnabled: Boolean = false,
    onSwitchChanged: (newValue: Boolean) -> Unit = {},
    settingItemClicked: () -> Unit = {}
) {
    Column(
        Modifier.padding(start = 13.dp, end = 13.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { settingItemClicked() }) {
            Image(
                painter = painterResource(image),
                contentDescription = "",
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
            )
//            Icon(Icons.Default.Notifications, contentDescription = "")
            Text(
                text,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )

            if (withSwitch) Switch(
                checked = switchEnabled, onCheckedChange = onSwitchChanged
            ) else Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = ""
            )
        }

        HorizontalDivider(
            thickness = 2.dp, modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(PaddingValues(0.dp))
}