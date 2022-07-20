package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_chart.RecipeChartsContent
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_details.RecipeDetailsContent
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreenContent(recipe: Recipe) {

    val pagerState = rememberPagerState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RB_White)) {

        TabRowPart(pagerState = pagerState)

        PagerContent(
            pagerState = pagerState,
            recipe = recipe
        )
    }

    
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabRowPart(pagerState: PagerState) {

    val tabTextAndIcon = listOf(
        "tab 1" to R.drawable.ic_add_circle,
        "tab 2" to R.drawable.ic_add_circle,
        "tab 3" to R.drawable.ic_add_circle
    )



    val coroutineScope= rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = RB_Black_75,
        contentColor = RB_Red,
        divider = {
            TabRowDefaults.Divider(
                thickness = 3.dp, color = RB_Black_25
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions),
                height = 3.dp,
                color = RB_BlueDark
            )
        }
    ) {
        tabTextAndIcon.forEachIndexed { index, pair ->

            val selected = pagerState.currentPage == index
            
            Tab(
                selected = selected,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                enabled = true
            ) {
                
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    
                    Box(modifier = Modifier
                        .size(30.dp)
                        .align(CenterHorizontally)) {
                        Image(
                            painter = painterResource(id = pair.second),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                        )
                    }

                    Text(
                        text = pair.first,
                        modifier = Modifier.align(CenterHorizontally),
                        fontSize =
                        if(selected) {
                            12.sp
                        } else {
                            10.sp
                        },
                        color =
                        if(selected) {
                            RB_Black
                        } else {
                            RB_Black_50
                        },
                        fontWeight =
                        if(selected) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        },
                    )


                }
                
            }
            
        }
    }

}


@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
private fun PagerContent(
    pagerState: PagerState,
    recipe: Recipe
) {
    
    CompositionLocalProvider( LocalOverScrollConfiguration provides null ) {

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            count = 3,
            state = pagerState
        ) { pager ->

            when (pager) {
                0 -> { RecipeDetailsContent(recipe) }
                1 -> { RecipeChartsContent(recipe)  }
                2 -> {}
            }


        }
        
        HorizontalPagerIndicator(
            modifier = Modifier.padding(bottom = 20.dp),
            pagerState = pagerState,
            activeColor = RB_BlueDarkDeep,
            inactiveColor = RB_BlueLightExtra,
            indicatorWidth = 8.dp,
            indicatorHeight = 8.dp,
            indicatorShape = CircleShape,
            spacing = 5.dp
        )
        
    }

}