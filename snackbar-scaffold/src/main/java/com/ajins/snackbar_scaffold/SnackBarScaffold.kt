package com.ajins.snackbar_scaffold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ajins.snackbar_scaffold.utils.ObserveAsEvents
import com.ajins.snackbar_scaffold.utils.SnackBarStyle
import kotlinx.coroutines.launch

@Composable
fun SnackBarScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    snackBarStyle: SnackBarStyle = SnackBarStyle(
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        actionColor = MaterialTheme.colorScheme.primary,
        leadingIconColor = MaterialTheme.colorScheme.primary,
        dismissActionContentColor = MaterialTheme.colorScheme.error,
        textStyle = MaterialTheme.typography.titleMedium,

        ),
    content: @Composable (PaddingValues) -> Unit
) {
    var leadingIcon by remember { mutableStateOf<ImageVector?>(null) }
    var actionIcon by remember { mutableStateOf<ImageVector?>(null) }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    ObserveAsEvents(
        flow = SnackbarController.events,
        key1 = null,
        key2 = null
    ) { event ->
        scope.launch {

            snackbarHostState.currentSnackbarData?.dismiss()
            leadingIcon = event.leadingIcon
            actionIcon = event.action?.actionIcon
            val result = snackbarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.name,
                duration = event.duration,
                withDismissAction = event.showDismissAction
            )
            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(snackBarStyle.padding)
            ) {

                snackbarHostState.currentSnackbarData?.let { data ->
                    Snackbar(
                        modifier = Modifier,
                        contentColor = snackBarStyle.contentColor,
                        containerColor = snackBarStyle.containerColor,
                        shape = snackBarStyle.shape,
                        actionContentColor = snackBarStyle.actionContentColor,
                        actionOnNewLine = snackBarStyle.actionOnNewLine,
                        dismissActionContentColor = snackBarStyle.dismissActionContentColor,
                        action = {

                            data.visuals.actionLabel?.let { actionLabel ->
                                Text(
                                    text = actionLabel,
                                    style = snackBarStyle.textStyle.copy(
                                        color = snackBarStyle.actionColor
                                    ),
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .clickable {
                                            if (actionLabel.isNotBlank())
                                                data.performAction()
                                        }
                                )
                            } ?: actionIcon?.let { icon ->
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    tint = snackBarStyle.actionColor,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .size(24.dp)
                                        .clickable {
                                            data.performAction()
                                        }
                                )
                            }

                        },
                        dismissAction = {
                            if (data.visuals.withDismissAction)
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = null,
                                    tint = snackBarStyle.dismissActionContentColor,
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                        .size(24.dp)
                                        .clickable {
                                            data.dismiss()
                                        }
                                )

                        }
                    ) {
                        Row(
                            modifier = Modifier.wrapContentWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            leadingIcon?.let { icon ->
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "leading icon",
                                    tint = snackBarStyle.leadingIconColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = data.visuals.message,
                                style = snackBarStyle.textStyle.copy(
                                    color = snackBarStyle.contentColor
                                ),
                            )

                        }
                    }
                }
            }
        },
        content = content
    )
}