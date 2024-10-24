@file:OptIn(ExperimentalMaterial3Api::class)

package dev.sindrenm.repro.backnavigation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()

    super.onCreate(savedInstanceState)

    setContent {
      MaterialTheme {
        MainScreen()
      }
    }
  }
}

@Composable
internal fun MainScreen() {
  val navController = rememberNavController()

  NavHost(navController, startDestination = HomeScreenRoute) {
    composable<HomeScreenRoute> {
      HomeScreen(openNested = { navController.navigate(NestedScreenRoute) })
    }

    composable<NestedScreenRoute> {
      NestedScreen(navigateUp = { navController.navigateUp() })
    }
  }
}

@Serializable
object HomeScreenRoute

@Serializable
object NestedScreenRoute

@Composable
fun HomeScreen(openNested: () -> Unit, modifier: Modifier = Modifier) {
  Scaffold(
    modifier = modifier,
    topBar = { TopAppBar(title = { Text("Home Screen") }) },
  ) { contentPadding ->
    Button(
      modifier = Modifier
          .padding(contentPadding)
          .fillMaxSize()
          .wrapContentSize(),
      onClick = openNested,
    ) {
      Text("Go forth and navigate")
    }
  }
}

@Composable
fun NestedScreen(
  navigateUp: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Scaffold(
    modifier = modifier,
    topBar = { TopAppBar(title = { Text("Nested Screen") }) },
  ) { contentPadding ->
    Button(
      modifier = Modifier
          .padding(contentPadding)
          .fillMaxSize()
          .wrapContentSize(),
      onClick = navigateUp,
    ) {
      Text("Navigate Up")
    }
  }
}
