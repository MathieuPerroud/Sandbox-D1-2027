package org.mathieu.sandboxdiiage12027.ui.features.characters.screens.list


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.mathieu.sandboxdiiage12027.R
import org.mathieu.sandboxdiiage12027.domain.models.Character
import org.mathieu.sandboxdiiage12027.domain.models.charactersMock
import org.mathieu.sandboxdiiage12027.ui.composables.CharacterCard
import org.mathieu.sandboxdiiage12027.ui.composables.characterCardMinWidthRequired
import kotlin.math.floor

/**
 * Function:
 * Displays a list of characters using different card layouts based on the screen's width.
 *
 * Specific Features:
 * - Three types of width-based layouts:
 *   a. A single card with horizontal stacking.
 *   b. Multiple cards with horizontal stacking.
 *   c. A single card with vertical stacking.
 *
 * - The layout adapts dynamically to the screen width:
 *
 *   a. Case (a): Triggered if the screen width is at least 420dp.
 *
 *   b. Case (b): Triggered if the screen width is greater than n times the width required for (a).
 *
 *   c. Case (c): Triggered if the screen width is less than the width required for (a).
 *
 * @param navController Navigation controller for managing navigation between screens.
 */
@Composable
fun CharactersListScreen(navController: NavController) {

    val viewModel: CharactersListViewModel = viewModel()

    val characters by viewModel.characters.collectAsState()

    CharactersListContent(
        characters = characters,
        clickedOnCharacter = { character ->
            navController.navigate("character_details/${character.id}")
        }
    )

}

@Composable
private fun CharactersListContent(
    characters: List<Character>,
    clickedOnCharacter: (Character) -> Unit
) {

    // Récupération de la largeur de l'écran en dp
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.background)
            ),
        columns = GridCells.Fixed(
            floor(
                screenWidthDp / characterCardMinWidthRequired
            ).toInt().takeIf { it > 0 } ?: 1
        )
    ) {
        items(characters) { character ->
            Box(
                contentAlignment = Alignment.Center
            ) {

                CharacterCard(
                    character = character,
                    clickedOnCard = {
                        clickedOnCharacter(character)
                    }
                )

            }
        }
    }
}



@Preview(name = "VerticalCard should be displayed in a vertical scroll orientation", device = Devices.NEXUS_6P, showSystemUi = true)
@Composable
private fun CharacterCardTooTightPreview() =
    Box(
        modifier = Modifier
            .background(colorResource(R.color.background))
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        CharactersListContent(
            characters = charactersMock,
            clickedOnCharacter = { }
        )
    }

@Preview(name = "HorizontalCard should be displayed in a grid", device = Devices.NEXUS_10, showSystemUi = true)
@Composable
private fun CharacterCardWideScreenPreview() =
    Box(
        modifier = Modifier
            .background(colorResource(R.color.background))
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        CharactersListContent(
            characters = charactersMock,
            clickedOnCharacter = { }
        )
    }
