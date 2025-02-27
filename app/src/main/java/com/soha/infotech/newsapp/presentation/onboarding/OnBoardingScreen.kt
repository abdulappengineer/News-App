package com.soha.infotech.newsapp.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soha.infotech.newsapp.presentation.Dimens.MediumPadding2
import com.soha.infotech.newsapp.presentation.common.NewsBackButton
import com.soha.infotech.newsapp.presentation.common.NewsButton
import com.soha.infotech.newsapp.presentation.onboarding.components.OnBoardingPage
import com.soha.infotech.newsapp.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

/**
 * Part 1.4 : Implement onboarding Screen
 *
 * We have added pages, button, dots in the screen
 *
 * Added Horizontal Pager for sliding the pages
 *
 * And added a logic for the button as not showing back button on the first screen
 * and adding it on second screen and added some functionality onClick
 * for making page change to next, back.
 */

@Composable
fun OnBoardingScreen(
    onEvent: (OnBoardingEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("", "Get Stated")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index], modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        }
        // To put the Indicators and buttons at the bottom of the screen we can use "Spacer" and weight "1f"
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .padding(start = 16.dp, end = 16.dp)
                .navigationBarsPadding() // This will calculate the bottom navigation space and then adding it at the bottom of the Row
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(52.dp),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )


            Row(verticalAlignment = Alignment.CenterVertically) {
                val coroutine = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    NewsBackButton(
                        text = buttonState.value[0],
                        onClick = {
                            coroutine.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        })
                }

                NewsButton(
                    text = buttonState.value[1],
                    onClick = {
                        coroutine.launch {
                            // Here we have implemented the onboarding screen properly
                            if (pagerState.currentPage == 2) {
                                onEvent(OnBoardingEvent.SaveAppEntry)
                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    })
            }
        }

        Spacer(modifier = Modifier.weight(0.5f))


        // One thing is remaining is changing the system top bar: By add this to MainActivity :WindowCompat.setDecorFitsSystemWindows(window,false)
    }
}