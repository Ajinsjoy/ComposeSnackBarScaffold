

## Introduction 🙋‍♂️

The release of the Snackbar Scaffold library for Android Jetpack Compose! This library simplifies the implementation and management of snackbars within your Compose applications, offering a robust and customizable solution for displaying transient messages.

Key Features:

* Integrated Snackbar Support: Easily integrate snackbars into your app using a prebuilt scaffold that handles display and dismissal logic.
* Action Support: Add interactive actions to your snackbars with intuitive API support


## How to Add📚

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	dependencyResolutionManagement {
	  	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	 	repositories {
	  		mavenCentral()
	   		maven ( url= "https://jitpack.io" )
	  	}
	}

Step 2. Add the dependency

	dependencies {
	        implementation ("com.github.Ajinsjoy:ComposeSnackBarScaffold:1.0.2")
	}

## How to Use📚

Step 1. Wrapping the Root Composable with SnackBarScaffold

When creating a user interface in Jetpack Compose, the SnackBarScaffold provides a structured way to display Snackbar messages and manage other UI components. Wrapping your root composable with a SnackBarScaffold allows you to add a Snackbar to your application while maintaining a clean separation of concerns.

   	 SnackBarScaffold(Modifier ) {
                       // Your UI composables go here
                }

Step 2. Sending Snackbar Events with SnackbarController

SnackbarController provides a structured way to send Snackbar events. This allows you to show messages or trigger specific actions when interacting with a Snackbar. Here's how to use it effectively.
 
# Sending a Basic Snackbar Event

  	SnackbarController.sendEvent(event = SnackBarEvent("Message"))

Explanation:

* SnackbarController: A controller responsible for managing Snackbar events.
* SnackBarEvent: Represents an event that contains the message to be displayed.
* This sends a basic Snackbar event that simply shows the provided message.

# Sending a Snackbar Event with an Action
If you need the Snackbar to include an actionable button, you can use the following approach:

	SnackbarController.sendEvent(
            event = SnackBarEvent(
                message= message,
                action = SnackBarAction(
                    name = "click",
                    action = {
                        SnackbarController.sendEvent(SnackBarEvent("Snack bar action"))
                    }
                )
            )
        )


Explanation:

* SnackBarEvent: Contains the message and optionally includes an action.
* SnackBarAction:
	* name: The text displayed on the action button (e.g., "Click").
	* action: A lambda function that is executed when the action button is clicked.
 * 
In this example, clicking the Snackbar action button triggers another SnackbarEvent with a new message.

## Key Benefits
* Snackbar Management: Simplifies adding and managing Snackbar messages in your application.
* Centralized Layout: Encourages a consistent UI structure by acting as a top-level container.
