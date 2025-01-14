package com.ajins.composelibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ajins.composelibrary.ui.theme.ComposeLibraryTheme
import com.ajins.snackbar_scaffold.SnackBarAction
import com.ajins.snackbar_scaffold.SnackBarEvent
import com.ajins.snackbar_scaffold.SnackBarScaffold
import com.ajins.snackbar_scaffold.SnackbarController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLibraryTheme {
                val scope = rememberCoroutineScope()
                SnackBarScaffold {
                    val context = LocalContext.current
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(
                            modifier = Modifier,
                            onClick = {
                                scope.launch {
                                    SnackbarController.sendEvent(
                                        event = SnackBarEvent(
                                            message = "Sample Snackbar ",
                                            leadingIcon = null,
                                            duration = SnackbarDuration.Short,
                                            action = SnackBarAction(
                                                name = "click"
                                            ) {
                                                SnackbarController.sendEvent(
                                                    event = SnackBarEvent(
                                                        message = "Action performed",
                                                    )
                                                )
                                            },
                                            showDismissAction = false
                                        )
                                    )
                                }
                            }) {
                            Text(text = "Show Snackbar")
                        }
                    }
                }
            }
        }
    }
}

