package com.madtechet.crazypoker.modules.game.presentation.components.gameplay

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madtechet.crazypoker.modules.game.utils.getPokerImageAsset

@Composable
fun Controls(
    playEnabled: Boolean,
    onPlay: (String) -> Unit,
    skipEnabled: Boolean,
    onSkip: () -> Unit,
    asEnabled: Boolean
) {
    val items = listOf("", "club", "diamond", "heart", "spade")
    var selectedSuite by remember { mutableStateOf(items[0]) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(all = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { onPlay(selectedSuite) }, enabled = playEnabled) {
                Text("Play")
            }
            Button(onClick = onSkip, enabled = skipEnabled) {
                Text("Skip")
            }
        }
        if (asEnabled) {
            SuiteDropdown(
                onIndexChange = { selectedSuite = items[it] },
                items = items,
                selectedSuite = selectedSuite
            )
        }
    }
}

@Composable
fun GetContent(s: String) {
    return if (s.isEmpty())
        Text(text = s)
    else
        Image(
            painter = painterResource(id = getPokerImageAsset(s)),
            contentDescription = "Suite Icon",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
}

@Composable
fun SuiteDropdown(
    onIndexChange: (Int) -> Unit,
    items: List<String>,
    selectedSuite: String,
) {
    var expanded by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxHeight()) {
        Text("Play As:")
        Spacer(modifier = Modifier.width(10.dp))
        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
            Row(Modifier.clickable {
                expanded = true
            }) {
                Box(modifier = Modifier.width(30.dp)) {
                    GetContent(s = selectedSuite)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(30.dp)
                ) {
                    items.forEachIndexed { index, s ->
                        DropdownMenuItem(
                            onClick = {
                                onIndexChange(index)
                                expanded = false
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            GetContent(s)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewControls() {
    Controls(
        true,
        {},
        true,
        {},
        asEnabled = true
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewControlsDisabled() {
    Controls(
        false,
        {},
        false,
        {},
        asEnabled = false
    )
}
