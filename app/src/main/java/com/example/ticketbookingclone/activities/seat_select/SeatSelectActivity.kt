package com.example.ticketbookingclone.activities.seat_select

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ticketbookingclone.R
import com.example.ticketbookingclone.activities.search_result.ItemListScreen
import com.example.ticketbookingclone.activities.ticket_detail.TicketDetailActivity
import com.example.ticketbookingclone.activities.ticket_detail.TicketDetailScreen
import com.example.ticketbookingclone.domain.FlightModel

class SeatSelectActivity : AppCompatActivity() {
    private lateinit var flight: FlightModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        flight = intent.getSerializableExtra("flight") as FlightModel

        setContent {
            SeatListScreen(
                flight = flight,
                onBackClick = { finish() },
                onConfirm = {
                    val intent = Intent(this, TicketDetailActivity::class.java).apply {
                        putExtra("flight", flight)
                    }

                    startActivity(intent, null)
                }
            )
        }
    }
}