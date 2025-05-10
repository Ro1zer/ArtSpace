package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var imageId by remember { mutableIntStateOf(R.drawable.kvitka) }
    var titleId by remember { mutableIntStateOf(R.string.kvitka_title) }
    var descId by remember { mutableIntStateOf(R.string.kvitka_desc) }
    //TODO: Delete this magic numbers
    var state = 0
    var onStateUpdate = {
        imageId = when (state) {
            0 -> R.drawable.kvitka
            1 -> R.drawable.house
            2 -> R.drawable.street
            3 -> R.drawable.street_2
            4 -> R.drawable.skyview
            5 -> R.drawable.skyview_2
            else -> R.drawable.tree_and_view
        }
        titleId = when (state) {
            0 -> R.string.kvitka_title
            1 -> R.string.house_title
            2 -> R.string.street_title
            3 -> R.string.street2_title
            4 -> R.string.skyview_title
            5 -> R.string.skyview2_title
            else -> R.string.tree_and_view_title
        }
        descId = when (state) {
            0 -> R.string.kvitka_desc
            1 -> R.string.house_desc
            2 -> R.string.street_desc
            3 -> R.string.street2_desc
            4 -> R.string.skyview_desc
            5 -> R.string.skyview2_desc
            else -> R.string.tree_and_view_desc
        }
    }
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            ArtImage(
                id = imageId,
                modifier = Modifier
                    .size(350.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(Modifier.height(20.dp))
            ArtDescription(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(10.dp),
                titleId = titleId,
                descId = descId
            )
            Spacer(Modifier.height(20.dp))
            //TODO: Delete this magic numbers
            ButtonsPanel(
                onNext = {
                    state = if (state > 5) 0 else state + 1
                    onStateUpdate()
                },
                onPrevious = {
                    state = if (state < 0) 5 else state - 1
                    onStateUpdate()
                }
            )
        }
    }
}

@Composable
fun ArtImage(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
) {
    Image(
        painter = painterResource(id),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun ArtDescription(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    @StringRes descId: Int,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(titleId),
            fontSize = 18.sp,
            textAlign = TextAlign.Start
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = stringResource(descId),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
fun ButtonsPanel(
    modifier: Modifier = Modifier,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
) {
    Row(
        modifier = modifier.padding(bottom = 15.dp, start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ActionButton(
            modifier = Modifier.weight(2f),
            textId = R.string.previous,
            onClick = onPrevious
        )
        Spacer(Modifier.weight(1f))
        ActionButton(
            modifier = Modifier.weight(2f),
            textId = R.string.next,
            onClick = onNext
        )
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    @StringRes textId: Int,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inverseSurface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = stringResource(textId),
            textAlign = TextAlign.Center
        )
    }
}