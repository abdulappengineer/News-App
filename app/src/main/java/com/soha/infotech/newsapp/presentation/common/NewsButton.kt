package com.soha.infotech.newsapp.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Part 1.2 : We have to make new file name as NewsButton.kt
 * where we will make UI of the button so we can use the button in the onboarding screen.
 *
 * There are two buttons first one is for “next” and second is for “back”
 */

@Composable
fun NewsButton(
    text:String,
    onClick:()->Unit

) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary ,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )

    }
}

// Here's the preview function for your NewsButton composable:
/*@Preview(showBackground = true)
@Composable
fun PreviewNewsButton() {
    NewsButton(
        text = "Next",
        onClick = {} // Empty lambda for preview
    )
}*/

@Composable
fun NewsBackButton(
    text:String,
    onClick:()->Unit
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Color.Black
        )
    }
}

// Here's the preview function for your NewsBackButton composable:
/*@Preview(showBackground = true)
@Composable
fun PreviewNewsBackButton() {
    NewsBackButton(
        text = "Back",
        onClick = {} // Empty lambda for preview
    )
}*/
