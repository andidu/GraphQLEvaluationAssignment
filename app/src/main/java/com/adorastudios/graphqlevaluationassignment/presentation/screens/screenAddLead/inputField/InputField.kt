package com.adorastudios.graphqlevaluationassignment.presentation.screens.screenAddLead.inputField

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun InputField(
    modifier: Modifier,
    titleId: Int,
    value: String,
    placeholderId: Int,
    onValueChanged: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = stringResource(id = titleId),
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
            ),
            color = Color(0xff000000),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFFAFAFA))
                .border(
                    width = 1.dp,
                    color = Color(0xFFEEEEEE),
                    shape = RoundedCornerShape(12.dp),
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            if (value.isEmpty()) {
                Text(
                    text = stringResource(id = placeholderId),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                    ),
                    color = Color(0xff616161),
                )
            }
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChanged,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    color = Color(0xff616161),
                ),
            )
        }
    }
}

@Composable
fun InputField(
    modifier: Modifier,
    titleId: Int,
    value: String,
    placeholderId: Int,
    onValueChanged: (InputFieldOption) -> Unit,
    options: List<InputFieldOption>,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = stringResource(id = titleId),
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
            ),
            color = Color(0xff000000),
        )
        var showOptions by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFFAFAFA))
                .clickable { showOptions = !showOptions }
                .border(
                    width = 1.dp,
                    color = Color(0xFFEEEEEE),
                    shape = RoundedCornerShape(12.dp),
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = value.takeIf { it.isNotEmpty() } ?: stringResource(id = placeholderId),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                ),
                color = Color(0xff616161),
            )
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.options_down),
                contentDescription = null,
            )
        }
        AnimatedVisibility(visible = showOptions && options.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(vertical = 8.dp),
            ) {
                items(
                    items = options,
                    key = { it.id },
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onValueChanged(it)
                                showOptions = false
                            }
                            .padding(vertical = 16.dp, horizontal = 20.dp),
                        text = it.value,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                        ),
                        color = Color(0xff212121),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    var value by remember { mutableStateOf("") }
    InputField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        titleId = R.string.addLeadScreen_firstName,
        value = value,
        placeholderId = R.string.addLeadScreen_firstNamePlaceholder,
        onValueChanged = {
            value = it
        },
    )
}

@Preview(showBackground = true)
@Composable
fun InputFieldOptionsPreview() {
    var value by remember { mutableStateOf("") }
    InputField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        titleId = R.string.addLeadScreen_language,
        value = value,
        placeholderId = R.string.addLeadScreen_selectPlaceholder,
        onValueChanged = {
            value = it.value
        },
        options = listOf(
            InputFieldOption(1, "One"),
            InputFieldOption(2, "Two"),
            InputFieldOption(3, "Three"),
            InputFieldOption(4, "Four"),
            InputFieldOption(5, "Five"),
        ),
    )
}
