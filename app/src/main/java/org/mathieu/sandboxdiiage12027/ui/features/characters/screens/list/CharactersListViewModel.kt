package org.mathieu.sandboxdiiage12027.ui.features.characters.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.mathieu.sandboxdiiage12027.domain.models.Character
import org.mathieu.sandboxdiiage12027.domain.models.charactersMock

class CharactersListViewModel: ViewModel() {


    private val _characters = MutableStateFlow<List<Character>>(
        emptyList()
    )

    val characters: StateFlow<List<Character>>
        get() = _characters


    init {

        viewModelScope.launch(
            context = Dispatchers.IO
        ) {

            _characters.value = getCharacters()

        }

    }


    private suspend fun getCharacters(): List<Character> {

        return charactersMock

    }

}
