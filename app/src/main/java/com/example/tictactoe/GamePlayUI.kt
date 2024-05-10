package com.example.tictactoe

import android.webkit.ConsoleMessage
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import java.io.Console

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
                    color = if (gamePlay.playerTurn.value) colorDark else color,
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
                    color = if (gamePlay.playerTurn.value) color else colorDark,
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
    val xImage = painterResource(id = R.drawable.woodenx)
    val oImage = painterResource(id = R.drawable.throwrings)
    val nImage = painterResource(id = R.drawable.emptyimage)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 30.dp))
        {
            val f1 = remember { mutableFloatStateOf(0.0f) }
            Button(
                onClick = {
                    if (gamePlay.getMyPosition(0) == 0)
                    {
                        gamePlay.setMove(0)
                        f1.floatValue = 1.0f
                        gamePlay.goNext()
                    }
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
                Box()
                {
                    Image(
                        painter = if (gamePlay.getMyPosition(0) == 1) xImage else if (gamePlay.getMyPosition(0) == 2) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.getMyPosition(0) == 2) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = f1.floatValue
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            val f2 = remember { mutableFloatStateOf(0.0f) }
            Button(
                onClick = {
                    if (gamePlay.getMyPosition(1) == 0)
                    {
                        gamePlay.setMove(1)
                        f2.floatValue = 1.0f
                        gamePlay.goNext()
                    }
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
                Box()
                {
                    Image(
                        painter = if (gamePlay.getMyPosition(1) == 1) xImage else if (gamePlay.getMyPosition(1) == 2) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.getMyPosition(1) == 2) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = f2.floatValue
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            val f3 = remember { mutableFloatStateOf(0.0f) }
            Button(
                onClick = {
                    if (gamePlay.getMyPosition(2) == 0)
                    {
                        gamePlay.setMove(2)
                        f3.floatValue = 1.0f
                        gamePlay.goNext()
                    }
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
                Box()
                {
                    Image(
                        painter = if (gamePlay.getMyPosition(2) == 1) xImage else if (gamePlay.getMyPosition(2) == 2) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.getMyPosition(2) == 2) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = f3.floatValue
                    )
                }
            }
        }

        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 30.dp))
        {
            val f4 = remember { mutableFloatStateOf(0.0f) }
            Button(
                onClick = {
                    if (gamePlay.getMyPosition(3) == 0)
                    {
                        gamePlay.setMove(3)
                        f4.floatValue = 1.0f
                        gamePlay.goNext()
                    }
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
                Box()
                {
                    Image(
                        painter = if (gamePlay.getMyPosition(3) == 1) xImage else if (gamePlay.getMyPosition(3) == 2) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.getMyPosition(3) == 2) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = f4.floatValue
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            val f5 = remember { mutableFloatStateOf(0.0f) }
            Button(
                onClick = {
                    if (gamePlay.getMyPosition(4) == 0)
                    {
                        gamePlay.setMove(4)
                        f5.floatValue = 1.0f
                        gamePlay.goNext()
                    }
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
                Box()
                {
                    Image(
                        painter = if (gamePlay.getMyPosition(4) == 1) xImage else if (gamePlay.getMyPosition(4) == 2) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.getMyPosition(4) == 2) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = f5.floatValue
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            val f6 = remember { mutableFloatStateOf(0.0f) }
            Button(
                onClick = {
                    if (gamePlay.getMyPosition(5) == 0)
                    {
                        gamePlay.setMove(5)
                        f6.floatValue = 1.0f
                        gamePlay.goNext()
                    }
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
                Box()
                {
                    Image(
                        painter = if (gamePlay.getMyPosition(5) == 1) xImage else if (gamePlay.getMyPosition(5) == 2) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.getMyPosition(5) == 2) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = f6.floatValue
                    )
                }
            }
        }

        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 30.dp))
        {
            val f7 = remember { mutableFloatStateOf(0.0f) }
            Button(
                onClick = {
                    if (gamePlay.getMyPosition(6) == 0)
                    {
                        gamePlay.setMove(6)
                        f7.floatValue = 1.0f
                        gamePlay.goNext()
                    }
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
                Box()
                {
                    Image(
                        painter = if (gamePlay.getMyPosition(6) == 1) xImage else if (gamePlay.getMyPosition(6) == 2) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.getMyPosition(6) == 2) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = f7.floatValue
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            val f8 = remember { mutableFloatStateOf(0.0f) }
            Button(
                onClick = {
                    if (gamePlay.getMyPosition(7) == 0)
                    {
                        gamePlay.setMove(7)
                        f8.floatValue = 1.0f
                        gamePlay.goNext()
                    }
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
                Box()
                {
                    Image(
                        painter = if (gamePlay.getMyPosition(7) == 1) xImage else if (gamePlay.getMyPosition(7) == 2) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.getMyPosition(7) == 2) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = f8.floatValue
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            val f9 = remember { mutableFloatStateOf(0.0f) }
            Button(
                onClick = {
                    if (gamePlay.getMyPosition(8) == 0)
                    {
                        gamePlay.setMove(8)
                        f9.floatValue = 1.0f
                        gamePlay.goNext()
                    }
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
                Box()
                {
                    Image(
                        painter = if (gamePlay.getMyPosition(8) == 1) xImage else if (gamePlay.getMyPosition(8) == 2) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.getMyPosition(8) == 2) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = f9.floatValue
                    )
                }
            }
        }
    }
}