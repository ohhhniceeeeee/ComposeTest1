package com.example.app.viewmodel

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.app.R
import com.example.app.model.entity.Category
import com.example.app.model.entity.DataType
import com.example.app.model.entity.SwiperEntity

class MainViewModel : ViewModel() {

    //分类数据
    val categories by mutableStateOf(
        listOf(
            Category("思想政治"),
            Category("法律法规"),
            Category("职业道德"),
            Category("诚信自律")
        )
    )

    //当前分类下标
    var categoryIndex by mutableStateOf(0)
        private set

    //更新分类下标
    fun updateCategoryIndex(index: Int) {
        categoryIndex = index
    }

    //类型数据
    val types by mutableStateOf(
        listOf(
            DataType(title = "相关资讯", icon = Icons.Default.Description),
            DataType(title = "视频课程", icon = Icons.Default.SmartDisplay)
        )
    )

    //当前类型下标
    var currentTypeIndex by mutableStateOf(0)
        private set


    fun updateTypeIndex(index: Int) {
        currentTypeIndex = index
    }

    //轮播图数据
    val swiperData = listOf(
//        SwiperEntity("10.0.2.2/zombie.jpg"),
//        SwiperEntity("10.0.2.2/zombie.jpg"),
//        SwiperEntity("10.0.2.2/zombie.jpg"),
//        SwiperEntity("10.0.2.2/zombie.jpg"),
//        SwiperEntity("10.0.2.2/zombie.jpg")
        SwiperEntity(R.drawable.zombie),
        SwiperEntity(R.drawable.zombie2),
        SwiperEntity(R.drawable.ff14),
        SwiperEntity(R.drawable.lol),
        SwiperEntity(R.drawable.qqqt),
    )

    val notifications = listOf(
        "第一条通知",
        "第二条通知",
        "第三条通知",
        "第四条通知",
        "第五条通知"
    )


}