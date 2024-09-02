package com.hello.expensetrackerbyshreyansh.feature.stats

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.github.mikephil.charting.data.Entry
import com.hello.expensetrackerbyshreyansh.R
import com.hello.expensetrackerbyshreyansh.ui.widgets.ExpenseTextView
import com.hello.expensetrackerbyshreyansh.viewmodel.StatsViewModel
import com.hello.expensetrackerbyshreyansh.viewmodel.StatsViewModelFactory

@Composable
fun StatsScreen(navController: NavController) {
    Scaffold(topBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
        ) {
            ExpenseTextView(
                text = "Statistics",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center)
            )
            Image(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .size(30.dp)
                    .align(Alignment.CenterStart)
                    .clickable { navController.popBackStack() },
                colorFilter = ColorFilter.tint(Color.Black)

            )
            Image(
                painter = painterResource(id = R.drawable.threedot),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd),
                colorFilter = ColorFilter.tint(Color.Black)
            )
        }
    }) { innerPadding ->
        val viewModel =
            StatsViewModelFactory(navController.context).create(StatsViewModel::class.java)
        val dataState = viewModel.entries.collectAsState(initial = emptyList())
        Column(modifier = Modifier.padding(innerPadding)) {
            val entries = viewModel.getEntriesForChart(dataState.value)
        }
    }
}


@Composable
fun MakeLineChart(entries: List<Entry>) {

}