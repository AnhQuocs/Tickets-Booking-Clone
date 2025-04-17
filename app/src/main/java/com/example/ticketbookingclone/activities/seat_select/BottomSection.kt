package com.example.ticketbookingclone.activities.seat_select

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ticketbookingclone.R
import com.example.ticketbookingclone.activities.splash.GradientButton

@Composable
fun BottomSection(
    seatCount: Int,
    selectedSeats: String,
    totalPrice: Double,
    onConfirmClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(colorResource(R.color.darkPurple))
            .padding(top = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LegendItem("Available", color = colorResource(R.color.green))
            LegendItem("Selected", color = colorResource(R.color.orange))
            LegendItem("Unavailable", color = colorResource(R.color.grey))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "$seatCount Seat Selected",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Text(
                    text = if(selectedSeats.isBlank())"-" else selectedSeats,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Text(
                text = "$${String.format("%.0f", totalPrice)}",
                color = colorResource(R.color.orange),
                fontWeight = FontWeight.SemiBold,
                fontSize = 25.sp
            )
        }

        GradientButton(onClick = onConfirmClick, "Confirm Seats")
    }
}