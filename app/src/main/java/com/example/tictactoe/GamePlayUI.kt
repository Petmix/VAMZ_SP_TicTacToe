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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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

    val numOfGames = remember { mutableStateOf(gamePlay.getNumOfGamesPlayed().toString()) }
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    )
    {
        Text(
            text = numOfGames.value,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(0.dp, 80.dp, 0.dp, 0.dp)
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
    val coroutineScope = rememberCoroutineScope()
    val xImage = painterResource(id = R.drawable.woodenx)
    val oImage = painterResource(id = R.drawable.throwrings)
    val nImage = painterResource(id = R.drawable.emptyimage)

    val a1 = remember { mutableFloatStateOf(0.0f) }
    val a2 = remember { mutableFloatStateOf(0.0f) }
    val a3 = remember { mutableFloatStateOf(0.0f) }
    val a4 = remember { mutableFloatStateOf(0.0f) }
    val a5 = remember { mutableFloatStateOf(0.0f) }
    val a6 = remember { mutableFloatStateOf(0.0f) }
    val a7 = remember { mutableFloatStateOf(0.0f) }
    val a8 = remember { mutableFloatStateOf(0.0f) }
    val a9 = remember { mutableFloatStateOf(0.0f) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Row(modifier = Modifier.padding(0.dp, 80.dp, 0.dp, 0.dp))
        {
            Button(
                onClick = {
                    if (gamePlay.moves[0] == null && !gamePlay.gameOver.value)
                    {
                        gamePlay.setMove(0)
                        a1.floatValue = 1.0f
                        gamePlay.checkEnd()
                        gamePlay.goNext()
                        if (!gamePlay.multiPlayerMode.value && !gamePlay.gameOver.value)
                        {
                            val index = gamePlay.moveAI()
                            when (index)
                            {
                                0 -> a1.floatValue = 1.0f
                                1 -> a2.floatValue = 1.0f
                                2 -> a3.floatValue = 1.0f
                                3 -> a4.floatValue = 1.0f
                                4 -> a5.floatValue = 1.0f
                                5 -> a6.floatValue = 1.0f
                                6 -> a7.floatValue = 1.0f
                                7 -> a8.floatValue = 1.0f
                                8 -> a9.floatValue = 1.0f
                                9 -> gamePlay.gameOver.value = true
                            }
                            gamePlay.checkEnd()
                            gamePlay.goNext()
                        }
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
                        painter = if (gamePlay.moves[0] == true) xImage else if (gamePlay.moves[0] == false) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.moves[0] == false) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = a1.floatValue
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    if (gamePlay.moves[1] == null && !gamePlay.gameOver.value)
                    {
                        gamePlay.setMove(1)
                        a2.floatValue = 1.0f
                        gamePlay.checkEnd()
                        gamePlay.goNext()
                        if (!gamePlay.multiPlayerMode.value && !gamePlay.gameOver.value)
                        {
                            val index = gamePlay.moveAI()
                            when (index)
                            {
                                0 -> a1.floatValue = 1.0f
                                1 -> a2.floatValue = 1.0f
                                2 -> a3.floatValue = 1.0f
                                3 -> a4.floatValue = 1.0f
                                4 -> a5.floatValue = 1.0f
                                5 -> a6.floatValue = 1.0f
                                6 -> a7.floatValue = 1.0f
                                7 -> a8.floatValue = 1.0f
                                8 -> a9.floatValue = 1.0f
                                9 -> gamePlay.gameOver.value = true
                            }
                            gamePlay.checkEnd()
                            gamePlay.goNext()
                        }
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
                        painter = if (gamePlay.moves[1] == true) xImage else if (gamePlay.moves[1] == false) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.moves[1] == false) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = a2.floatValue
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    if (gamePlay.moves[2] == null && !gamePlay.gameOver.value)
                    {
                        gamePlay.setMove(2)
                        a3.floatValue = 1.0f
                        gamePlay.checkEnd()
                        gamePlay.goNext()
                        if (!gamePlay.multiPlayerMode.value && !gamePlay.gameOver.value)
                        {
                            val index = gamePlay.moveAI()
                            when (index)
                            {
                                0 -> a1.floatValue = 1.0f
                                1 -> a2.floatValue = 1.0f
                                2 -> a3.floatValue = 1.0f
                                3 -> a4.floatValue = 1.0f
                                4 -> a5.floatValue = 1.0f
                                5 -> a6.floatValue = 1.0f
                                6 -> a7.floatValue = 1.0f
                                7 -> a8.floatValue = 1.0f
                                8 -> a9.floatValue = 1.0f
                                9 -> gamePlay.gameOver.value = true
                            }
                            gamePlay.checkEnd()
                            gamePlay.goNext()
                        }
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
                        painter = if (gamePlay.moves[2] == true) xImage else if (gamePlay.moves[2] == false) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.moves[2] == false) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = a3.floatValue
                    )
                }
            }
        }

        Row(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp))
        {
            Button(
                onClick = {
                    if (gamePlay.moves[3] == null && !gamePlay.gameOver.value)
                    {
                        gamePlay.setMove(3)
                        a4.floatValue = 1.0f
                        gamePlay.checkEnd()
                        gamePlay.goNext()
                        if (!gamePlay.multiPlayerMode.value && !gamePlay.gameOver.value)
                        {
                            val index = gamePlay.moveAI()
                            when (index)
                            {
                                0 -> a1.floatValue = 1.0f
                                1 -> a2.floatValue = 1.0f
                                2 -> a3.floatValue = 1.0f
                                3 -> a4.floatValue = 1.0f
                                4 -> a5.floatValue = 1.0f
                                5 -> a6.floatValue = 1.0f
                                6 -> a7.floatValue = 1.0f
                                7 -> a8.floatValue = 1.0f
                                8 -> a9.floatValue = 1.0f
                                9 -> gamePlay.gameOver.value = true
                            }
                            gamePlay.checkEnd()
                            gamePlay.goNext()
                        }
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
                        painter = if (gamePlay.moves[3] == true) xImage else if (gamePlay.moves[3] == false) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.moves[3] == false) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = a4.floatValue
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    if (gamePlay.moves[4] == null && !gamePlay.gameOver.value)
                    {
                        gamePlay.setMove(4)
                        a5.floatValue = 1.0f
                        gamePlay.checkEnd()
                        gamePlay.goNext()
                        if (!gamePlay.multiPlayerMode.value && !gamePlay.gameOver.value)
                        {
                            val index = gamePlay.moveAI()
                            when (index)
                            {
                                0 -> a1.floatValue = 1.0f
                                1 -> a2.floatValue = 1.0f
                                2 -> a3.floatValue = 1.0f
                                3 -> a4.floatValue = 1.0f
                                4 -> a5.floatValue = 1.0f
                                5 -> a6.floatValue = 1.0f
                                6 -> a7.floatValue = 1.0f
                                7 -> a8.floatValue = 1.0f
                                8 -> a9.floatValue = 1.0f
                                9 -> gamePlay.gameOver.value = true
                            }
                            gamePlay.checkEnd()
                            gamePlay.goNext()
                        }
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
                        painter = if (gamePlay.moves[4] == true) xImage else if (gamePlay.moves[4] == false) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.moves[4] == false) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = a5.floatValue
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    if (gamePlay.moves[5] == null && !gamePlay.gameOver.value)
                    {
                        gamePlay.setMove(5)
                        a6.floatValue = 1.0f
                        gamePlay.checkEnd()
                        gamePlay.goNext()
                        if (!gamePlay.multiPlayerMode.value && !gamePlay.gameOver.value)
                        {
                            val index = gamePlay.moveAI()
                            when (index)
                            {
                                0 -> a1.floatValue = 1.0f
                                1 -> a2.floatValue = 1.0f
                                2 -> a3.floatValue = 1.0f
                                3 -> a4.floatValue = 1.0f
                                4 -> a5.floatValue = 1.0f
                                5 -> a6.floatValue = 1.0f
                                6 -> a7.floatValue = 1.0f
                                7 -> a8.floatValue = 1.0f
                                8 -> a9.floatValue = 1.0f
                                9 -> gamePlay.gameOver.value = true
                            }
                            gamePlay.checkEnd()
                            gamePlay.goNext()
                        }
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
                        painter = if (gamePlay.moves[5] == true) xImage else if (gamePlay.moves[5] == false) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.moves[5] == false) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = a6.floatValue
                    )
                }
            }
        }

        Row(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp))
        {
            Button(
                onClick = {
                    if (gamePlay.moves[6] == null && !gamePlay.gameOver.value)
                    {
                        gamePlay.setMove(6)
                        a7.floatValue = 1.0f
                        gamePlay.checkEnd()
                        gamePlay.goNext()
                        if (!gamePlay.multiPlayerMode.value && !gamePlay.gameOver.value)
                        {
                            val index = gamePlay.moveAI()
                            when (index)
                            {
                                0 -> a1.floatValue = 1.0f
                                1 -> a2.floatValue = 1.0f
                                2 -> a3.floatValue = 1.0f
                                3 -> a4.floatValue = 1.0f
                                4 -> a5.floatValue = 1.0f
                                5 -> a6.floatValue = 1.0f
                                6 -> a7.floatValue = 1.0f
                                7 -> a8.floatValue = 1.0f
                                8 -> a9.floatValue = 1.0f
                                9 -> gamePlay.gameOver.value = true
                            }
                            gamePlay.checkEnd()
                            gamePlay.goNext()
                        }
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
                        painter = if (gamePlay.moves[6] == true) xImage else if (gamePlay.moves[6] == false) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.moves[6] == false) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = a7.floatValue
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    if (gamePlay.moves[7] == null && !gamePlay.gameOver.value)
                    {
                        gamePlay.setMove(7)
                        a8.floatValue = 1.0f
                        gamePlay.checkEnd()
                        gamePlay.goNext()
                        if (!gamePlay.multiPlayerMode.value && !gamePlay.gameOver.value)
                        {
                            val index = gamePlay.moveAI()
                            when (index)
                            {
                                0 -> a1.floatValue = 1.0f
                                1 -> a2.floatValue = 1.0f
                                2 -> a3.floatValue = 1.0f
                                3 -> a4.floatValue = 1.0f
                                4 -> a5.floatValue = 1.0f
                                5 -> a6.floatValue = 1.0f
                                6 -> a7.floatValue = 1.0f
                                7 -> a8.floatValue = 1.0f
                                8 -> a9.floatValue = 1.0f
                                9 -> gamePlay.gameOver.value = true
                            }
                            gamePlay.checkEnd()
                            gamePlay.goNext()
                        }
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
                        painter = if (gamePlay.moves[7] == true) xImage else if (gamePlay.moves[7] == false) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.moves[7] == false) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = a8.floatValue
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    if (gamePlay.moves[8] == null && !gamePlay.gameOver.value)
                    {
                        gamePlay.setMove(8)
                        a9.floatValue = 1.0f
                        gamePlay.checkEnd()
                        gamePlay.goNext()
                        if (!gamePlay.multiPlayerMode.value && !gamePlay.gameOver.value)
                        {
                            val index = gamePlay.moveAI()
                            when (index)
                            {
                                0 -> a1.floatValue = 1.0f
                                1 -> a2.floatValue = 1.0f
                                2 -> a3.floatValue = 1.0f
                                3 -> a4.floatValue = 1.0f
                                4 -> a5.floatValue = 1.0f
                                5 -> a6.floatValue = 1.0f
                                6 -> a7.floatValue = 1.0f
                                7 -> a8.floatValue = 1.0f
                                8 -> a9.floatValue = 1.0f
                                9 -> gamePlay.gameOver.value = true
                            }
                            gamePlay.checkEnd()
                            gamePlay.goNext()
                        }
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
                        painter = if (gamePlay.moves[8] == true) xImage else if (gamePlay.moves[8] == false) oImage else nImage,
                        contentDescription = null,
                        modifier = if (gamePlay.moves[8] == false) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f),
                        alpha = a9.floatValue
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(0.dp, 80.dp, 0.dp, 0.dp)
        )
        {
            Button(
                onClick = {
                    if (gamePlay.gameOver.value)
                    {
                        gamePlay.addNumOfGamesPlayed()
                    }
                    gamePlay.saveToDatabase(coroutineScope)
                    onGameEnd()
                },
                colors = ButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.White,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.White
                ),
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = shapes.extraLarge
                    )
                    .width(140.dp)
                    .height(60.dp)
            )
            {
                Text(
                    text = "Exit",
                    color = colorResource(id = R.color.dark_navy_blue),
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {
                    if (gamePlay.gameOver.value)
                    {
                        gamePlay.playAgain()
                        a1.floatValue = 0.0f
                        a2.floatValue = 0.0f
                        a3.floatValue = 0.0f
                        a4.floatValue = 0.0f
                        a5.floatValue = 0.0f
                        a6.floatValue = 0.0f
                        a7.floatValue = 0.0f
                        a8.floatValue = 0.0f
                        a9.floatValue = 0.0f
                    }
                },
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue),
                    disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = shapes.extraLarge
                    )
                    .width(140.dp)
                    .height(60.dp)
            )
            {
                Text(
                    text = "Again",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}