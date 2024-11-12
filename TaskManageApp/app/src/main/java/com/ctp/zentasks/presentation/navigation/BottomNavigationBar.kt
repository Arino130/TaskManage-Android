package com.ctp.zentasks.presentation.navigation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.ctp.zentasks.R
import com.ctp.zentasks.presentation.extensions.getColorFromResources
import com.ctp.zentasks.presentation.navigation.component.BarShape

@Composable
fun BottomNavigationBar(
    tabs: List<BottomNavScreen>,
    onTabSelected: (BottomNavScreen) -> Unit,
    selectTab: BottomNavScreen,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    bottomBarState: Boolean = true
) {
    val context = LocalContext.current
    val transparentColor = Color.Transparent
    val fabButtonColor = context.getColorFromResources(R.color.button_background_primary)
    val fabButtonIconColor = context.getColorFromResources(R.color.white)
    val bottomBgColor = context.getColorFromResources(R.color.bottom_background)
    if (bottomBarState) {
        val configuration = LocalConfiguration.current
        val density = LocalDensity.current

        val offsetStep = (with(density) { configuration.screenWidthDp.dp.roundToPx() }) / (3 * 2)
        val offset = offsetStep + 2 * offsetStep
        val circleRadius = 26.dp
        val barShape = BarShape(
            offset = offset.toFloat(),
            circleRadius = circleRadius,
            cornerRadius = 0.dp,
        )

        Box(
            modifier = modifier
                .height(dimensionResource(R.dimen.bottom_bar) + circleRadius)
                .fillMaxWidth()
                .background(transparentColor)
                .clickable(onClick = { }, enabled = false, role = Role.Button),
            contentAlignment = Alignment.BottomCenter
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(PaddingValues(bottom = 30.dp))
                    .zIndex(1f)
                    .size(circleRadius * 2)
                    .clip(CircleShape)
                    .background(fabButtonColor)
                    .clickable(onClick = onAddClick, enabled = true, role = Role.Button)
            ) {
                Icon(Icons.Default.Add, "Add", tint = fabButtonIconColor)
            }

            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .graphicsLayer {
                        shape = barShape
                        clip = true
                    }
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.bottom_bar))
                    .background(bottomBgColor)
            ) {
                tabs.forEachIndexed() { index, item ->
                    BottomNavigationBarCustomItem(
                        context = context,
                        item = item,
                        onTabSelected = {
                            onTabSelected(item)
                        },
                        currentTab = selectTab,
                    )

                    if (tabs.size % 2 == 0 && (tabs.size / 2) == index + 1) {
                        Box(modifier = Modifier.size(24.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBarCustomItem(
    context: Context,
    item: BottomNavScreen,
    onTabSelected: (BottomNavScreen) -> Unit,
    currentTab: BottomNavScreen,
    modifier: Modifier = Modifier
) {
    val isSelected = currentTab == item
    val activeColor = context.getColorFromResources(R.color.bottom_active_nav)
    val inactiveColor = context.getColorFromResources(R.color.bottom_inactive_nav)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onTabSelected(item) }
    ) {
        Icon(
            painter = if (isSelected)
                painterResource(id = item.selectedIcon)
            else painterResource(
                id = item.unSelectedIcon
            ),
            contentDescription = stringResource(item.label),
            tint = if (isSelected) activeColor else inactiveColor
        )
    }

}
