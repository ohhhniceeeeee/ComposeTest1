package com.example.app.ui.navigation

sealed class Destinations(val route: String) {
    //首页主框架
    object HomeFrame : Destinations("HomeFrame")

    //文章详情页面
    object ArticleDetail : Destinations("ArticleDetail")
}