package com.example.m1cta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.m1cta.ui.theme.M1CTATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            M1CTATheme {
                Wrapper()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello, $name!",
        style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Composable
fun Wrapper() {
    Scaffold(modifier = Modifier.fillMaxSize() ) {
        innerPadding ->
        androidx.compose.foundation.layout.Box(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Greeting(
                name = "Android",
            )
        }

    }
}

