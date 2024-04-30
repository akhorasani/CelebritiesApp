package com.ali.celebritiesapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ali.celebritiesapp.R

@Composable
fun CelebritiesAppLogo(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.displaySmall,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(start = 75.dp, top = 15.dp),
        color = Color(0xFF7D0C91)
    )
}

@ExperimentalMaterial3Api
@Composable
fun CelebritiesTopAppBar(
    title: String,
    icon: ImageVector? = null,
    onBackArrowClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = stringResource(id = R.string.arrow_back),
                        tint = Color(0xFF7D0C91).copy(alpha = 0.7f),
                        modifier = Modifier.clickable { onBackArrowClicked.invoke() }
                    )
                    Spacer(modifier = Modifier.width(80.dp))
                }
                Text(
                    text = title,
                    color = Color(0xFF7D0C91),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Serif
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }
        }, modifier = Modifier.background(Color.Transparent)
    )
}