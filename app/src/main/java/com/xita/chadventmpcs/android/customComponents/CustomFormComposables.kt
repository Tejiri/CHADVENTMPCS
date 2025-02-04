package com.xita.chadventmpcs.android.customComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xita.chadventmpcs.android.ui.theme.Purple40

class CustomFormComposables {


    @Composable
    fun CustomTextField(
        value: String,
        placeholder: String,
        onValueChange: (textFieldValue: String) -> Unit,
        obscureText: Boolean
    ) {
        OutlinedTextField(
            value = value,
            visualTransformation = if (obscureText) PasswordVisualTransformation() else VisualTransformation.None,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
                .background(Color.Gray.copy(alpha = 0.3f))
        )
    }

    @Composable
    fun CustomButton(
        onclick: () -> Unit,
        isLoading: Boolean,
        buttonText: String,
        transparent: Boolean = false
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
                .clip(RoundedCornerShape(40))
                .background(if (transparent) Color.Transparent else Purple40)
                .border(3.dp, Purple40, RoundedCornerShape(40))
                .padding(12.dp)
                .clickable(onClick = onclick),
        ) {
            if (isLoading) CircularProgressIndicator(
                modifier = Modifier
                    .align(alignment = Alignment.Center), color = Color.White
            ) else Text(
                buttonText,
                color = if (transparent) Purple40 else Color.White,
                modifier = Modifier
                    .align(alignment = Alignment.Center),
                style = TextStyle(fontSize = 25.sp)
            )
        }
    }

}