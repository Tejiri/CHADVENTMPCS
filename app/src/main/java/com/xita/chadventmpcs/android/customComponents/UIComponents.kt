package com.xita.chadventmpcs.android.customComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xita.chadventmpcs.android.ui.theme.BottomAlertGreen

class UIComponents {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BottomAlertDialog(
        title: String,
        message: String,
        isError: Boolean,
        ondismiss:()->Unit
    ) {

        ModalBottomSheet(

            modifier = Modifier
                .wrapContentHeight()
                .padding(start = 10.dp, end = 10.dp, bottom = 50.dp),
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = Color.Transparent,
            shape = RoundedCornerShape(20.dp),
            onDismissRequest = {
                ondismiss()
            },
        ) {
            Box(
                Modifier
                    .padding(20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .fillMaxWidth()
                    .background(if (isError) Color.Red else BottomAlertGreen)
            ) {
                Column(
                    Modifier
                        .padding(20.dp),
                ) {
                    Text(
                        title,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        message,
                        style = TextStyle(color = Color.White, fontSize = 18.sp)
                    )
                }
            }
        }

    }

}