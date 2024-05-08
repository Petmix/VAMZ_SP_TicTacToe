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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Shapes
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

val shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

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
    Box(modifier = Modifier
        .fillMaxSize()
        .height(2400.dp)){
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .height(2400.dp),
            contentScale = ContentScale.Crop
        )
    }

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    )
    {
        Text(
            text = gamePlay.getNumOfGames().toString() + "/" + gamePlay.getNumOfGamesPlayed().toString(),
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
        var playerBoxColor = color
        var aiBoxColor = color
        if (gamePlay.getPlayerTurn())
        {
            playerBoxColor = colorDark
            aiBoxColor = color
        }
        else
        {
            playerBoxColor = color
            aiBoxColor = colorDark
        }

        Box(
            modifier = Modifier
                .width(120.dp)
                .height(90.dp)
                .background(color = playerBoxColor, shape = shapes.medium,)
        )
        {
            Text(
                text = gamePlay.getPlayer1Name(),
                modifier = Modifier
                    .padding(0.dp, 0.dp, 8.dp, 0.dp),
                color = Color.White,
                fontSize = 20.sp
            )

            Text(
                text = gamePlay.getPlayer1Score().toString(),
                modifier = Modifier
                    .padding(0.dp, 8.dp, 0.dp, 0.dp),
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Box(
            modifier = Modifier
                .width(120.dp)
                .height(90.dp)
                .background(color = aiBoxColor, shape = shapes.medium)
        )
        {
            Text(
                text = gamePlay.getPlayer2Name(),
                modifier = Modifier
                    .padding(0.dp, 0.dp, 8.dp, 0.dp),
                color = Color.White,
                fontSize = 20.sp
            )

            Text(
                text = gamePlay.getPlayer2Score().toString(),
                modifier = Modifier
                    .padding(0.dp, 8.dp, 0.dp, 0.dp),
                color = Color.White,
                fontSize = 20.sp
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
            var image = painterResource(id = R.drawable.woodenx)
            if (!gamePlay.getPlayerTurn())
            {
                image = painterResource(id = R.drawable.throwrings)
            }

            Button(
                onClick = {},
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
                Image(painter = image, contentDescription = null, modifier = Modifier.scale(1.6f, 1.6f))
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {},
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
                Image(painter = painterResource(id = R.drawable.throwrings), contentDescription = null, modifier = Modifier.scale(1.8f, 1.8f))
            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {},
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

            }
        }

        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 30.dp))
        {
            var image = painterResource(id = R.drawable.woodenx)
            if (!gamePlay.getPlayerTurn())
            {
                image = painterResource(id = R.drawable.throwrings)
            }

            Button(
                onClick = {},
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

            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {},
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

            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {},
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

            }
        }

        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 30.dp))
        {
            var image = painterResource(id = R.drawable.woodenx)
            if (!gamePlay.getPlayerTurn())
            {
                image = painterResource(id = R.drawable.throwrings)
            }

            Button(
                onClick = {},
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

            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {},
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

            }

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                onClick = {},
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

            }
        }
    }
}