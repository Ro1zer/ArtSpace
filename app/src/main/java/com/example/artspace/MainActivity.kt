package com.example.artspace

import android.os.Bundle
import android.widget.Space
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            //TODO: Change this hard code!!!
            ArtImage(
                id = R.drawable.kvitka,
                modifier = Modifier.size(300.dp).clip(RoundedCornerShape(16.dp))
            )
            Spacer(Modifier.height(20.dp))
            ArtDescription(
                modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant).padding(10.dp),
                titleId = R.string.kvitka_title,
                descId = R.string.kvitka_desc
            )
            Spacer(Modifier.height(20.dp))
            ButtonsPanel(
                onNext = {},
                onPrevious = {}
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
        contentDescription = painterResource(id).toString(),
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
        modifier = modifier,
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