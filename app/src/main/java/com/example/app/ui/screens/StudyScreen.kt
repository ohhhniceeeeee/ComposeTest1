package com.example.app.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.components.TopAppBar
import com.example.app.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.app.model.entity.VideoEntity
import com.example.app.ui.components.ArticleItem
import com.example.app.ui.components.NotificationContent
import com.example.app.ui.components.SwiperContent
import com.example.app.ui.components.VideoItem
import com.example.app.ui.theme.Blue700
import com.example.app.viewmodel.ArticleViewModel
import com.example.app.viewmodel.VideoViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StudyScreen(
    statusBarHeight: Int,
    viewModel: MainViewModel = viewModel(),
    articleViewModel: ArticleViewModel = viewModel(),
    videoViewModel: VideoViewModel = viewModel(),
    onNavigateToArticle: () -> Unit
) {
    Column(modifier = Modifier) {

        //标题栏
        TopAppBar(
            statusBarHeight = statusBarHeight,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {

            //搜索按钮
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .weight(1f),
                color = Color(0x33ffffff)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "搜索感兴趣的资讯或课程",
                        fontSize = 12.sp,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            //学习进度
            Text(
                text = "学习\n进度",
                fontSize = 10.sp,
                color = Color.White
            )
            Text(text = "26%", fontSize = 12.sp, color = Color.White)
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.White
            )
        }
        //分类标签
        TabRow(
            selectedTabIndex = viewModel.categoryIndex,
            containerColor = Color(0x22149EE7),
            contentColor = Color(0xFF149EE7)
        ) {
            viewModel.categories.forEachIndexed { index, category ->
                Tab(
                    selected = viewModel.categoryIndex == index,
                    onClick = {
                        viewModel.updateCategoryIndex(index)
                    },
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF666666)
                ) {
                    Text(
                        text = category.title,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 14.sp
                    )
                }

            }

        }

        //类型标签
        TabRow(
            selectedTabIndex = viewModel.currentTypeIndex,
            containerColor = Color.Transparent,
            contentColor = Color(0xFF149EE7),
            indicator = {},
            divider = {}
        ) {
            viewModel.types.forEachIndexed { index, item ->
                LeadingIconTab(
                    selected = viewModel.currentTypeIndex == index,
                    onClick = { viewModel.updateTypeIndex(index) },
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF666666),
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = null)
                    },
                    text = {
                        Text(
                            text = item.title,
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontSize = 16.sp
                        )
                    }
                )
            }
        }

        LazyColumn() {
            //轮播图
            item { SwiperContent(vm = viewModel) }

            //通知事项
            item { NotificationContent(viewModel) }


            if (viewModel.currentTypeIndex == 0) {
                //文章列表
                items(articleViewModel.list) { article ->
                    ArticleItem(articleEntity = article, modifier = Modifier.clickable {
                        onNavigateToArticle()
                    })
                }
            } else {
                //视频列表
                items(videoViewModel.list) { videoEntity ->
                    VideoItem(videoEntity)
                }
            }
        }


    }
}

