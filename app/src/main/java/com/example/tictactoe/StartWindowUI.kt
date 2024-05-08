package com.example.tictactoe

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class TTTApp(@StringRes val title: Int)
{
    Start(title = R.string.app_name),
    NewGame(title = R.string.new_game),
    GamePlay(title = R.string.game_play),
    ScoreBoard(title = R.string.score)
}

@Composable
fun MainWindow(
    onSingleOrMultiPlayerClick: () -> Unit = {},
    onScoreBoardClick: () -> Unit = {}
)
{
    val image = painterResource(R.drawable.tictactoebackground)
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
        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 30.dp))
        {
            Button(
                onClick = { onSingleOrMultiPlayerClick() },
                colors = ButtonColors(containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue), disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = MaterialTheme.shapes.medium
                    )
                    .width(300.dp)
                    .height(100.dp)
            )
            {
                Text(
                    text = "Singleplayer",
                    color = Color.White,
                    fontSize = 32.sp
                )
            }
        }

        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 30.dp))
        {
            Button(
                onClick = { onSingleOrMultiPlayerClick() },
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue),
                    disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = MaterialTheme.shapes.medium
                    )
                    .width(300.dp)
                    .height(100.dp)
            )
            {
                Text(
                    text = "Multiplayer",
                    color = Color.White,
                    fontSize = 32.sp
                )
            }
        }

        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 30.dp))
        {
            Button(
                onClick = { onScoreBoardClick() },
                colors = ButtonColors(containerColor = colorResource(id = R.color.navy_blue),
                    contentColor = colorResource(id = R.color.navy_blue), disabledContainerColor = colorResource(id = R.color.navy_blue),
                    disabledContentColor = colorResource(id = R.color.navy_blue)
                ),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.navy_blue),
                        shape = MaterialTheme.shapes.medium
                    )
                    .width(300.dp)
                    .height(100.dp)
            )
            {
                Text(
                    text = "Score Board",
                    color = Color.White,
                    fontSize = 32.sp
                )
            }
        }
    }
}

@Composable
fun TicTacToeAppStart(navController: NavHostController = rememberNavController())
{
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = TTTApp.valueOf(
        backStackEntry?.destination?.route ?: TTTApp.Start.name
    )

    NavHost(
        navController = navController,
        startDestination = TTTApp.Start.name,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        composable(route = TTTApp.Start.name)
        {
            MainWindow(
                onSingleOrMultiPlayerClick = { navigateTo(navController, TTTApp.NewGame.name) },
                onScoreBoardClick = { navigateTo(navController, TTTApp.ScoreBoard.name) }
            )
        }
        composable(route = TTTApp.NewGame.name)
        {
            val context = LocalContext.current
            NewGameWindow(
                onCancelButtonClicked = { navigateTo(navController, TTTApp.Start.name) },
                onNextButtonClicked = { navigateTo(navController, TTTApp.GamePlay.name) }
            )
        }
        composable(route = TTTApp.GamePlay.name)
        {
            TTTScreen(
                gamePlay = GamePlay(),
                onGameEnd = { navigateTo(navController, TTTApp.Start.name) }
            )
        }
        composable(route = TTTApp.ScoreBoard.name)
        {
            val context = LocalContext.current
            ScoreBoardWindow(
                onBackClick = { navigateTo(navController, TTTApp.Start.name) }
            )
        }
    }
}

private fun navigateTo(
    navController: NavHostController,
    name: String
)
{
    navController.navigate(name)
}