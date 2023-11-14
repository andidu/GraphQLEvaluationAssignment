package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenLeadList.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adorastudios.graphqlevaluationassignment.R

@Composable
fun MenuTile(
    modifier: Modifier = Modifier,
    onTabClick: (Int) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MenuTab(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            selected = false,
            iconId = R.drawable.menu_home,
            textId = R.string.menu_home,
            onClick = {
                onTabClick(0)
            },
        )
        MenuTab(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            selected = false,
            iconId = R.drawable.menu_phone,
            textId = R.string.menu_phone,
            onClick = {
                onTabClick(1)
            },
        )
        MenuTab(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            selected = false,
            iconId = R.drawable.menu_messages,
            textId = R.string.menu_messages,
            onClick = {
                onTabClick(2)
            },
        )
        MenuTab(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            selected = true,
            iconId = R.drawable.menu_users,
            textId = R.string.menu_users,
            onClick = {
                onTabClick(3)
            },
        )
        MenuTab(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            selected = false,
            iconId = R.drawable.menu_more,
            textId = R.string.menu_more,
            onClick = {
                onTabClick(4)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MenuTilePreview() {
    MenuTile(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xD9000000))
            .padding(16.dp),
        onTabClick = {},
    )
}
