package com.example.ticketbookingclone.activities.search_result

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ticketbookingclone.viewmodel.MainViewModel
import com.google.android.gms.actions.ItemListIntents

class SearchResultActivity : AppCompatActivity() {
    private val viewModel = MainViewModel()
    private var from: String = ""
    private var to: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        from = intent.getStringExtra("from") ?: ""
        to = intent.getStringExtra("to") ?: ""

        setContent {
            ItemListScreen(
                from = from,
                to = to,
                viewModel = viewModel,
                onBackClick = {finish()}
            )
        }

    }
}