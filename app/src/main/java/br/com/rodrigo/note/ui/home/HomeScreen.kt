package br.com.rodrigo.note.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.rodrigo.note.R
import br.com.rodrigo.note.models.Note
import br.com.rodrigo.note.viewmodels.IHomeViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

private enum class PopupState {
    Open, Edit, Close
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: IHomeViewModel
) {
    val popupState = rememberSaveable { mutableStateOf(PopupState.Close) }
    val noteSelected = rememberSaveable { mutableStateOf<Note?>(null) }
    val noteListState = homeViewModel.noteList.collectAsState(initial = listOf())


    Scaffold {
        LazyColumn {
            items(noteListState.value.size) {
                val note = noteListState.value[it]

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .clickable {
                            noteSelected.value = note
                            popupState.value = PopupState.Edit
                        }
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    homeViewModel.deleteNote(note)
                                }
                            )
                        }
                ) {
                    Text(
                        text = note.text,
                        maxLines = 1,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 16.dp, end = 16.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .height(0.5.dp)
                            .fillMaxWidth()
                            .background(color = Color.Gray.copy(alpha = 0.54f))
                            .align(Alignment.BottomCenter)
                    )
                }
            }
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier.align(Alignment.Center),
                        onClick = {
                            popupState.value = PopupState.Open
                        }
                    ) {
                        Text(
                            stringResource(R.string.screen_home_button_add_note)
                        )
                    }
                }
            }
        }

        when (popupState.value) {
            PopupState.Open -> {
                NotePopup(
                    onClickDimiss = {
                        popupState.value = PopupState.Close
                    },
                    onClickSave = {
                        homeViewModel.addNote(note = Note(text = it))
                        popupState.value = PopupState.Close
                    }
                )
            }
            PopupState.Edit -> {
                noteSelected.value?.let { note ->
                    NotePopup(
                        text = note.text,
                        onClickDimiss = {
                            popupState.value = PopupState.Close
                        },
                        onClickSave = {
                            homeViewModel.updateNote(
                                note = Note(id = note.id, text = it)
                            )
                            popupState.value = PopupState.Close
                        }
                    )
                }
            }
            PopupState.Close -> {

            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        homeViewModel = object : IHomeViewModel {
            override val noteList: Flow<List<Note>>
                get() = flowOf(
                    listOf(
                        Note(text = "note 1"),
                        Note(text = "note 2"),
                        Note(text = "note 3"),
                        Note(text = "note 4"),
                        Note(text = "note 5"),
                    )
                )

            override fun addNote(note: Note) {
                TODO("Not yet implemented")
            }

            override fun updateNote(note: Note) {
                TODO("Not yet implemented")
            }

            override fun deleteNote(note: Note) {
                TODO("Not yet implemented")
            }
        }
    )
}