package io.vishpraveen.democompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            Greeting()
        }
    }
    
}


@Composable
private fun Greeting() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.teal_700))) {
        Text(text = "hello")
    }
}

@Preview
@Composable
fun PreviewGreeting() {
    Greeting()
}