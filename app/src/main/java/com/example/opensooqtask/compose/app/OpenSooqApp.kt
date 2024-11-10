package com.example.opensooqtask.compose.app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.opensooqtask.R
import com.example.opensooqtask.compose.navGraph.OpenSooqNavGraph


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OpenSooqApp() {
    val topBarState = remember { mutableStateOf(TopBarState()) }
    val navController = rememberNavController()
    AppContent(topBarState, navController)
}

@Composable
private fun AppContent(
    topBarState: MutableState<TopBarState>,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBarContent(topBarState.value, navController)
        },
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            OpenSooqNavGraph(navController) { newState ->
                topBarState.value = newState
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    AppContent(
        remember { mutableStateOf(TopBarState(true, "Select Category", Color.White, true, true)) },
        rememberNavController()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarContent(topBarState: TopBarState, navController: NavHostController) {
    if (topBarState.showAppBar) {
        TopAppBar(
            title = { HeaderText(topBarState.title) },
            navigationIcon = {
                TopBarNavigationIcon(
                    topBarState.showBackButton,
                )
                { navController.popBackStack() }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = topBarState.backgroundColor
            )
        )
    }
}

@Composable
private fun TopBarNavigationIcon(showIcon: Boolean, onClick: () -> Unit) {
    if (showIcon) {
        IconButton(onClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "Back",
                modifier = Modifier
                    .size(30.dp)
                    .padding(0.dp)
            )
        }
    }
}

@Composable
private fun HeaderText(text: String) {
    Text(text, fontWeight = FontWeight(700), fontSize = 24.sp)
}

