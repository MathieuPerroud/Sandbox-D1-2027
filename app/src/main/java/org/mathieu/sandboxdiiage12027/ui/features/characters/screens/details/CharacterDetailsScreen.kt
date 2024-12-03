package org.mathieu.sandboxdiiage12027.ui.features.characters.screens.details

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CharacterDetailsScreen(
    navController: NavController,
    characterId: Int
) {

    Button(
        onClick = {
            navController.navigate("characters_list")
        },
        content = {
            Text("Click me")
        }
    )

    Text("$characterId")

}

