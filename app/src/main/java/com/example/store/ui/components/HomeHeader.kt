package com.example.store.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.R
import com.example.store.ui.theme.StoreCard
import com.example.store.ui.theme.StoreChip
import com.example.store.ui.theme.StoreText
import com.example.store.ui.theme.StoreTextSecondary

@Composable
fun HomeHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "الشعار",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(48.dp)
        )
        Spacer(Modifier.width(8.dp))
        Column {
            Text(
                text = "متجر التطبيقات",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = StoreText
            )
            Text(
                text = "اكتشف • نزّل • استمتع",
                fontSize = 12.sp,
                color = StoreTextSecondary
            )
        }
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "الإشعارات",
            tint = StoreText,
            modifier = Modifier.size(26.dp)
        )
        Spacer(Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(StoreChip),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "الحساب",
                tint = StoreTextSecondary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun HomeSearchBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(StoreCard)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = null,
            tint = StoreTextSecondary,
            modifier = Modifier.size(22.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "ابحث عن تطبيقات وألعاب...",
            fontSize = 14.sp,
            color = StoreTextSecondary,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Outlined.Mic,
            contentDescription = "بحث صوتي",
            tint = StoreTextSecondary,
            modifier = Modifier.size(22.dp)
        )
    }
}
