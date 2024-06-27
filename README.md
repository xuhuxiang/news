# news
本项目为移动开发大作业，在线新闻APP
# 项目演示视频链接
https://www.bilibili.com/video/BV1Nn3MeQExg/
# 项目介绍
本应用主要使用Scaffold框架与NavHost进行不同界面的切换，使用Compose组件完成界面设计，使用MVVM架构，使用RxJava2和Moshi访问极速数据网的新闻API获取数据并完成JSON数据的解析，使用Room组件完成数据库持久化存储。
# 项目设计图
![w1](https://github.com/xuhuxiang/news/assets/101508698/955185ae-d1db-400f-a5c9-4207b41c7bd4)
# 不同界面说明
NavHostApp：主要完成在不同界面进行导航的实现。
LoginScreen：完成注册登录界面，当用户首次注册时跳转信息完善界面InitInfo，完成后将数据存入数据库或登录成功跳转主框架MainFrame。
MainFrame：主框架，使用Scaffold完成在新闻列表ArticleScreen与MineScreen界面之间的导航。
ArticleScreen：文章列表界面，加载数据，完成不同类别新闻的显示，主要实现SwiperContent轮播图和ArticleItem文章项。
SwiperContent：实现循环的轮播图播放，展示图片和文章标题，通过点击可以跳转对应的新闻详情页。
ArticleItem：每一个新闻项，展示新闻标题，图片，来源，时间，通过点击可以跳转对应的新闻详情页。
ArticleDetailScreen：完成新闻详情页的实现，通过WebView展现Web界面，使用BottomSheetScaffold实现点击动态展示下拉框，进行界面字体的调整。
MineScreen：我的界面，展示个人信息以及修改个人信息的界面。
GeYan：用于修改座右铭。
Pictures：用来修改头像
PersonInfo：用来展示用户的个人简介详细信息，以及提供修改信息的设置。
AboutScreen：用来展示App的帮助信息。
