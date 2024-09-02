package com.hello.expensetrackerbyshreyansh.feature.stats

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
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
                color = Color.Black,
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
        Column(modifier = Modifier.padding(innerPadding)) {
            val chartEntries = viewModel.entries.collectAsState(initial = emptyList())
            if (chartEntries.value.isEmpty()) {
                CircularProgressIndicator()
            } else {
                val (points, xAxisData) = viewModel.getEntriesForChart(chartEntries.value)
                val maxValue = points.maxByOrNull { it.y }?.y?.toInt() ?: 0
                MakeLineChart(points, xAxisData, maxValue)
            }
        }
    }
}


@Composable
fun MakeLineChart(points: List<Point>, xAxisData: AxisData, maxValue: Int) {
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = points,
                        lineStyle = LineStyle(
                            color = MaterialTheme.colorScheme.tertiary,
                            lineType = LineType.Straight()
                        ),
                        intersectionPoint = IntersectionPoint(color = MaterialTheme.colorScheme.tertiary, radius = 6.dp),
                        selectionHighlightPoint = SelectionHighlightPoint(
                            color = MaterialTheme.colorScheme.primary,
                            radius = 6.dp
                        ),
                        ShadowUnderLine(
                            alpha = 0.5f,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.inversePrimary,
                                    Color.Transparent
                                )
                            )
                        ),
                        SelectionHighlightPopUp()
                    )
                )
            ),
            backgroundColor = MaterialTheme.colorScheme.surface,
            xAxisData = xAxisData,
            yAxisData = AxisData.Builder()
                .steps(5)
                .labelData { index ->
                    val yScale = maxValue / 5
                    "${(index * yScale).toInt()}"
                }
                .backgroundColor(Color.Transparent)
                .axisLineColor(MaterialTheme.colorScheme.tertiary)
                .axisLabelColor(MaterialTheme.colorScheme.tertiary)
                .build()
        )
    )
}

