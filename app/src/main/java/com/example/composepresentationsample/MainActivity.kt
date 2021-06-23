package com.example.composepresentationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.composepresentationsample.vm.ContactsViewModel
import com.example.composepresentationsample.ui.theme.ComposePresentationSampleTheme
import com.example.composepresentationsample.vm.DetailsViewModel

class MainActivity : ComponentActivity() {

    private val contactsViewModel: ContactsViewModel by viewModels()
    private val detailsViewModel: DetailsViewModel by viewModels()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePresentationSampleTheme {
                ContactsApp(contactsVM = contactsViewModel, detailsVM = detailsViewModel)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun ContactsApp(contactsVM: ContactsViewModel, detailsVM: DetailsViewModel) {
    Surface {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "contacts") {
            composable("contacts") {
                ContactsScreen(
                    onContactClick = { navController.navigate("details/$it") },
                    viewModel = contactsVM
                )
            }
            composable(
                "details/{contactId}",
                arguments = listOf(navArgument("contactId") { type = NavType.IntType })
            ) {
                DetailsScreen(
                    onBackPressed = { navController.popBackStack() },
                    contactId = it.arguments!!.getInt("contactId"),
                    viewModel = detailsVM
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(name = "Light theme", showBackground = true, heightDp = 600, showSystemUi = false)
@Composable
fun LightPreview() {
    ComposePresentationSampleTheme(darkTheme = true) {
        ContactsApp(contactsVM = ContactsViewModel(), detailsVM = DetailsViewModel())
    }
}

@ExperimentalFoundationApi
@Preview(name = "Dark theme", showBackground = true, heightDp = 600, showSystemUi = false)
@Composable
fun DarkPreview() {
    ComposePresentationSampleTheme(darkTheme = true) {
        ContactsApp(contactsVM = ContactsViewModel(), detailsVM = DetailsViewModel())
    }
}