package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenLeadList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adorastudios.graphqlevaluationassignment.R
import com.adorastudios.graphqlevaluationassignment.presentation.screens.Screens
import com.adorastudios.graphqlevaluationassignment.presentation.screens.screenLeadList.header.Action
import com.adorastudios.graphqlevaluationassignment.presentation.screens.screenLeadList.header.HeaderTile
import com.adorastudios.graphqlevaluationassignment.presentation.screens.screenLeadList.menu.MenuTile

@Composable
fun LeadListScreen(
    navController: NavController,
    viewModel: LeadListViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        HeaderTile(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textId = R.string.header_leads,
            actions = listOf(
                Action(
                    iconId = R.drawable.header_user,
                    action = {
                        navController.navigate(Screens.AddLeadScreen.route)
                    },
                    textDescription = null,
                ),
                Action(
                    iconId = R.drawable.header_search,
                    action = {},
                    textDescription = null,
                ),
            ),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            when (val targetState = viewModel.state.value.loadingState) {
                is LoadingState.Failed -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.errors_failed,
                                targetState.message,
                            ),
                        )
                    }
                }

                is LoadingState.Loaded -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(
                            top = 8.dp,
                            bottom = 116.dp,
                            start = 8.dp,
                            end = 8.dp,
                        ),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(
                            items = targetState.leads,
                            key = { it.id },
                        ) {
                            LeadTile(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        // navController.navigate()
                                    }
                                    .padding(8.dp),
                                lead = it,
                            )
                        }
                        if (targetState.hasMore) {
                            item {
                                LaunchedEffect(key1 = true) {
                                    viewModel.loadSomeMore()
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Refresh,
                                        contentDescription = null,
                                        tint = Color(0xff000000),
                                    )
                                }
                            }
                        }
                    }
                }

                LoadingState.Loading -> {
                }
            }

            MenuTile(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {}
                    .padding(16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xD9000000))
                    .padding(16.dp),
                onTabClick = {},
            )
        }
    }
}
