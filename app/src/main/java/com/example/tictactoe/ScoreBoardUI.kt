package com.example.tictactoe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Zobrazuje pozadie a názov obrazovky a vytvára list hier TTTState uložených v databáze.
 */
@Composable
fun ScoreBoardWindow(onBackClick: () -> Unit, score: ScoreBoardViewModel = viewModel(factory = AppViewModelProvider.Factory))
{
    Box( // modré pozadie
        modifier = Modifier
            .width(500.dp)
            .height(850.dp)
            .background(color = colorResource(id = R.color.blue))
    )
    {}


    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(10.dp, 10.dp, 0.dp, 0.dp)
    )
    {
        Button( // tlačidlo na pre vrátenie sa na hlavnú obrazovku
            onClick = { onBackClick() },
            colors = ButtonColors(
                containerColor = colorResource(id = R.color.navy_blue),
                contentColor = colorResource(id = R.color.navy_blue),
                disabledContentColor = colorResource(id = R.color.navy_blue),
                disabledContainerColor = colorResource(id = R.color.navy_blue)
            ),
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.navy_blue),
                    shape = shapes.extraLarge
                )
                .width(60.dp)
                .height(60.dp)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.whiteback),
                contentDescription = null,
                modifier = Modifier
                    .scale(2.2f)
            )
        }
    }

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    )
    {
        Image( // obrázok textu/názvu Score Board
            painter = painterResource(id = R.drawable.scoreboardtext),
            contentDescription = null,
            modifier = Modifier
                .padding(0.dp, 30.dp, 10.dp, 0.dp)
        )
    }

    GamesList(score)
}

/**
 * Vytvorenie listu hier z databázy pomocou LazyColumn.
 */
@Composable
fun GamesList(score: ScoreBoardViewModel)
{
    val scoreState by score.scoreState.collectAsState() // drží list údajov typu [TTTState] z databázy
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(280.dp)
            .height(450.dp)
            .padding(0.dp, 70.dp, 0.dp, 0.dp)
    )
    {
        Box( // bledo-modré ohraničenie listu hier
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.light_blue),
                    shape = shapes.medium
                )
        )
        {
            LazyColumn( // list hier
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                userScrollEnabled = true,
                modifier = Modifier
                    .width(400.dp)
                    .height(800.dp)
            )
            {
                items(count = scoreState.itemList.size)
                {
                    GamesItem(list = scoreState.itemList, index = it)
                }
            }
        }
    }
}

/**
 * Návrh karty v liste. Každá karta predstavuje jednu hru TTTState uloženú v databáze.
 */
@Composable
fun GamesItem(list: List<TTTState>, index: Int)
{
    Card(
        colors = CardColors(
            containerColor = colorResource(id = R.color.light_yellow),
            contentColor = colorResource(id = R.color.light_yellow),
            disabledContainerColor = colorResource(id = R.color.light_yellow),
            disabledContentColor = colorResource(id = R.color.light_yellow)
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .background(color = colorResource(id = R.color.light_yellow))
            .width(380.dp)
            .height(180.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    )
    {
        Row {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
            {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                )
                {
                    Text( // meno hráča 1
                        text = list[index].player1Name,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.dark_text)
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                )
                {
                    Text(
                        text = "vs",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.dark_text)
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Start
                )
                {
                    Text( // meno hráča 2
                        text = list[index].player2Name,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.dark_text)
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 10.dp)
                    .align(Alignment.CenterVertically)
            )
            {
                Row(
                    verticalAlignment = Alignment.Top
                )
                {
                    Image( // obrázok X
                        painter = painterResource(id = R.drawable.woodenx),
                        contentDescription = null,
                        modifier = Modifier
                            .scale(0.8f)
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Row(
                    verticalAlignment = Alignment.Bottom
                )
                {
                    Image( // obrázok O
                        painter = painterResource(id = R.drawable.throwrings),
                        contentDescription = null,
                        modifier = Modifier
                            .scale(0.9f)
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(40.dp, 10.dp, 0.dp, 10.dp)
                    .align(Alignment.CenterVertically)
            )
            {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.End
                )
                {
                    Text( // dátum
                        text = list[index].date,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.dark_text)
                    )
                }

                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.End
                )
                {
                    Text( // čas
                        text = list[index].time,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.dark_text)
                    )
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                )
                {
                    Text( // skóre hráča 1 ku skóre hráča 2
                        text = list[index].player1Score.toString() + ":" + list[index].player2Score.toString(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.dark_text)
                    )
                }
            }
        }
    }
}