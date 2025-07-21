package com.chickiroad.game.game.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import chickenwhirl.composeapp.generated.resources.Res
import chickenwhirl.composeapp.generated.resources.trap
import com.chickiroad.game.game.model.Obstacle
import org.jetbrains.compose.resources.painterResource

@Composable
fun ObstacleItem(
    obstacle: Obstacle,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(Res.drawable.trap),
        contentDescription = "Trap",
        modifier = modifier
            .size(obstacle.size.dp)
            .offset(x = obstacle.x.dp, y = obstacle.y.dp)
    )
}
