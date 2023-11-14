package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenLeadList

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.adorastudios.graphqlevaluationassignment.com.adorastudios.graphqlevaluationassignment.FetchLeadsQuery

@Composable
fun LeadTile(
    modifier: Modifier = Modifier,
    lead: FetchLeadsQuery.Data1,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            model = lead.avatar?.thumbnail,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE2E2E2),
                            shape = CircleShape,
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = lead.firstName?.firstOrNull()?.uppercase() ?: "",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 16.41.sp,
                        ),
                    )
                }
            } else {
                SubcomposeAsyncImageContent()
            }
        }
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "${lead.firstName ?: ""} ${lead.secondName ?: ""}",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                ),
                color = Color(0xff212121),
                maxLines = 1,
            )
            AsyncImage(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape),
                model = lead.country?.emoji,
                contentDescription = lead.country?.title,
                contentScale = ContentScale.Crop,
            )
        }
        Box(
            modifier = Modifier.height(44.dp),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        lead.color.secondary?.let {
                            Color(android.graphics.Color.parseColor("#$it"))
                        } ?: Color.Transparent,
                    )
                    .padding(horizontal = 8.dp),
            ) {
                Text(
                    text = lead.intention?.title ?: "",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 24.sp,
                    ),
                    color = Color(android.graphics.Color.parseColor("#${lead.color.main}")),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LeadTilePreview() {
    LeadTile(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        lead = FetchLeadsQuery.Data1(
            id = -1,
            firstName = "Sam",
            secondName = "East",
            intention = FetchLeadsQuery.Intention(
                id = -1,
                title = "New",
            ),
            adSource = FetchLeadsQuery.AdSource(
                id = -1,
                title = "Whatever that is",
            ),
            country = FetchLeadsQuery.Country(
                id = -1,
                emoji = "https://upload.wikimedia.org/wikipedia/en/c/c3/Flag_of_France.svg",
                title = "France",
            ),
            languages = listOf(),
            avatar = FetchLeadsQuery.Avatar(
                thumbnail = "https://images.unsplash.com/photo-1493612276216-ee3925520721?q=80&w=3308&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            ),
            color = FetchLeadsQuery.Color(
                main = "276EF1",
                secondary = "EEF3FE",
                title = "???",
            ),
        ),
    )
}
