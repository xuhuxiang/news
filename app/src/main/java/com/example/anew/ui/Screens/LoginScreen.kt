package com.example.anew.ui.Screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anew.R
import com.example.anew.ui.MainApp
import com.example.anew.ui.ViewModel.NewsViewModel
import com.example.anew.ui.database.Info
import com.example.anew.ui.database.User
import com.example.anew.ui.database.UserDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginScreen(newsViewModel: NewsViewModel,onNavigateToMainFrame:()->Unit={},
                onNavigateToInitInfo:()->Unit={})
{
    //屏幕宽度
//    var screenWidth:Float
//    with(LocalDensity.current){
//        screenWidth= LocalConfiguration.current.screenWidthDp.dp.toPx()
//    }
//    //屏幕高度
//    var screenHeight:Float
//    with(LocalDensity.current){
//        screenHeight= LocalConfiguration.current.screenHeightDp.dp.toPx()
//    }
    val userDao = UserDatabase.getInstance(MainApp.context).getUserDao()
    val userInfo=UserDatabase.getInstance(MainApp.context).getInfoDao()
    val context= LocalContext.current
    var account by remember{ mutableStateOf("") }
    var password by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    BoxWithConstraints(modifier=Modifier.fillMaxSize()){
        //背景图片
        Image(
            painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier=Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        //右上往左下渐变
        Box(modifier= Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFFbb8387),
                        Color.Transparent
                    ), start = Offset(constraints.maxWidth.toFloat(), 0f),
                    end = Offset(x = 0f, y = constraints.maxHeight.toFloat())
                )
            )
        )
        Box(modifier= Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF149EE7),
                        Color.Transparent
                    ), start = Offset(x = 0f, y = constraints.maxHeight.toFloat()),
                    end = Offset(x = constraints.maxWidth.toFloat(), 0f)
                )
            )
        )
        Column(modifier= Modifier
            .fillMaxSize()
            .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(120.dp))
                Image(painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier= Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp)))
            }
            Spacer(modifier = Modifier.height(80.dp))
            Column(modifier=Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(value = account , onValueChange = {account=it},
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint= Color.White)
                    },
                    label={
                        Text(text="账号", fontSize = 14.sp, color = Color.White)
                    },
                    colors= TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                        cursorColor = Color.White
                    )
                )

                TextField(value = password , onValueChange = {password=it},
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Password,
                            contentDescription = null,
                            tint= Color.White)
                    },
                    trailingIcon = {
                        Icon(imageVector = if(showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null,
                            modifier=Modifier.clickable {
                                showPassword=!showPassword
                            },tint= Color.White)
                    },
                    label={
                        Text(text="密码", fontSize = 14.sp, color = Color.White)
                    },
                    visualTransformation = if(showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    colors= TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                        cursorColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = {
                    Observable.create<User> { emitter ->
                        if(account==""||password==""){
                            emitter.onError(NoSuchElementException("请输入账号或密码"))
                        }
                        else {
                            val user = userDao.queryUserByName(account)
                            if (user == null) {
                                emitter.onError(NoSuchElementException("用户不存在"))
                            } else {
                                emitter.onNext(user)
                            }
                        }
                    }.subscribeOn(Schedulers.io()) // 指定被观察者的线程处理I/O操作
                        .observeOn(AndroidSchedulers.mainThread()) // 指定观察者的线程为主线程
                        .subscribe({ user ->
                            // 因为我们已经在主线程中，所以可以安全地使用Toast
                            if (user.password != password) {
                                Toast.makeText(context, "账号或密码错误", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(context, "登录成功", Toast.LENGTH_LONG).show()
                                newsViewModel.info.value[5] = account
                                newsViewModel.info.value[6] = password
                                newsViewModel.updateCurrentScreen(0)
                                onNavigateToMainFrame.invoke()
                            }
                        }, { error ->
                            // 处理错误情况
                            Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
                        })
                }) {
                    Text(text="登录",
                        color= Color.White,
                        fontSize = 16.sp
                    )
                }
            }
            TextButton(onClick = {
                Observable.create<Long> { emitter ->
                    if(account!=""||password=="") {
                        var id = userDao.insertUser(User(account, password))
                        userInfo.insertUserInfo(
                            Info(
                                account,
                                R.drawable.logo,
                                "",
                                "",
                                "",
                                "",
                                "",
                                ""
                            )
                        )
                        emitter.onNext(id)
                    }else{
                        emitter.onError(NoSuchElementException("请输入账号或密码"))
                    }
                }.subscribeOn(Schedulers.io())//指定被观察者的线程处理I/O 操作
                    .observeOn(AndroidSchedulers.mainThread())//指定观察者的线程为主线程
                    .subscribe ({
                        if(it!=-1L){
                            Toast.makeText(context,"注册成功",Toast.LENGTH_LONG).show()
                            newsViewModel.info.value[5] = account
                            newsViewModel.info.value[6] = password
                            newsViewModel.updateCurrentScreen(0)
                            if(account!="")
                                onNavigateToInitInfo()
                        }else{
                            Toast.makeText(context,"注册失败",Toast.LENGTH_LONG).show()
                        }
                    }, {error->
                        Toast.makeText(context,error.message,Toast.LENGTH_LONG).show()
                    })
            }) {
                Text(text="还没有一个账号，点击注册",color= Color.LightGray, fontSize = 12.sp)

            }
        }
    }
}
