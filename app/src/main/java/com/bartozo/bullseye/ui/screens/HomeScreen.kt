package com.bartozo.bullseye.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bartozo.bullseye.R
import com.bartozo.bullseye.ui.components.Button
import com.bartozo.bullseye.ui.components.Label
import com.bartozo.bullseye.viewmodel.GameViewModel

@Composable
fun HomeScreen(viewModel: GameViewModel) {

    val round: Int by viewModel
        .round
        .observeAsState(0)

    val score: Int by viewModel
        .score
        .observeAsState(0)

    val target: Int by viewModel
        .target
        .observeAsState(0)

    val sliderValue: Float by viewModel
        .sliderValue
        .observeAsState(0f)

    val guessValueDialogShownState: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(false)
    }

    val infoDialogShownState: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "\uD83C\uDFAF Bullseye \uD83C\uDFAF",
                        color = MaterialTheme.colors.onPrimary
                    )
                },
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = "background image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TargetLabel(target = target)
                    SliderRow(
                        sliderValue = sliderValue,
                        onSliderValueChange = { viewModel.updateSlider(it) }
                    )
                    Button(
                        text = "Hit me!",
                        onClickAction = {
                            guessValueDialogShownState.value = true
                        }
                    )
                    ScoreRow(
                        score = score,
                        round = round,
                        onInfoClicked = {
                            infoDialogShownState.value = true
                        },
                        onStartOverClicked = {
                            viewModel.startNewGame()
                        }
                    )
                }
            }

            if (guessValueDialogShownState.value) {
                AlertDialog(
                    onDismissRequest = {
                        guessValueDialogShownState.value = false
                        viewModel.startNewRound(round, score)
                    },
                    title = {
                        Text(viewModel.alertTitle())
                    },
                    text = {
                        Text(viewModel.scoringMessage())
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                guessValueDialogShownState.value = false
                                viewModel.startNewRound(round, score)
                            }) {
                            Text("Awesome!")
                        }
                    }
                )
            }

            if (infoDialogShownState.value) {
                AlertDialog(
                    onDismissRequest = {
                        infoDialogShownState.value = false
                    },
                    title = {
                        Text(
                            text = "\uD83C\uDFAF Bullseye \uD83C\uDFAF"
                        )
                    },
                    text = {
                        Text(
                            text = "This is Bullseye, the game where you can win points and earn" +
                                    " fame by dragging a slider.\n\n" +
                                    "Your goal is to place the slider as close as possible to the " +
                                    "target value. The closer you are, the more points you score."
                        )
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                infoDialogShownState.value = false
                            }) {
                            Text("Enjoy!")
                        }
                    }
                )
            }
        }
    )
}

@Composable
private fun SliderRow(
    modifier: Modifier = Modifier,
    sliderValue: Float,
    onSliderValueChange: (Float) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = "1",
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Slider(
            modifier = Modifier
                .weight(9f),
            value = sliderValue,
            onValueChange = onSliderValueChange,
            valueRange = 0f..100f,
            steps = 100,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.Green
            )
        )
        Text(
            modifier = Modifier
                .weight(1f),
            text = "100",
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun SliderRowPreview() {
    SliderRow(sliderValue = 0f) {}
}


@Composable
private fun ScoreRow(
    modifier: Modifier = Modifier,
    score: Int,
    round: Int,
    onStartOverClicked: () -> Unit = {},
    onInfoClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            text = "Star over",
            icon = R.drawable.start_over_icon,
            onClickAction = onStartOverClicked
        )
        Label(
            text = "Score:",
            value = score
        )
        Label(
            text = "Round:",
            value = round
        )
        Button(
            text = "Info",
            icon = R.drawable.info_icon,
            onClickAction = onInfoClicked
        )
    }
}

@Preview
@Composable
private fun ScoreRowPreview() {
    ScoreRow(score = 0, round = 1)
}

@Composable
private fun TargetLabel(
    modifier: Modifier = Modifier,
    target: Int
) {
    Label(
        modifier = modifier,
        text = "Put the bullseye as close as you can to:",
        value = target
    )
}

@Preview
@Composable
private fun TargetLabelPreview() {
    TargetLabel(target = 100)
}
