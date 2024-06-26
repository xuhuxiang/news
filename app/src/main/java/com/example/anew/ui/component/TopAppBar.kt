package com.example.anew.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBar(content : @Composable (()->Unit)) {
    val appBarHeight = 56.dp
    Row(modifier=Modifier.fillMaxWidth().height(appBarHeight), horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        content()
    }
}