package com.ajins.composelibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ajins.composelibrary.ui.theme.ComposeLibraryTheme
import com.ajins.snackbar_scaffold.SnackBarAction
import com.ajins.snackbar_scaffold.SnackBarEvent
import com.ajins.snackbar_scaffold.SnackBarScaffold
import com.ajins.snackbar_scaffold.SnackbarController
import com.ajins.snackbar_scaffold.utils.SnackBarStyle
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLibraryTheme {
                val scope = rememberCoroutineScope()
                SnackBarScaffold(
                    snackBarStyle = SnackBarStyle(
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        actionColor = MaterialTheme.colorScheme.primary,
                        leadingIconColor = MaterialTheme.colorScheme.primary,
                        dismissActionContentColor = MaterialTheme.colorScheme.error,
                        textStyle = MaterialTheme.typography.titleMedium,
                        shape = CircleShape
                    )

                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(
                            modifier = Modifier,
                            onClick = {
                                scope.launch {
                                    SnackbarController.sendEvent(
                                        event = SnackBarEvent(
                                            message = "Sample Snackbar ",
                                            duration = SnackbarDuration.Short,
                                            action = SnackBarAction(
                                                actionIcon = Icons.Outlined.Check,

                                            ) {
                                                SnackbarController.sendEvent(
                                                    event = SnackBarEvent(
                                                        message = "Action performed",
                                                    )
                                                )
                                            },
                                            showDismissAction = true
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

