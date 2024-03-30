package com.example.app.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.model.entity.NavigationItem
import com.google.accompanist.insets.ProvideWindowInsets

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MainFrame(
    statusBarHeight: Int,
    onNavigateToArticle: () -> Unit
) {

    val navigationItems = listOf(
        NavigationItem(title = "学习", icon = Icons.Filled.Home),
        NavigationItem(title = "任务", icon = Icons.Filled.DateRange),
        NavigationItem(title = "我的", icon = Icons.Filled.Person)
    )

    var currentNavigationIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier.navigationBarsPadding()
            ) {
                navigationItems.forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = currentNavigationIndex == index,
                        onClick = {
                            currentNavigationIndex = index
                        },
                        icon = {
                            Icon(
                                imageVector = navigationItem.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = navigationItem.title)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF149EE7),
                            selectedTextColor = Color(0xFF149EE7),
                            unselectedIconColor = Color(0xFF999999),
                            unselectedTextColor = Color(0xFF999999)
                        )
                    )
                }
            }
        },
//        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentNavigationIndex) {
                0 -> StudyScreen(statusBarHeight,onNavigateToArticle = onNavigateToArticle)
                1 -> TaskScreen()
                2 -> MineScreen(statusBarHeight)
            }
        }
    }
}

