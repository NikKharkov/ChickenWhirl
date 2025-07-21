package com.chickiroad.game.game.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import chickenwhirl.composeapp.generated.resources.Res
import chickenwhirl.composeapp.generated.resources.coin
import com.chickiroad.game.game.model.Coin
import org.jetbrains.compose.resources.painterResource

@Composable
fun CoinItem(
    coin: Coin,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(Res.drawable.coin),
        contentDescription = "Coin",
        modifier = modifier
            .size(coin.size.dp)
            .offset(x = coin.x.dp, y = coin.y.dp)
    )
}