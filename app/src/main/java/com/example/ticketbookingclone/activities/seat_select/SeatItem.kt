package com.example.ticketbookingclone.activities.seat_select

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketbookingclone.R

@Composable
fun SeatItem(
    seat: Seat,
    onSeatClick: () -> Unit
) {
    val backgroundColor = when (seat.status) {
        SeatStatus.AVAILABLE -> colorResource(R.color.green)
        SeatStatus.SELECTED -> colorResource(R.color.orange)
        SeatStatus.UNAVAILABLE -> colorResource(R.color.grey)
        SeatStatus.EMPTY -> Color.Transparent
    }

    val textColor = when (seat.status) {
        SeatStatus.AVAILABLE -> Color.White
        SeatStatus.SELECTED -> Color.Black
        SeatStatus.UNAVAILABLE -> Color.Gray
        SeatStatus.EMPTY -> Color.Transparent
    }

    val clickableEnabled = seat.status == SeatStatus.AVAILABLE || seat.status == SeatStatus.SELECTED

    Box(
        modifier = Modifier
            .size(28.dp)
            .padding(3.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor)
            .clickable(enabled = clickableEnabled) { onSeatClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = seat.name,
            color = textColor,
            fontSize = 12.sp
        )
    }
}