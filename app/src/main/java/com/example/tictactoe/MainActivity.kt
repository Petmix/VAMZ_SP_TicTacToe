package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.material3.Button
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TTTScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TTTScreen()
{
    val playerTurn = remember { mutableStateOf(true) }

    Header(playerTurn.value)
    Board(playerTurn.value)
}

@Composable
fun Header(playerTurn : Boolean)
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

    val color = colorResource(id = R.color.navy_blue)
    val colorDark = colorResource(id = R.color.dark_navy_blue)
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.padding(0.dp, 150.dp, 0.dp, 0.dp)
    ){
        var playerBoxColor = color
        var aiBoxColor = color
        if (playerTurn)
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
                .background(playerBoxColor)
        ){
            Text(text = "Player", modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center),
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Box(
            modifier = Modifier
                .width(120.dp)
                .height(90.dp)
                .background(aiBoxColor)
        ){
            Text(text = "AI", modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center),
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun Board(playerTurn: Boolean)
{
    val shapes = Shapes(
        extraSmall = RoundedCornerShape(4.dp),
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(16.dp),
        large = RoundedCornerShape(24.dp),
        extraLarge = RoundedCornerShape(32.dp)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Row()
        {
            var image = painterResource(id = R.drawable.woodenx)
            if (!playerTurn)
            {
                image = painterResource(id = R.drawable.throwrings)
            }

            Button(
                onClick = {},
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

            Spacer(modifier = Modifier.width(50.dp))

            Button(
                onClick = {},
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

            Spacer(modifier = Modifier.width(50.dp))

            Button(
                onClick = {},
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