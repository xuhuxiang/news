package com.example.anew.ui.component

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

import androidx.navigation.compose.composable
import com.example.anew.ui.Screens.AboutScreen
import com.example.anew.ui.Screens.ArticleDetailScreen
import com.example.anew.ui.Screens.GeYan
import com.example.anew.ui.Screens.Info
import com.example.anew.ui.Screens.InitInfo
import com.example.anew.ui.Screens.LoginScreen
import com.example.anew.ui.Screens.MainFrame
import com.example.anew.ui.Screens.PersonInfo
import com.example.anew.ui.Screens.Pictures
import com.example.anew.ui.ViewModel.NewsViewModel
import com.example.anew.ui.navigation.Destination

//导航控制器
@Composable
fun NavHostApp(newsViewModel: NewsViewModel){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.Login.route
    ) {
        //Login
        composable(Destination.Login.route) {
            LoginScreen(newsViewModel, onNavigateToMainFrame = {
                navController.navigate(Destination.HomeFrame.route)
            },onNavigateToInitInfo={
                navController.navigate(Destination.InitInfo.route)
            })
        }
        //首页大框架
        composable(Destination.HomeFrame.route) {
            MainFrame(newsViewModel = newsViewModel, onNavigateToArticle = {
                navController.navigate(Destination.ArticleDetail.route)
            }, onNavigateToPic = {
                navController.navigate(Destination.Pictures.route)
            }, onNavigateToGe = {
                navController.navigate(Destination.Ge.route)
            }, onNavigateToAbout = {
                navController.navigate(Destination.About.route)
            }, onExit = {
                navController.popBackStack()
            }, onNavigateToPersonInfo = {
                navController.navigate(Destination.PersonInfo.route)
            })
        }

        //文章详情页
        composable(Destination.ArticleDetail.route) {
            ArticleDetailScreen(onBack = {
                navController.popBackStack()
            }, vm = newsViewModel)
        }

        composable(Destination.Pictures.route) {
            Pictures(newsViewModel = newsViewModel, onBackToMine = {
                navController.popBackStack()
            })
        }
        composable(Destination.Ge.route) {
            GeYan(newsViewModel = newsViewModel, onBackToMine = {
                navController.popBackStack()
            })
        }
        composable(Destination.About.route) {
            AboutScreen(onBackToMine = {
                navController.popBackStack()
            })
        }
        composable(Destination.PersonInfo.route) {
            PersonInfo(newsViewModel, onBackToMine = {
                navController.popBackStack()
            },onNavigateToInfo={
                navController.navigate(Destination.Info.route)
            },onNavigateToPic={
                navController.navigate(Destination.Pictures.route)
            })
        }
        composable(Destination.Info.route) {
            Info(newsViewModel, onBackToMine = {
                navController.popBackStack()
            })
        }
        composable(Destination.InitInfo.route) {
            InitInfo(newsViewModel = newsViewModel,onBackToMine={
                navController.popBackStack()
            },onNavigateToInfo={
                navController.navigate(Destination.Info.route)
            },onNavigateToPic={
                navController.navigate(Destination.Pictures.route)
            },onNavigateToMainFrame={
                navController.popBackStack()
                navController.navigate(Destination.HomeFrame.route)
            })
        }
    }
}