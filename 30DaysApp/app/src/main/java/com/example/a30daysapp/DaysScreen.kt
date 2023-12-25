package com.example.a30daysapp


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysapp.model.Star
import com.example.a30daysapp.model.StarsRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaysItems(
    star: Star,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier,
        onClick = {expanded = !expanded}
    ) {
        Column(
            modifier = modifier
                .sizeIn(minHeight = 144.dp)
                .fillMaxWidth()
                .padding(16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Text(
                text = stringResource(star.day),
                style = MaterialTheme.typography.titleSmall,
            )
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = stringResource(star.starName),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = modifier.height(8.dp))
            Image(
                painter = painterResource(star.starImage),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            if(expanded) {
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = stringResource(star.starDes),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun DaysList(
    days: List<Star>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(16.dp)
    ){
        this.items(days) { star ->
            DaysItems(star = star)
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )
    },
        modifier = modifier)
}

@Preview
@Composable
fun StarItemsPreview() {
    val star = Star(
        R.string.day_1,
        R.string.andromeda_name,
        R.string.andromeda_des,
        R.drawable.andromeda
    )
    DaysItems(star = star)
}

@Preview
@Composable
fun StarListPreview() {
    DaysList(days = StarsRepository.stars)
}