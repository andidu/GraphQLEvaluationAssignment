package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenAddLead

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adorastudios.graphqlevaluationassignment.R

@Composable
fun SaveMenu(
    modifier: Modifier = Modifier,
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Box(
            modifier = Modifier.weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    onNegativeClick()
                }
                .border(
                    width = 1.dp,
                    color = Color(0xFFE8A889),
                    shape = RoundedCornerShape(12.dp),
                )
                .padding(vertical = 12.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.menu_cancel),
                color = Color(0xFFE8A889),
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                ),
            )
        }
        Box(
            modifier = Modifier.weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    onPositiveClick()
                }
                .background(color = Color(0xFFE8A889))
                .padding(vertical = 12.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.menu_save),
                color = Color(0xFF000000),
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SaveMenuPreview() {
    SaveMenu(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xD9000000))
            .padding(16.dp),
        onPositiveClick = {},
        onNegativeClick = {},
    )
}
