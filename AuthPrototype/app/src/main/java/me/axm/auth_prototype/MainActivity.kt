package me.axm.auth_prototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn
import me.axm.auth_prototype.auth.AuthResult
import me.axm.auth_prototype.auth.AuthViewModel
import me.axm.auth_prototype.ui.theme.AuthPrototypeTheme
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuthPrototypeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
//        Add logic for livedata

        // Use launchWhenStarted to collect flows in a lifecycle-aware manner
        lifecycleScope.launchWhenResumed  {
            viewModel.authResults.collect { result ->
                when (result) {
                    is AuthResult.Authorized -> {
                        Timber.tag("oauth").i("authorized")
                        // Handle successful authentication, e.g., navigate to the main screen
                    }
                    is AuthResult.UnknownError -> {
                        Timber.tag("oauth").i("UnknownError")
                        // Handle authentication error, e.g., show an error message
                    }
                    // Handle other possible states as needed
                    is AuthResult.Unauthorized -> TODO()
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
    AuthPrototypeTheme {
        Greeting("Android")
    }
}
