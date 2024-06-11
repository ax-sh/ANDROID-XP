package me.axm.auth_prototype

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
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
        auth()
    }

    private fun auth() {
        val context = this
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.authResults.collect { result ->
                    when (result) {
                        is AuthResult.Authorized -> {
                            Timber.tag("authResults oauth").i("authorized")
                            // Handle successful authentication, e.g., navigate to the main screen
                        }

                        is AuthResult.UnknownError -> {
                            Timber.tag("authResults oauth").i("UnknownError")
                            Toast.makeText(
                                context,
                                "An unknown error occurred",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                        // Handle other possible states as needed
                        is AuthResult.Unauthorized -> {
                            Toast.makeText(
                                context,
                                "You're not authorized",
                                Toast.LENGTH_LONG
                            ).show()
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
    AuthPrototypeTheme {
        Greeting("Android")
    }
}
