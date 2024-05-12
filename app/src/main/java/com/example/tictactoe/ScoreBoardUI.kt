package com.example.tictactoe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ScoreBoardWindow(onBackClick: () -> Unit, gamePlay: GamePlay)
{
    val image = painterResource(R.drawable.scoreboardbackground)
    Box(){
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            verticalAlignment = Alignment.Top,
        )
        {
            Image(
                painter = painterResource(id = R.drawable.scoreboardtext),
                contentDescription = null,
                modifier = Modifier
                    .padding(0.dp, 30.dp, 10.dp, 0.dp)
            )
        }
    }

    GamesList(gamePlay)
}

@Composable
fun GamesList(gamePlay: GamePlay)
{
    val coroutineScope = rememberCoroutineScope()
    //val games = remember { gamePlay.getListFromDao(coroutineScope) }
    val games = remember { DataProvider.gameList }
    Box(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.light_blue),
                shape = shapes.medium
            )
            .width(380.dp)
            .height(820.dp)
            .padding(0.dp ,50.dp, 0.dp, 0.dp)
    )
    {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            userScrollEnabled = true
        )
        {
            items(count = games.size)
            {
                GamesItem(list = games, index = it)
            }
        }
    }
}

@Composable
fun GamesItem(list: List<TTTState>, index: Int)
{
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.light_blue)),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    )
    {
        Row {

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            )
            {
                Text(
                    text = list[index].player1Name,
                    style = typography.headlineMedium,
                    color = colorResource(id = R.color.dark_navy_blue)
                )

                Text(
                    text = list[index].player1Score.toString(),
                    style = typography.headlineSmall,
                    color = colorResource(id = R.color.dark_navy_blue)
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            )
            {
                Text(
                    text = list[index].player2Name,
                    style = typography.headlineMedium,
                    color = colorResource(id = R.color.dark_navy_blue)
                )

                Text(
                    text = list[index].player2Score.toString(),
                    style = typography.headlineSmall,
                    color = colorResource(id = R.color.dark_navy_blue)
                )
            }
        }
    }
}