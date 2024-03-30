package com.example.app.ui.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.navigation.Destinations
import com.example.app.ui.screens.ArticleDetailScreen
import com.example.app.ui.screens.MainFrame


/**
 * 导航图
 */
@Composable
fun NavHostApp(statusBarHeight: Int) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.HomeFrame.route) {
        //首页主框架
        composable(
            Destinations.HomeFrame.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            }
        ) {
            MainFrame(
                statusBarHeight = statusBarHeight,
                onNavigateToArticle = {
                    navController.navigate(Destinations.ArticleDetail.route)
                }
            )
        }

        //文章详情页
        composable(Destinations.ArticleDetail.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            }) {
            ArticleDetailScreen()
        }


    }

}