package com.soha.infotech.newsapp.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.soha.infotech.newsapp.R
import com.soha.infotech.newsapp.presentation.Dimens.MediumPadding1
import com.soha.infotech.newsapp.presentation.Dimens.MediumPadding2
import com.soha.infotech.newsapp.presentation.onboarding.Page

/**
 * Part 1.1 : Here we will add components
 */

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(page.image),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(MediumPadding1)
        )
        Text(
            text = page.title,
            color = colorResource(R.color.display_small),
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            modifier = Modifier.padding(horizontal = MediumPadding2),
            text = page.description,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }

}

// Here's the preview function for your OnBoardingPage composable:
/*@Preview(showBackground = true)
@Composable
fun PreviewOnBoardingPage() {
    OnBoardingPage(
        page = Page(
            image = R.drawable.first_ill, // Replace with an actual drawable resource
            title = "Welcome to the App",
            description = "This is an onboarding screen description."
        )
    )
}*/
