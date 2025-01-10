

## Introduction 🙋‍♂️

The release of the Snackbar Scaffold library for Android Jetpack Compose! This library simplifies the implementation and management of snackbars within your Compose applications, offering a robust and customizable solution for displaying transient messages.

Key Features:

* Integrated Snackbar Support: Easily integrate snackbars into your app using a prebuilt scaffold that handles display and dismissal logic.
* Action Support: Add interactive actions to your snackbars with intuitive API support


## How to 📚

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

