package me.axm.buildconfigtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.axm.buildconfigtest.ui.theme.BuildConfigTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuildConfigTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Row {
                        Row {
                            Text(
                                text = BuildConfig.BASE_URL,
                                modifier = Modifier.padding(innerPadding),
                            )
                        }
                        Row {
                            Text(text = "  ")
                        }
                        Row {
                            Text(
                                text = getString(R.string.base_url),
                                modifier = Modifier.padding(innerPadding),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BuildConfigTestTheme {
        Greeting("Android")
    }
}
