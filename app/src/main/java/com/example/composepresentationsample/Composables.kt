package com.example.composepresentationsample

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composepresentationsample.data.FakeContactData
import com.example.composepresentationsample.vm.ContactsViewModel
import com.example.composepresentationsample.model.Contact
import com.example.composepresentationsample.ui.theme.Typography
import com.example.composepresentationsample.vm.DetailsViewModel

@Composable
fun Screen(
    toolbarTitle: String,
    shouldShowBackButton: Boolean = false,
    onNavigationIconClick: (() -> Unit) = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            ComposePlaygroundTopAppBar(
                title = toolbarTitle,
                shouldShowBackButton = shouldShowBackButton,
                onNavigationIconClick = onNavigationIconClick
            )
        }
    ) {
        content()
    }
}

@Composable
fun ComposePlaygroundTopAppBar(
    title: String,
    shouldShowBackButton: Boolean,
    onNavigationIconClick: (() -> Unit) = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title, // ignore hardcoded strings in scope of presentation
                style = Typography.h5,
                modifier = Modifier.padding(start = 4.dp)
            )
        },
        navigationIcon = if (shouldShowBackButton) {
            {
                IconButton(onClick = onNavigationIconClick) {
                    Icon(Icons.Default.ArrowBack, null)
                }
            }
        } else {
            null
        }
    )
}

@ExperimentalFoundationApi
@Composable
fun ContactsScreen(onContactClick: (Int) -> Unit, viewModel: ContactsViewModel) {
    Screen(toolbarTitle = "Contacts") {
        ContactList(
            contactsGroupedByInitial = viewModel.contactsGroupedByInitial,
            favoriteContactIds = viewModel.favoriteContactIds,
            toggleFavorite = viewModel::toggleFavorite,
            onContactClick = onContactClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun DetailsScreen(
    onBackPressed: (() -> Unit),
    contactId: Int,
    viewModel: DetailsViewModel
) {
    Screen(
        toolbarTitle = "Info",
        shouldShowBackButton = true,
        onNavigationIconClick = onBackPressed
    ) {
        ContactInfo(
            contact = viewModel.getContactInfo(contactId = contactId),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ContactInfo(contact: Contact, modifier: Modifier) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(24.dp)
                .background(MaterialTheme.colors.primarySurface, CircleShape)
                .size(120.dp)
        ) {
            Text(
                text = contact.initials,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h2.copy(fontWeight = FontWeight.Light)
            )
        }
        Text(
            text = "${contact.firstName} ${contact.lastName}",
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = contact.number,
            style = MaterialTheme.typography.body1.copy(fontStyle = FontStyle.Italic)
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun ContactList(
    contactsGroupedByInitial: Map<Char, List<Contact>>,
    favoriteContactIds: LiveData<Set<Int>>,
    toggleFavorite: (Int) -> Unit,
    onContactClick: (Int) -> Unit,
    modifier: Modifier
) {
    LazyColumn(modifier = modifier.background( MaterialTheme.colors.secondarySurface)) {
        contactsGroupedByInitial.forEach { (initialChar, contacts) ->

            stickyHeader {
                InitialCharHeader(
                    char = initialChar,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .background(color = MaterialTheme.colors.secondarySurface)
                )
            }

            itemsIndexed(contacts) { index, contact ->
                val background = MaterialTheme.colors.background
                ContactItem(
                    contact = contact,
                    favoriteContactIds = favoriteContactIds,
                    onFavoriteToggle = toggleFavorite,
                    onClick = onContactClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .backgroundWithRoundedCorners(
                            color = background,
                            radius = 6.dp,
                            currentItem = index,
                            totalItems = contacts.size
                        )
                )

                if (index != contacts.size - 1) {
                    Divider()
                }
            }
        }
    }
}

class CounterViewModel: ViewModel() {

    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> = _counter

    fun increaseCounter() {
        _counter.value = (_counter.value  ?: 0) + 1
    }
}

@Composable
fun Counter(
    counterViewModel: CounterViewModel
) {
    val counter: State<Int?> = counterViewModel.counter.observeAsState()

    Row {
        Text(text = "Counter=${counter.value}")
        Button(onClick = { counterViewModel.increaseCounter() }) {
            Text(text = "increase")
        }
    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
fun Previnit() {
    Counter(CounterViewModel())
}

@Composable
fun InitialCharHeader(char: Char, modifier: Modifier = Modifier) {
    Row(modifier = modifier.background(MaterialTheme.colors.secondarySurface)) {
        Text(
            text = char.toString(),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .background(color = MaterialTheme.colors.background, shape = CircleShape)
                .size(20.dp),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun ContactItem(
    contact: Contact,
    favoriteContactIds: LiveData<Set<Int>>,
    onFavoriteToggle: (Int) -> Unit,
    onClick: (Int) -> Unit,
    modifier: Modifier
) {
    val favorites by favoriteContactIds.observeAsState()

    Row(modifier = modifier.clickable(onClick = { onClick(contact.id) })) {
        Text(
            text = "${contact.firstName} ${contact.lastName}",
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        FavoriteButton(
            isFavorite = contact.id in favorites ?: emptySet(),
            onClick = { onFavoriteToggle(contact.id) }
        )
    }
}

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = { onClick() },
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null, // ignore for the context of this presentation
            tint = Color.Red
        )
    }
}

fun Modifier.backgroundWithRoundedCorners(
    color: Color,
    radius: Dp,
    currentItem: Int,
    totalItems: Int
) = this.then(
    other = when {
        totalItems == 1 -> background(color = color, shape = RoundedCornerShape(radius))
        currentItem == 0 -> background(
            color = color,
            shape = RoundedCornerShape(topStart = radius, topEnd = radius)
        )
        currentItem == totalItems - 1 -> background(
            color = color,
            shape = RoundedCornerShape(bottomStart = radius, bottomEnd = radius)
        )
        else -> background(color = color)
    }
)

val Colors.secondarySurface: Color get() = if (isLight) secondary else surface