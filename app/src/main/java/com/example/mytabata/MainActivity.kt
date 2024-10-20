package com.example.mytabata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytabata.ui.theme.MyTabataTheme

var estado : Boolean = false

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTabataTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Counter(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier) {
    var theCounter by remember { mutableStateOf(0L) }
    val miConterDown by remember { mutableStateOf(CountDown(10,10) { newvalue -> theCounter = newvalue }) }

    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = theCounter.toString(),
                fontSize = 48.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = {
                    miConterDown.toggle()
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "pulsar",
                    fontSize = 24.sp
                )
            }
        }
    }
}



