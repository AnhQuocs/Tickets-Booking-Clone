package com.example.ticketbookingclone.activities.seat_select

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ticketbookingclone.R
import com.example.ticketbookingclone.domain.FlightModel

enum class SeatStatus {
    AVAILABLE,
    SELECTED,
    UNAVAILABLE,
    EMPTY
}

data class Seat (
    var status: SeatStatus,
    var name: String
)

@Composable
fun SeatListScreen(
    flight: FlightModel,
    onBackClick: () -> Unit,
    onConfirm: (FlightModel) -> Unit
) {
    val context = LocalContext.current

    val seatList = remember { mutableStateListOf<Seat>() }
    val selectedSeatNames = remember { mutableStateListOf<String>() }

    var seatCount by remember { mutableStateOf(0) }
    var totalPrice by remember { mutableStateOf(0.0) }

    LaunchedEffect(flight) {
        seatList.clear()
        seatList.addAll(generateSeatList(flight))
        seatCount = selectedSeatNames.size
        totalPrice = seatCount * flight.Price
    }

    fun updatePriceAndCount() {
        seatCount = selectedSeatNames.size
        totalPrice = seatCount * flight.Price
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkPurple2))
    ) {
        val (topSection, middleSection, bottomSection) = createRefs()

        TopSection(
            modifier = Modifier
                .constrainAs(topSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onBackClick = onBackClick
        )

        // middle section
        ConstraintLayout(
            modifier = Modifier
                .padding(top = 80.dp)
                .constrainAs(middleSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            val (airplane, seatGrid) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.airple_seat),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(airplane) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier
                    .padding(top = 215.dp)
                    .padding(horizontal = 52.dp)
                    .constrainAs(seatGrid) {
                        top.linkTo(parent.top)
                        start.linkTo(airplane.start)
                        end.linkTo(airplane.end)
                    }
            ) {
                items(seatList) { seat ->
                    SeatItem(
                        seat = seat,
                        onSeatClick = {
                            when(seat.status) {
                                SeatStatus.AVAILABLE -> {
                                    seat.status = SeatStatus.SELECTED
                                    selectedSeatNames.add(seat.name)
                                }
                                SeatStatus.SELECTED -> {
                                    seat.status = SeatStatus.AVAILABLE
                                    selectedSeatNames.remove(seat.name)
                                }
                                else -> {}
                            }
                            updatePriceAndCount()
                        }
                    )
                }

            }
        }

        BottomSection(
            seatCount = seatCount,
            selectedSeats = selectedSeatNames.joinToString(", "),
            totalPrice = totalPrice,
            onConfirmClick = {
                if(seatCount > 0) {
                    flight.Passenger = selectedSeatNames.joinToString(", ")
                    flight.Price = totalPrice
                    onConfirm(flight)
                } else {
                    Toast.makeText(context, "Please select your seat", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.constrainAs(bottomSection) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

fun generateSeatList(flight: FlightModel): List<Seat> {
    val seatList = mutableListOf<Seat>()
    val numberSeat = flight.NumberSeat + (flight.NumberSeat / 7) + 1
    val seatAlphabetMap = mapOf(
        0 to "A",
        1 to "B",
        2 to "C",
        4 to "D",
        5 to "E",
        6 to "F",
    )
    var row = 0
    for(i in 0 until numberSeat) {
        if(i % 7 == 0) {
            row++
        }
        if(i % 7 == 3) {
            seatList.add(Seat(SeatStatus.EMPTY, row.toString()))
        } else {
            val seatName = seatAlphabetMap[i % 7] + row
            val seatStatus = if(flight.ReservedSeats.contains(seatName)) {
                SeatStatus.UNAVAILABLE
            } else {
                SeatStatus.AVAILABLE
            }
            seatList.add(Seat(seatStatus, seatName))
        }
    }

    return seatList
}
