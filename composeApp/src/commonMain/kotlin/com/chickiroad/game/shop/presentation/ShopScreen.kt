package com.chickiroad.game.shop.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chickenwhirl.composeapp.generated.resources.Res
import chickenwhirl.composeapp.generated.resources.btn_back
import chickenwhirl.composeapp.generated.resources.coin
import chickenwhirl.composeapp.generated.resources.default_background
import chickenwhirl.composeapp.generated.resources.egg_blue
import chickenwhirl.composeapp.generated.resources.egg_red
import chickenwhirl.composeapp.generated.resources.egg_yellow
import chickenwhirl.composeapp.generated.resources.settings_card
import chickenwhirl.composeapp.generated.resources.shop_header
import com.chickiroad.game.shop.presentation.components.ShopCard
import com.chickiroad.game.ui.shared_components.Background
import com.chickiroad.game.ui.shared_components.CustomButton
import com.chickiroad.game.ui.shared_components.TextWithBorder
import com.chickiroad.game.ui.theme.Beige
import com.chickiroad.game.ui.theme.Orange
import com.chickiroad.game.ui.theme.OrangeGradient
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ShopScreen(
    onHomeClick: () -> Unit,
    shopViewModel: ShopViewModel = koinViewModel()
) {
    val shopUiState by shopViewModel.uiState.collectAsState()

    Background(backgroundResImage = Res.drawable.default_background)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(340.dp)
                .height(630.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.settings_card),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            Image(
                painter = painterResource(Res.drawable.shop_header),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(120.dp)
                    .offset(y = (-70).dp)
            )

            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 20.dp)
                    .background(color = Beige, shape = RoundedCornerShape(16.dp))
                    .border(width = 1.dp, color = Orange, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.coin),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(18.dp)
                )

                TextWithBorder(
                    text = shopUiState.coins.toString(),
                    fontSize = 18.sp,
                    textColor = SolidColor(Color.White),
                    borderColor = OrangeGradient
                )
            }

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    shopUiState.items.take(2).forEach { item ->
                        ShopCard(
                            bonusRes = item.icon,
                            price = item.price,
                            onBuyClick = { shopViewModel.purchaseItem(item.id) },
                            isPurchased = item.isPurchased
                        )
                    }
                }

                if (shopUiState.items.size > 2) {
                    ShopCard(
                        bonusRes = shopUiState.items[2].icon,
                        price = shopUiState.items[2].price,
                        onBuyClick = { shopViewModel.purchaseItem(shopUiState.items[2].id) },
                        isPurchased = shopUiState.items[2].isPurchased
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-10).dp)
                    .align(Alignment.BottomCenter),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(Res.drawable.egg_blue),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )

                Image(
                    painter = painterResource(Res.drawable.egg_red),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )

                Image(
                    painter = painterResource(Res.drawable.egg_yellow),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        CustomButton(
            image = painterResource(Res.drawable.btn_back),
            onClick = onHomeClick,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .width(84.dp)
                .height(40.dp)
        )
    }
}