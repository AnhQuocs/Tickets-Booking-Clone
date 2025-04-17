package com.example.ticketbookingclone.activities.ticket_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ticketbookingclone.R
import com.example.ticketbookingclone.activities.splash.GradientButton
import com.example.ticketbookingclone.domain.FlightModel
import okhttp3.internal.wait

@Composable
fun TicketDetailScreen(
    flight: FlightModel,
    onBackClick: () -> Unit,
    onDownloadTicketClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.darkPurple2))
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(colorResource(R.color.darkPurple2))
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.darkPurple2))
            ) {
                val (topSection, ticketDetail) = createRefs()

                TicketDetailHeader(
                    modifier = Modifier.constrainAs(topSection) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    onBackClick = onBackClick
                )

                TicketDetailContent(
                    flight = flight,
                    modifier = Modifier.constrainAs(ticketDetail) {
                        top.linkTo(parent.top, margin = 110.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }

            GradientButton(onClick = {}, "Download Ticket")
        }
    }
}