package br.com.rodrigo.note.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.rodrigo.note.R

@Composable
fun NotePopup(
    text: String = "",
    onClickDimiss: () -> Unit,
    onClickSave: (String) -> Unit,
) {
    val txtState = rememberSaveable { mutableStateOf(text) }
    Dialog(onDismissRequest = onClickDimiss) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            BasicTextField(
                value = txtState.value,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
                onValueChange = { txt ->
                    txtState.value = txt
                }
            )

            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Button(onClick = onClickDimiss) {
                    Text(text = stringResource(R.string.screen_popup_button_dismiss))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { onClickSave(txtState.value) }) {
                    Text(text = stringResource(R.string.screen_popup_button_save))
                }
            }
        }
    }
}