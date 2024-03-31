package com.example.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.Blue700
import androidx.compose.material.icons.automirrored.filled.NavigateBefore
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ui.components.WebViewCompose
import com.example.app.ui.components.rememberWebViewState
import com.example.app.viewmodel.ArticleViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(articleViewModel: ArticleViewModel = viewModel(), onBack: () -> Unit) {

    val webViewState = rememberWebViewState(data = articleViewModel.content)


    var fontScale by remember {
        mutableStateOf(1.0f)
    }

    val scaffoldState = rememberBottomSheetScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "文章详情",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue700,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.NavigateBefore,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                onBack()
                            }
                            .padding(8.dp),
                        tint = Color.White
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.TextFields,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    if (scaffoldState.bottomSheetState.isVisible) {
                                        scaffoldState.bottomSheetState.expand()
                                    }

                                }
                            }
                            .padding(8.dp),
                        tint = Color.White
                    )
                }
            )
        },
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 56.dp)
            ) {
                Text(text = "字体大小", fontSize = 16.sp)
                Slider(
                    value = fontScale,
                    onValueChange = {
                        fontScale = it
                        webViewState.evaluateJavascript("document.body.style.zoom = $it")
                    },
                    steps = 3, valueRange = 0.75f..1.75f,
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "较小", fontSize = 14.sp, color = Color(0xFF999999))//0.75f
                    Text(text = "标准", fontSize = 14.sp, color = Color(0xFF999999))//1.0f
                    Text(text = "普大", fontSize = 14.sp, color = Color(0xFF999999))//1.25f
                    Text(text = "超大", fontSize = 14.sp, color = Color(0xFF999999))//1.5f
                    Text(text = "特大", fontSize = 14.sp, color = Color(0xFF999999))//1.75f
                }
            }
        }

    ) { it ->
        Column(modifier = Modifier.padding(it)) {
            WebViewCompose(webViewState)
        }
    }
}