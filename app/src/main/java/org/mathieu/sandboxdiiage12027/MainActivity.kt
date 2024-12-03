package org.mathieu.sandboxdiiage12027

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.mathieu.sandboxdiiage12027.ui.features.characters.screens.details.CharacterDetailsScreen
import org.mathieu.sandboxdiiage12027.ui.features.characters.screens.list.CharactersListScreen
import org.mathieu.sandboxdiiage12027.ui.theme.SandboxDiiage12027Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            SandboxDiiage12027Theme {

                NavHost(
                    navController = navController,
                    startDestination = "characters_list"
                ) {

                    composable(
                        route = "characters_list"
                    ) {

                        CharactersListScreen(
                            navController = navController
                        )

                    }

                    composable(
                        // destination part
                        route = "character_details/{character_id}",
                        arguments = listOf(
                            navArgument("character_id") {
                                type = NavType.IntType
                            }
                        )
                    ) { navBackstackEntry ->

                        // BackStackEntry
                        val characterId = navBackstackEntry.arguments?.getInt("character_id")

                        if (characterId == null) {
                            navController.popBackStack()
                            Log.d("characters_details", "Should have character id")
                            return@composable
                        }


                        // Canvas drawing
                        CharacterDetailsScreen(
                            navController = navController,
                            characterId = characterId
                        )

                    }

                }

            }
        }
    }
}
