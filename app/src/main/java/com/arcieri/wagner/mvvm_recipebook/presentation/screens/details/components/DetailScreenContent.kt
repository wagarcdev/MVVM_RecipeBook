package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_chart.RecipeChartsContent
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_details.RecipeDetailsContent
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreenContent(
    catalogViewModel: CatalogViewModel,
) {

    val pagerState = rememberPagerState()

    
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        TabRowPart(pagerState = pagerState)

        PagerContent(
            catalogViewModel = catalogViewModel,
            pagerState = pagerState,
        )
    }

    
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabRowPart(pagerState: PagerState) {

    val tabTextAndIcon = listOf(
        "Recipe" to R.drawable.new_recipe_icon_flat_white,
        "Info Charts" to R.drawable.balance_flat_white_icon,
        "Costs" to R.drawable.ic_dollar
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
                color = RB_Orange
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
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    
                    Box(modifier = Modifier
                        .size(20.dp)
                        .align(CenterHorizontally)) {
                        Icon(
                            painter = painterResource(id = pair.second),
                            contentDescription = null,
                            tint =
                            if(selected) {
                                RB_White
                            } else {
                                RB_White_75
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = pair.first,
                        modifier = Modifier.align(CenterHorizontally),
                        fontSize =
                        if(selected) {
                            14.sp
                        } else {
                            12.sp
                        },
                        color =
                        if(selected) {
                            RB_White
                        } else {
                            RB_White_75
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
    catalogViewModel: CatalogViewModel,
    pagerState: PagerState,
) {
    
    CompositionLocalProvider( LocalOverscrollConfiguration provides null ) {

        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x00000000)),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { pager ->

            when (pager) {
                0 -> { RecipeDetailsContent(catalogViewModel) }
                1 -> { RecipeChartsContent(catalogViewModel)  }
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