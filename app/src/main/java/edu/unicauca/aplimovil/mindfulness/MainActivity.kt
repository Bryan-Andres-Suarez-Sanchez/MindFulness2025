package edu.unicauca.aplimovil.mindfulness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.unicauca.aplimovil.mindfulness.ui.theme.MindFulnessTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.foundation.layout.fillMaxHeight
import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindFulnessTheme {
                MySootheApp()
            }
        }
    }
}

@Composable
fun MySootheApp() {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            MySootheAppPortrait()
        }
        Configuration.ORIENTATION_LANDSCAPE -> {
            MySootheAppLandscape()
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.alignYourBody) {
            AlingYourBodyRow()

        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    MindFulnessTheme {
        HomeScreen()
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
){
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.placeholder_search)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        placeholder = {
            Text(text = stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Preview
@Composable
fun PreviewSearchBar(){
    SearchBar()
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes imageId: Int,
    @StringRes textId: Int,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = stringResource(textId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(textId),
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.paddingFromBaseline(top=24.dp, bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlingYourBodyElementPreview(){
    MindFulnessTheme {
        AlignYourBodyElement(
            imageId = R.drawable.ab4_tabata,
            textId = R.string.ab4_tabata
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes imageId: Int,
    @StringRes textId: Int,
    modifier: Modifier = Modifier
){
    Surface (
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(width = 255.dp)
        )
        {
            Image(
                painter = painterResource(imageId),
                contentDescription = stringResource(textId),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(textId),
                style = MaterialTheme.typography.titleMedium, 
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewFavoriteCollectionCard(){
    MindFulnessTheme {
        FavoriteCollectionCard(
            R.drawable.fc1_short_mantras,
            R.string.fc1_short_mantras,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun AlingYourBodyRow(
    modifier: Modifier = Modifier
){
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(alignYourBodyData){ item->
            AlignYourBodyElement(item.imageId, item.textId)
        }
    }
}

@Preview
@Composable
fun AlingYourBodyRowPreview(){
    MindFulnessTheme {
        AlingYourBodyRow()
    }
}

@Composable
fun FavoriteCollectionsGrid(

    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(item.imageId, item.textId)
        }
    }
}

@Preview
@Composable
fun FavoriteCollectionGridPreview(){
    MindFulnessTheme {
        FavoriteCollectionsGrid()
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    MindFulnessTheme{
        HomeSection(R.string.alignYourBody) {
            AlingYourBodyRow()
        }
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab1_quick_yoga,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableResourcePair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc4_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableResourcePair(it.first, it.second) }


private data class DrawableResourcePair(
    @DrawableRes val imageId: Int,
    @StringRes val textId: Int
)

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun MySootheAppPortrait() {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
}

@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
fun MySootheAppLandscape() {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                SootheNavigationRail()
                HomeScreen()
            }
        }
}


