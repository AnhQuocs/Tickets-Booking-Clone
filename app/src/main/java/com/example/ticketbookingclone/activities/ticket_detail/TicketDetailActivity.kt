package com.example.ticketbookingclone.activities.ticket_detail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ticketbookingclone.R
import com.example.ticketbookingclone.domain.FlightModel

class TicketDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val flight = intent.getSerializableExtra("flight") as FlightModel

        setContent {
            TicketDetailScreen(
                flight = flight,
                onBackClick = {finish()},
                onDownloadTicketClick = {

                }
            )
        }
    }
}