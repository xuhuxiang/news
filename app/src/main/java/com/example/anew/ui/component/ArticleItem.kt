package com.example.anew.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import coil.compose.AsyncImage
import com.example.anew.ui.Entity.ListItem


@Composable
fun ArticleItem(article: ListItem, modifier: Modifier = Modifier){
    val constraintSet= ConstraintSet {
        //引用
        val title=createRefFor("title")
        val img=createRefFor("img")
        val source=createRefFor("source")
        val time=createRefFor("time")
        //val divider=createRefFor("divider")
        constrain(img){
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
            centerVerticallyTo(parent)
            width= Dimension.value(140.dp)
        }
        constrain(title){
            start.linkTo(img.end, margin = 8.dp)
            end.linkTo(parent.end)
            width=Dimension.fillToConstraints
        }
        constrain(source){
            start.linkTo(title.start)
            bottom.linkTo(parent.bottom)
        }
        constrain(time){
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
    }
    Card (  modifier = modifier
        //外边距
        .padding(8.dp).fillMaxWidth()
        .height(100.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(Color(0xFFFFFFFF)),
        shape = RoundedCornerShape(6.dp),
        border = if (isSystemInDarkTheme()) BorderStroke(1.dp, Color.White) else null){
        ConstraintLayout (constraintSet,modifier= modifier
            .padding(8.dp)
            .fillMaxWidth()
           ){
            AsyncImage(
                model = article.pic, contentDescription = null, modifier = Modifier
                    .layoutId("img")
                    .aspectRatio(14 / 10f)
                    .clip(RoundedCornerShape(8.dp))
            )

            Text(
                text = article.title,
                fontSize = 16.sp,
                color = Color(0xFF666666),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.layoutId("title")
            )

            Text(
                text = "来源:${article.src}",
                fontSize = 16.sp,
                color = Color(0xFF999999),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.layoutId("source")
            )

            Text(
                text = "${article.time}",
                fontSize = 16.sp,
                color = Color(0xFF999999),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.layoutId("time")
            )
        }
    }

}