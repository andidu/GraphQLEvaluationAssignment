package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenAddLead

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adorastudios.graphqlevaluationassignment.R

@Composable
fun HeaderTile(
    modifier: Modifier = Modifier,
    textId: Int,
    onCLickBack: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF5F5F5))
                .clickable { onCLickBack() },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.header_back),
                contentDescription = null,
                tint = Color(0xff000000),
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = textId),
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
            ),
            color = Color(0xff000000),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderTilePreview() {
    HeaderTile(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        textId = R.string.header_createLead,
        onCLickBack = {},
    )
}
