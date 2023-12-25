package com.example.build_a_grind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.build_a_grind.data.Datasource
import com.example.build_a_grind.model.Topic
import com.example.build_a_grind.ui.theme.Build_A_GrindTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Build_A_GrindTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CoursesApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun TopicCardPreview() {
    TopicCard(Topic(R.string.photography,25, R.drawable.photography))
}

@Composable
fun TopicCard(topic: Topic,modifier: Modifier = Modifier) {
    Card(modifier = Modifier) {
        Row() {
            Image(
                painter = painterResource(topic.courseImage),
                contentDescription = stringResource(topic.courseName),
                modifier = Modifier
                    .width(68.dp)
                    .height(68.dp),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = LocalContext.current.getString(topic.courseName),
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(modifier = Modifier.padding(start = 16.dp)) {
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = stringResource(R.string.grain),
                        modifier = Modifier
                    )
                    Text(
                        text = topic.number.toString(),
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}
@Composable
fun CoursesApp() {
    CoursesList(
        topicList = Datasource().loadTopics(),
    )
}

@Composable
fun CoursesList(topicList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
        items(topicList) { topic ->
            TopicCard(
                topic = topic
            )
        }
    }
}