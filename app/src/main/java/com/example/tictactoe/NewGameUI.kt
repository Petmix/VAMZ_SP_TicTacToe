package com.example.tictactoe

import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewGameWindow(
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {}
)
{
    val image = painterResource(R.drawable.newgamebackground)
    var p1Name by remember { mutableStateOf("") }
    var p2Name by remember { mutableStateOf("") }
    var goesFirst by remember { mutableIntStateOf(0) }
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
                .height(600.dp)
                .padding(0.dp, 0.dp, 0.dp, 100.dp)
                .background(
                    color = colorResource(id = R.color.light_blue),
                    shape = MaterialTheme.shapes.medium
                )
        )
        {
            TextField(
                value = p1Name,
                onValueChange = { p1Name = it },
                modifier = Modifier
                    .padding(30.dp, 50.dp, 0.dp, 0.dp)
                    .width(320.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                label = { Text(stringResource(R.string.name)) }
            )

            TextField(
                value = p2Name,
                onValueChange = { p2Name = it },
                modifier = Modifier
                    .padding(30.dp, 130.dp, 0.dp, 0.dp)
                    .width(320.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Go),
                label = { Text(stringResource(R.string.name)) }
            )

            Image(
                painter = painterResource(id = R.drawable.woodenx),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .scale(1.3f, 1.3f)
                    .padding(0.dp, 0.dp, 15.dp, 265.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.throwrings),
                contentDescription = null,
                modifier = Modifier
                    .padding(265.dp, 105.dp, 0.dp, 0.dp)
                    .scale(0.86f, 0.86f)
            )

            Text(
                text = "Who starts first?",
                color = colorResource(id = R.color.dark_navy_blue),
                modifier = Modifier.padding(80.dp, 230.dp, 0.dp, 0.dp),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
            )
            {
                val colorNotSelected = colorResource(id = R.color.light_blue)
                val colorSelected = colorResource(id = R.color.navy_blue)
                Button(
                    onClick = { goesFirst = 1 },
                    colors = ButtonColors(containerColor = colorNotSelected,
                        contentColor = colorSelected, disabledContainerColor = colorNotSelected,
                        disabledContentColor = colorNotSelected
                    ),
                    modifier = Modifier
                        .background(
                            color = colorNotSelected,
                            shape = MaterialTheme.shapes.medium
                        )
                        .width(100.dp)
                        .height(100.dp)
                )
                {
                    Image(painter = painterResource(id = R.drawable.woodenx), contentDescription = null)
                }

                Button(
                    onClick = { goesFirst = 2 },
                    colors = ButtonColors(containerColor = colorNotSelected,
                        contentColor = colorSelected, disabledContainerColor = colorNotSelected,
                        disabledContentColor = colorNotSelected
                    ),
                    modifier = Modifier
                        .background(
                            color = colorNotSelected,
                            shape = MaterialTheme.shapes.medium
                        )
                        .width(100.dp)
                        .height(100.dp)
                )
                {
                    Image(painter = painterResource(id = R.drawable.throwrings), contentDescription = null)
                }

                Button(
                    onClick = { goesFirst = 0 },
                    colors = ButtonColors(containerColor = colorNotSelected,
                        contentColor = colorSelected, disabledContainerColor = colorNotSelected,
                        disabledContentColor = colorNotSelected
                    ),
                    modifier = Modifier
                        .background(
                            color = colorNotSelected,
                            shape = MaterialTheme.shapes.medium
                        )
                        .width(100.dp)
                        .height(100.dp)
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.both),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.dp, 5.dp, 0.dp, 0.dp)
                    )
                }
            }
        }
    }
}