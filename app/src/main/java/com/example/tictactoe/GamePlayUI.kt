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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TTTScreen(
    gamePlay: GamePlay,
    onGameEnd: () -> Unit
)
{
    Header(gamePlay)
    Board(gamePlay, onGameEnd)
}

@Composable
fun Header(gamePlay: GamePlay)
{
    val image = painterResource(R.drawable.gamebackground)
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

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    )
    {
        Text(
            text = gamePlay.getNumOfGamesPlayed().toString() + "/" + gamePlay.getNumOfGames().toString(),
            modifier = Modifier.padding(0.dp, 80.dp, 0.dp, 0.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
    }

    val color = colorResource(id = R.color.navy_blue)
    val colorDark = colorResource(id = R.color.dark_navy_blue)
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.padding(0.dp, 150.dp, 0.dp, 0.dp)
    )
    {
        Box(
            modifier = Modifier
                .width(120.dp)
                .height(90.dp)
                .background(
                    color = if (gamePlay.getPlayerTurn()) colorDark else color,
                    shape = shapes.medium
                )
        )
        {
            Text(
                text = gamePlay.getPlayer1Name(),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(0.dp, 5.dp, 0.dp, 0.dp),
                color = Color.White,
                fontSize = 24.sp
            )

            Text(
                text = gamePlay.getPlayer1Score().toString(),
                modifier = Modifier
                    .padding(50.dp, 50.dp, 0.dp, 0.dp),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .width(120.dp)
                .height(90.dp)
                .background(
                    color = if (gamePlay.getPlayerTurn()) color else colorDark,
                    shape = shapes.medium
                )
        )
        {
            Text(
                text = gamePlay.getPlayer2Name(),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(0.dp, 5.dp, 0.dp, 0.dp),
                color = Color.White,
                fontSize = 24.sp
            )

            Text(
                text = gamePlay.getPlayer2Score().toString(),
                modifier = Modifier
                    .padding(50.dp, 50.dp, 0.dp, 0.dp),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun Board(gamePlay: GamePlay, onGameEnd: () -> Unit)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 30.dp))
        {
            Button(
                onClick = {
                    gamePlay.setMove(0)
                    gamePlay.goNext()
                },
                colors = ButtonColors(containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue), disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = shapes.medium
                    )
                    .width(100.dp)
                    .height(100.dp)
            )
            {
                GetImageFromMove(gamePlay.getMyPosition(0))
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    gamePlay.setMove(1)
                    gamePlay.goNext()
                },
                colors = ButtonColors(containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue), disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = shapes.medium
                    )
                    .width(100.dp)
                    .height(100.dp)
            )
            {
                GetImageFromMove(gamePlay.getMyPosition(1))
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    gamePlay.setMove(2)
                    gamePlay.goNext()
                },
                colors = ButtonColors(containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue), disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = shapes.medium
                    )
                    .width(100.dp)
                    .height(100.dp)
            )
            {
                GetImageFromMove(gamePlay.getMyPosition(2))
            }
        }

        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 30.dp))
        {
            Button(
                onClick = {
                    gamePlay.setMove(3)
                    gamePlay.goNext()
                },
                colors = ButtonColors(containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue), disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = shapes.medium
                    )
                    .width(100.dp)
                    .height(100.dp)
            )
            {
                GetImageFromMove(gamePlay.getMyPosition(3))
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    gamePlay.setMove(4)
                    gamePlay.goNext()
                },
                colors = ButtonColors(containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue), disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = shapes.medium
                    )
                    .width(100.dp)
                    .height(100.dp)
            )
            {
                GetImageFromMove(gamePlay.getMyPosition(4))
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    gamePlay.setMove(5)
                    gamePlay.goNext()
                },
                colors = ButtonColors(containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue), disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = shapes.medium
                    )
                    .width(100.dp)
                    .height(100.dp)
            )
            {
                GetImageFromMove(gamePlay.getMyPosition(5))
            }
        }

        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 30.dp))
        {
            Button(
                onClick = {
                    gamePlay.setMove(6)
                    gamePlay.goNext()
                },
                colors = ButtonColors(containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue), disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = shapes.medium
                    )
                    .width(100.dp)
                    .height(100.dp)
            )
            {
                GetImageFromMove(gamePlay.getMyPosition(6))
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    gamePlay.setMove(7)
                    gamePlay.goNext()
                },
                colors = ButtonColors(containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue), disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = shapes.medium
                    )
                    .width(100.dp)
                    .height(100.dp)
            )
            {
                GetImageFromMove(gamePlay.getMyPosition(7))
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    gamePlay.setMove(8)
                    gamePlay.goNext()
                },
                colors = ButtonColors(containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue), disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = shapes.medium
                    )
                    .width(100.dp)
                    .height(100.dp)
            )
            {
                GetImageFromMove(gamePlay.getMyPosition(8))
            }
        }
    }
}

@Composable
private fun GetImageFromMove(move: Int)
{
    when(move)
    {
        1 -> Image(
            painter = painterResource(id = R.drawable.woodenx),
            contentDescription = null,
            modifier = Modifier.scale(1.6f, 1.6f)
        )
        2 -> Image(
            painter = painterResource(id = R.drawable.throwrings),
            contentDescription = null,
            modifier = Modifier.scale(1.8f, 1.8f)
        )
        0 -> Image(
            painter = painterResource(id = R.drawable.emptyimage),
            contentDescription = null
        )
    }
}