package com.example.ticketbookingclone.activities.search_result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ticketbookingclone.R
import com.example.ticketbookingclone.viewmodel.MainViewModel

@Composable
fun ItemListScreen(
    from: String,
    to: String,
    viewModel: MainViewModel,
    onBackClick: () -> Unit
) {
    val items by viewModel.loadFiltered(from, to).observeAsState(emptyList())
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(from, to) {
        viewModel.loadFiltered(from, to)
    }

    LaunchedEffect(items) {
        isLoading = items.isEmpty()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.darkPurple2))
            .padding(top = 36.dp, start = 16.dp, end = 16.dp)
    ) {
        val (backBtn, headerTitle, worldImg) = createRefs()

        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = null,
            modifier = Modifier
                .clickable { onBackClick() }
                .constrainAs(backBtn) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Text(
            "Search Result",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.constrainAs(headerTitle) {
                top.linkTo(backBtn.top)
                start.linkTo(backBtn.end, margin = 8.dp)
                bottom.linkTo(backBtn.bottom)
            }
        )

        Image(
            painter = painterResource(R.drawable.world),
            contentDescription = null,
            modifier = Modifier.constrainAs(worldImg) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
    }

    //show list
    if(isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 100.dp)
        ) {
            itemsIndexed(items) { index, item ->
                FlightItem(item = item, index = index)
            }
        }
    }
}