package com.chickiroad.game.shop.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chickenwhirl.composeapp.generated.resources.Res
import chickenwhirl.composeapp.generated.resources.btn_bought
import chickenwhirl.composeapp.generated.resources.btn_buy
import chickenwhirl.composeapp.generated.resources.coin
import com.chickiroad.game.ui.shared_components.CustomButton
import com.chickiroad.game.ui.shared_components.TextWithBorder
import com.chickiroad.game.ui.theme.Beige
import com.chickiroad.game.ui.theme.Orange
import com.chickiroad.game.ui.theme.OrangeGradient
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ShopCard(
    bonusRes: DrawableResource,
    price: Int,
    onBuyClick: () -> Unit,
    isPurchased: Boolean
) {
    Column(
        modifier = Modifier
            .width(130.dp)
            .height(190.dp)
            .background(color = Beige, shape = RoundedCornerShape(16.dp))
            .border(width = 1.dp, color = Orange, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(bonusRes),
            contentDescription = null,
            modifier = Modifier.size(70.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.coin),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )

            TextWithBorder(
                text = "$price",
                fontSize = 20.sp,
                textColor = SolidColor(Color.White),
                borderColor = OrangeGradient
            )
        }

        CustomButton(
            image = painterResource(if (isPurchased) Res.drawable.btn_bought else Res.drawable.btn_buy),
            onClick = onBuyClick,
            enabled = !isPurchased,
            modifier = Modifier
                .width(120.dp)
                .height(55.dp)
        )
    }
}