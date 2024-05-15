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

/**
 * Používateľské rozhranie pre hernú plochu.
 * Pre funkcionalitu a uchovávanie dát využíva inštanciu triedy GamePlay, ktorá dedí z triedy ViewModel.
 */
@Composable
fun TTTScreen(
    gamePlay: GamePlay,
    onGameEnd: () -> Unit
)
{
    Header(gamePlay)
    Board(gamePlay, onGameEnd)
}

/**
 * Vrchná časť používateľského rozhrania a pozadie.
 * Ukazuje kto je na rade, kto má koľko bodov a koľko hier bolo odohraných.
 */
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
        modifier = Modifier.padding(0.dp, 150.dp, 0.dp, 0.dp)
    )
    {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(60.dp, 0.dp, 0.dp, 0.dp)
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
                    fontSize = 20.sp
                )

                Text(
                    text = gamePlay.getPlayer1Score().toString(),
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .padding(90.dp, 0.dp, 0.dp, 0.dp)
        )
        {
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
                    fontSize = 20.sp
                )

                Text(
                    text = gamePlay.getPlayer2Score().toString(),
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }
}

/**
 * Spodná časť - herná plocha.
 * Je zložená z 9 tlačidiel, ktorých obrázky sa menia a zobrazujú zmenou hodnôt v triede GamePlay.
 * Na spodku sa 2 ďalšie tlačidlá pre hranie znovu alebo ukončenie a uloženie.
 */
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
                        gamePlay.setMove(0) // Nastav môj ťah
                        a1.floatValue = 1.0f // Zobraz obrázok
                        gamePlay.checkEnd() // Pozri či nie je koniec
                        gamePlay.goNext() // Ide ďalší
                        if (!gamePlay.multiPlayerMode.value && !gamePlay.gameOver.value)
                        {
                            val index = gamePlay.moveAI() // AI vyberie políčko a vráti číslo tlačidla
                            when (index)
                            {
                                0 -> a1.floatValue = 1.0f // Zobrazí príslušný obrázok
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
                            gamePlay.checkEnd() // Pozri či nie je koniec
                            gamePlay.goNext() // Ide zase hráč
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
                        painter = if (gamePlay.moves[0] == true) xImage else if (gamePlay.moves[0] == false) oImage else nImage, // Kontroluje stav a mení obrázok príslušne
                        contentDescription = null,
                        modifier = if (gamePlay.moves[0] == false) Modifier.scale(1.8f, 1.8f) else Modifier.scale(1.6f, 1.6f), // Zmena veľksoti podľa obrázku
                        alpha = a1.floatValue // Zobrazenie alebo zmiznutie obrázku
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
                    if (gamePlay.gameOver.value) // Kontrola v prípade, že by hráč ukončil hru predčasne
                    {
                        gamePlay.addNumOfGamesPlayed() // Zvýš počet hier o 1 ak je koniec hry
                    }
                    if (gamePlay.multiPlayerMode.value) gamePlay.saveToDatabase(coroutineScope) // Ulož dáta do databázy
                    onGameEnd() // Vráť sa na hlavnú obrázovku
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
                    if (gamePlay.gameOver.value) // Iba ak je hra ukončená - niekto vyhral alebo je remíza
                    {
                        gamePlay.playAgain() // Spusti hru znova - resetuje ťahy
                        a1.floatValue = 0.0f // Schová obrázky
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