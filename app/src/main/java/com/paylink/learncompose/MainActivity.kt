package com.paylink.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paylink.learncompose.ui.theme.LearnComposeTheme
import com.paylink.learncompose.ui.theme.surfaceColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Conversation(SampleData.conversationSample)
            LoginMenuTest()
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    LearnComposeTheme {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = "Profile picture for ${msg.author}",
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(40.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
            )

            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            // We keep track if the message is expanded or not in this
            // variable
            var isExpanded by remember { mutableStateOf(false) }

            // We toggle the isExpanded variable when we click on this Column
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 1.dp,
                    // surfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { messagee ->
            MessageCard(msg = messagee)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginMenuTest(modifier: Modifier = Modifier) {

    val gradientColorsList = listOf(
        Color(0xFFC9FCFF),
        Color(0xFF00ADF6)
    )

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(bgGradient(gradientType = 0, colors = gradientColorsList))
//        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .graphicsLayer {
                    translationX = -355.dp.toPx() / 2
                    translationY = 80.dp.toPx() / 2
                }
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0x0),
                    ambientColor = Color(0xFF000000),
                    shape = RoundedCornerShape(size = 355.dp)
                )
                .width(355.dp)
                .height(355.dp)

                .background(
                    color = Color(0x4B00C2FF),
                    shape = RoundedCornerShape(
                        size = 355.dp
                    ),
                )
        )

        Box(
            Modifier
                .graphicsLayer {
                    translationX = 300.dp.toPx() / 2
                    translationY = 900.dp.toPx() / 2
                }
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0x40000000),
                    ambientColor = Color(0xFF000000),
                    shape = RoundedCornerShape(size = 355.dp)
                )
                .width(355.dp)
                .height(355.dp)
                .background(
                    color = Color(0x7000C2FF),
                    shape = RoundedCornerShape(size = 355.dp)
                )
        )

        Text(
            text = "WELCOME!",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight(750),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(bottom = 574.dp)
        )

        Box(modifier = Modifier
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000),
                shape = RoundedCornerShape(size = 30.dp),
            )
            .width(318.dp)
            .height(410.dp)
            .clip(shape = RoundedCornerShape(size = 30.dp))
            .background(
                color = Color(0x7AFFFFFF),
                shape = RoundedCornerShape(size = 30.dp)
            )
            .align(alignment = Alignment.Center)
        ) {
            Text(
                text = "LOGIN",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.44.sp,
                ),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .padding(bottom = 319.dp)
            )

            Text(
                text = "USERNAME",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    letterSpacing = 0.8.sp,
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .padding(bottom = 200.dp, start = 27.dp)
            )

            TextField(value = "Hello world!", onValueChange = {},
                modifier = Modifier
                    .graphicsLayer {
                        translationY = -140.dp.toPx() / 2
                    }
                    .align(alignment = Alignment.Center)
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0xFF000000),
                        ambientColor = Color(0x40000000),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .width(284.dp)
                    .height(30.dp)
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .clip(shape = RoundedCornerShape(size = 10.dp))
//                    .padding(bottom = 7 .dp)
            )
            Text(
                text = "PASSWORD",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    letterSpacing = 0.8.sp,
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .padding(bottom = 0.dp, start = 27.dp)
            )

            TextField(value = "Hello world!", onValueChange = {},
                modifier = Modifier
                    .graphicsLayer {
                        translationY = 60.dp.toPx() / 2
                    }
                    .align(alignment = Alignment.Center)
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0xFF000000),
                        ambientColor = Color(0x40000000),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .width(284.dp)
                    .height(30.dp)
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .clip(shape = RoundedCornerShape(size = 10.dp))
//                    .padding(bottom = 7 .dp)
            )

            var staySignedIn = true;
            Checkbox(checked = staySignedIn, onCheckedChange = {
                staySignedIn = !staySignedIn
            })

        }
    }
}

@Composable
private fun bgGradient(
    gradientType: Int,
    colors: List<Color>
): Brush {
    val endOffset = when (gradientType) {
        0 -> Offset(0f, Float.POSITIVE_INFINITY)
        1 -> Offset(Float.POSITIVE_INFINITY, 0f)
        else -> Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    }

    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = endOffset
    )
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LearnComposeTheme {
        LoginMenuTest()
    }
}

/**
 * SampleData for Jetpack Compose Tutorial
 */
object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Lexi",
            "Test...Test...Test..."
        ),
        Message(
            "Lexi",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            "Lexi",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        Message(
            "Lexi",
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "Lexi",
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        Message(
            "Lexi",
            "It's available from API 21+ :)"
        ),
        Message(
            "Lexi",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Lexi",
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Lexi",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "Lexi",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Lexi",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Lexi",
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Lexi",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}


