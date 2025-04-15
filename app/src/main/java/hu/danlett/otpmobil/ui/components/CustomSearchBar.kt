package hu.danlett.otpmobil.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hu.danlett.otpmobil.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
	textFieldState: TextFieldState,
	onSearch: (String) -> Unit
) {
	SearchBar(
		windowInsets = WindowInsets(top = 0.dp),
		modifier = Modifier.fillMaxWidth(),
		inputField = {
			SearchBarDefaults.InputField(
				query = textFieldState.text.toString(),
				onQueryChange = { textFieldState.edit { replace(0, length, it) } },
				onSearch = {
					onSearch(textFieldState.text.toString())
				},
				expanded = false,
				onExpandedChange = { },
				placeholder = { Text(text = stringResource(R.string.search_placeholder)) },
				leadingIcon = {
					Icon(Icons.Default.Search, contentDescription = null)
				}
			)
		},
		expanded = false,
		onExpandedChange = { },
	) {

	}
}