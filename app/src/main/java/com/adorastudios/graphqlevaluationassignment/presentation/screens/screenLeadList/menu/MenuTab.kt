package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenLeadList.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuTab(
    modifier: Modifier = Modifier,
    selected: Boolean,
    iconId: Int,
    textId: Int,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterVertically,
        ),
    ) {
        Box(
            modifier = Modifier
                .size(width = 48.dp, height = 24.dp)
                .clip(CircleShape)
                .clickable(enabled = !selected) { onClick() }
                .background(
                    if (selected) {
                        Color(0xFFE8A889)
                    } else {
                        Color.Transparent
                    },
                ),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(id = iconId),
                contentDescription = stringResource(id = textId),
                colorFilter = ColorFilter.tint(
                    if (selected) {
                        Color(0xff000000)
                    } else {
                        Color(0xB3ffffff)
                    },
                ),
                contentScale = ContentScale.Fit,
            )
        }
        Text(
            text = stringResource(id = textId),
            color = if (selected) {
                Color(0xffffffff)
            } else {
                Color(0xB3ffffff)
            },
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
            ),
        )
    }
}
