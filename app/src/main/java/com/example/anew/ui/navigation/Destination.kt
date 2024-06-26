package com.example.anew.ui.navigation

sealed class Destination(val route:String){
    //登录页面
    object Login:Destination("Login")
    //首页大框架
    object HomeFrame:Destination("HomeFrame")

    //文章详情页
    object ArticleDetail:Destination("ArticleDetail")
    object Ge:Destination("Ge")
    object Pictures:Destination("Pictures")
    object About:Destination("About")
    object Exit:Destination("Exit")
    object PersonInfo:Destination("PersonInfo")
    object Info:Destination("Info")
    object InitInfo:Destination("InitInfo")
}