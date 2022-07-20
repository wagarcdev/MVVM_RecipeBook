package com.arcieri.wagner.mvvm_recipebook.utils

import androidx.compose.ui.graphics.Color
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*
import java.util.*

internal class RandomColors {
    private val recycle: Stack<Color> = Stack()
    private val colors: Stack<Color> = Stack()

    val color: Color
        get() {
            if (colors.size == 0) {
                while (!recycle.isEmpty()) colors.push(recycle.pop())
                Collections.shuffle(colors)
            }
            val color: Color = colors.pop()
            recycle.push(color)
            return color
        }

    init {
        recycle.addAll(
            listOf(
                RB_Red,
                RB_RedDark,
                RB_RedDarker,
                RB_Blue,
                RB_BlueDark,
                RB_BlueDarker,
                RB_Indigo,
                RB_IndigoDark,
                RB_IndigoDarker,
                RB_Orange,
                RB_OrangeDark,
                RB_OrangeDarker,
                RB_Yellow,
                RB_YellowDark,
                RB_YellowDarker,
                RB_Violet,
                RB_VioletDark,
                RB_VioletDarker,
                RB_Green,
                RB_GreenDark,
                RB_GreenDarker
            )
        )
    }
}
