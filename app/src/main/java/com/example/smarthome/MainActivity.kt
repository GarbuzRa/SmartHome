package com.example.smarthome

import android.content.DialogInterface.OnShowListener
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarthome.ui.theme.SmartHomeTheme

data class FlowerCardData (
   val flowerName: String,
   val notification: Boolean,
   val sunLevel: Int,
   val waterLevel: Int,
   val flowerAvatar: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartHomeTheme {

            }
        }
    }
}
@Composable
fun FlowerCard(flower: FlowerCardData) {
    Card(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ){
        Row(
            modifier = Modifier.padding(6.dp),
            verticalAlignment = Alignment.CenterVertically // Alignment - расположение относительно оси x или оси y
        ){
            Image(painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "изображение растения",
                contentScale = ContentScale.Crop, //тип растягивания контента внутри -  в данном случае урезать до краев
                modifier = Modifier.size(width = 85.dp, height = 110.dp)
                    .clip(shape = RoundedCornerShape(8.dp)))

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = flower.flowerName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.padding(end = 6.dp)
                ) {
                    repeat(3) { index ->
                        var iconRes: Int = 0
                        if (index * 33 <= flower.sunLevel) {
                            iconRes = R.drawable.sun_full
                        } else {
                            iconRes = R.drawable.sun_empty
                        }

                        Icon(
                            painter = painterResource(iconRes),
                            contentDescription = "Sunlevel",
                            tint = androidx.compose.ui.graphics.Color.Yellow
                        )
                    }
                    Text(
                        text = "${flower.sunLevel.toString()}%",
                        modifier = Modifier.padding(top = 3.dp, start = 3.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.padding(end = 6.dp, bottom = 4.dp)
                ) {
                    repeat(3) { index ->
                        var iconRes: Int = 0
                        if (index * 33 <= flower.waterLevel) {
                            iconRes = R.drawable.sun_full
                        } else {
                            iconRes = R.drawable.sun_empty
                        }

                        Icon(
                            painter = painterResource(iconRes),
                            contentDescription = "Sunlevel",
                            tint = androidx.compose.ui.graphics.Color.Yellow
                        )
                    }
                    Text(
                        text = "${flower.waterLevel.toString()}%",
                        modifier = Modifier.padding(top = 3.dp, start = 3.dp)
                    )
                }

            }
            var iconPush: Int = 0
            if (flower.notification) {
                iconPush = R.drawable.push_active
            } else {
                iconPush = R.drawable.push
            }

            Icon(
                modifier = Modifier.align(Alignment.Top),
                painter = painterResource(iconPush),
                contentDescription = "Push",
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FlowerCardPreview() {
    val flower = FlowerCardData("Monstera Deliciosa", true, 63, 99, "")
    FlowerCard(flower)
}