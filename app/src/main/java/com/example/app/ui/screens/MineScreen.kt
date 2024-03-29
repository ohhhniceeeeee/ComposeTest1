package com.example.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Battery2Bar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.R
import com.example.app.ui.components.TopAppBar
import androidx.compose.material.icons.automirrored.filled.ArrowRight

@Composable
fun MineScreen(statusBarHeight: Int) {

    val menus = listOf(
        MenuItem(icon = Icons.Filled.AccessAlarm, title = "学习积分"),
        MenuItem(icon = Icons.Filled.AddShoppingCart, title = "浏览积分"),
        MenuItem(icon = Icons.Filled.Apartment, title = "学习档案"),

        MenuItem(icon = Icons.Filled.Accessibility, title = "常见问题"),
        MenuItem(icon = Icons.Filled.Battery2Bar, title = "版本信息"),
        MenuItem(icon = Icons.Filled.Adb, title = "个人设置"),
        MenuItem(icon = Icons.Filled.Analytics, title = "联系我们")
    )

    Column(modifier = Modifier) {
        TopAppBar(statusBarHeight = statusBarHeight) {
            Text(text = "我的页面", fontSize = 18.sp, color = Color.White)
        }
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            //头像部分
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 32.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.zombie),
                        contentDescription = null,
                        modifier = Modifier
                            .size(62.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .height(62.dp)
                    ) {
                        Text(text = "未登录", color = Color(0xFF333333), fontSize = 18.sp)
                        Text(text = "已坚持学习0天", color = Color(0xFF999999), fontSize = 12.sp)
                    }
                }
            }

            //菜单部分

            itemsIndexed(menus){index: Int, menu: MenuItem ->

                if (index == 3){
                    Spacer(modifier = Modifier.height(16.dp).background(Color(0xFFF5F5F5)))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = menu.icon,
                        contentDescription = menu.title,
                        tint = Color(0xFF149EE7),
                        modifier = Modifier.size(17.dp)
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = menu.title, color = Color(0xFF333333), fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(13.dp)
                            )
                        }
                        HorizontalDivider()
                    }
                }
            }

        }
    }
}

data class MenuItem(val icon: ImageVector, val title: String)