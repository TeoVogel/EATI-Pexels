package com.eati.pexels.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eati.pexels.domain.PhotoExt

@Composable
fun PhotoCard(modifier: Modifier = Modifier, photo: PhotoExt) {

    var expanded by remember(photo) {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        elevation = 4.dp,
        border =
        if (expanded)
            BorderStroke(2.dp, Color(photo.avgColor.toColorInt()))
        else
            null
    ) {
        Row(
        ) {
            AnimatedVisibility(
                visible = !expanded,
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color(photo.avgColor.toColorInt()))
            ) {
                AsyncImage(
                    modifier = Modifier.size(80.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(photo.sourceURL)
                        .build(),
                    contentDescription = null,
                )

            }
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(photo.alt, style = MaterialTheme.typography.h6)
                Text(
                    "by ${photo.photographer}",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.primary,
                )
                Spacer(modifier = Modifier.size(8.dp))
                AnimatedVisibility(visible = expanded) {
                    Column {
                        AsyncImage(
                            modifier = Modifier.fillMaxWidth(),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(photo.sourceURL)
                                .build(),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}