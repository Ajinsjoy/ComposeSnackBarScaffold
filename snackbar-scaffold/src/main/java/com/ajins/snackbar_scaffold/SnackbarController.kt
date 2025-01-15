package com.ajins.snackbar_scaffold

import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

data class SnackBarEvent(
    val message: String,
    val leadingIcon:ImageVector?=null,
    val duration: SnackbarDuration = SnackbarDuration.Short,
    val action: SnackBarAction? = null,
    val showDismissAction: Boolean=false,
)


data class SnackBarAction(
    val name: String?=null,
    val actionIcon:ImageVector?=null,
    val action: suspend () -> Unit,
)


object SnackbarController {

    private val _events = Channel<SnackBarEvent>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: SnackBarEvent) {
        _events.send(event)
    }

}