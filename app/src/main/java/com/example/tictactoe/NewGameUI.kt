package com.example.tictactoe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewGameWindow(
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    gamePlay: GamePlay
)
{
    val image = painterResource(R.drawable.newgamebackground)
    var p1Name by remember { mutableStateOf("") }
    var p2Name by remember { mutableStateOf("") }
    var difficultySelected by remember { mutableIntStateOf(0) }

    Box()
    {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Box(
            modifier = Modifier
                .width(380.dp)
                .height(660.dp)
                .padding(0.dp, 0.dp, 0.dp, 120.dp)
                .background(
                    color = colorResource(id = R.color.light_blue),
                    shape = MaterialTheme.shapes.medium
                )
        )
        {
            TextField(
                value = p1Name,
                onValueChange = { if (p2Name == "" && (p1Name.length < 10 || it.length < 10)) p1Name = it },
                modifier = Modifier
                    .padding(30.dp, 60.dp, 0.dp, 0.dp)
                    .width(320.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                label = { Text(stringResource(R.string.name)) }
            )

            TextField(
                value = p2Name,
                onValueChange = { if (p1Name == "" && (p2Name.length < 10 || it.length < 10)) p2Name = it },
                modifier = Modifier
                    .padding(30.dp, 140.dp, 0.dp, 0.dp)
                    .width(320.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Go
                ),
                label = { Text(stringResource(R.string.name)) }
            )

            Image(
                painter = painterResource(id = R.drawable.woodenx),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .scale(1.3f, 1.3f)
                    .padding(0.dp, 0.dp, 15.dp, 280.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.throwrings),
                contentDescription = null,
                modifier = Modifier
                    .padding(265.dp, 115.dp, 0.dp, 0.dp)
                    .scale(0.86f, 0.86f)
            )

            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(0.dp, 260.dp, 0.dp, 0.dp)
            )
            {
                val colorNotSelected = colorResource(id = R.color.navy_blue)
                val colorSelected = colorResource(id = R.color.dark_navy_blue)
                Button(
                    onClick = { difficultySelected = 1 },
                    colors = ButtonColors(containerColor = if (difficultySelected == 1) colorSelected else colorNotSelected,
                        contentColor = if (difficultySelected == 1) colorSelected else colorNotSelected, disabledContainerColor = colorNotSelected,
                        disabledContentColor = colorNotSelected),
                    modifier = Modifier
                        .background(
                            color = if (difficultySelected == 1) colorSelected else colorNotSelected,
                            shape = MaterialTheme.shapes.medium
                        )
                        .width(90.dp)
                        .height(60.dp)
                )
                {
                    Text(
                        text = "Easy",
                        color = Color.White,
                        fontSize = 19.sp
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Button(
                    onClick = { difficultySelected = 2 },
                    colors = ButtonColors(containerColor = if (difficultySelected == 2) colorSelected else colorNotSelected,
                        contentColor = if (difficultySelected == 2) colorSelected else colorNotSelected, disabledContainerColor = colorNotSelected,
                        disabledContentColor = colorNotSelected),
                    modifier = Modifier
                        .background(
                            color = if (difficultySelected == 2) colorSelected else colorNotSelected,
                            shape = MaterialTheme.shapes.medium
                        )
                        .width(120.dp)
                        .height(60.dp)
                )
                {
                    Text(
                        text = "Medium",
                        color = Color.White,
                        fontSize = 19.sp
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Button(
                    onClick = {  difficultySelected = 3 },
                    colors = ButtonColors(containerColor = if (difficultySelected == 3) colorSelected else colorNotSelected,
                        contentColor = if (difficultySelected == 3) colorSelected else colorNotSelected, disabledContainerColor = colorNotSelected,
                        disabledContentColor = colorNotSelected),
                    modifier = Modifier
                        .background(
                            color = if (difficultySelected == 3) colorSelected else colorNotSelected,
                            shape = MaterialTheme.shapes.medium
                        )
                        .width(90.dp)
                        .height(60.dp)
                )
                {
                    Text(
                        text = "Hard",
                        color = Color.White,
                        fontSize = 19.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp, 420.dp, 0.dp, 0.dp)
            )
            {
                Button(
                    onClick = { onCancelButtonClicked() },
                    colors = ButtonColors(containerColor = colorResource(id = R.color.white),
                        contentColor = colorResource(id = R.color.white), disabledContainerColor = colorResource(id = R.color.white),
                        disabledContentColor = colorResource(id = R.color.white)),
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.white),
                            shape = MaterialTheme.shapes.extraLarge
                        )
                        .width(140.dp)
                        .height(60.dp)
                )
                {
                    Text(
                        text = "Cancel",
                        color = colorResource(id = R.color.dark_navy_blue),
                        fontSize = 24.sp
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = {
                        if ((p1Name != "" || p2Name != "") && difficultySelected != 0)
                        {
                            gamePlay.resetGame()
                            gamePlay.setPlayer1Name(p1Name)
                            gamePlay.setPlayer2Name(p2Name)
                            gamePlay.difficulty.intValue = difficultySelected
                            gamePlay.multiPlayerMode.value = false
                            gamePlay.playerTurn.value = p1Name != ""
                            onNextButtonClicked()
                        }
                    },
                    colors = ButtonColors(containerColor = colorResource(id = R.color.navy_blue),
                        contentColor = colorResource(id = R.color.navy_blue), disabledContainerColor = colorResource(id = R.color.navy_blue),
                        disabledContentColor = colorResource(id = R.color.navy_blue)),
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.navy_blue),
                            shape = MaterialTheme.shapes.extraLarge
                        )
                        .width(140.dp)
                        .height(60.dp)
                )
                {
                    Text(
                        text = "Start",
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}