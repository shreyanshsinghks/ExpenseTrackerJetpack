package com.hello.expensetrackerbyshreyansh.viewmodel

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import com.hello.expensetrackerbyshreyansh.data.ExpenseDataBase
import com.hello.expensetrackerbyshreyansh.data.dao.ExpenseDao
import com.hello.expensetrackerbyshreyansh.data.model.ExpenseSummary

class StatsViewModel(dao: ExpenseDao) : ViewModel() {
    val entries = dao.getAllExpensesByDate()
    fun getEntriesForChart(entries: List<ExpenseSummary>): Pair<List<Point>, AxisData> {
        val sortedEntries = entries.sortedBy { it.date }
        val minDate = sortedEntries.minByOrNull { it.date }?.date ?: 0L
        val maxDate = sortedEntries.maxByOrNull { it.date }?.date ?: 0L
        val daysRange = ((maxDate - minDate) / (24 * 60 * 60 * 1000)).toInt() + 1 // Add 1 to include the last day

        val points = sortedEntries.mapIndexed { index, entry ->
            val daysSinceMinDate = ((entry.date - minDate) / (24 * 60 * 60 * 1000)).toInt()
            Point(
                x = daysSinceMinDate.toFloat(),
                y = entry.totalAmount.toFloat(),
                description = formatDateForXAxis(entry.date, minDate)
            )
        }

        val xAxisData = AxisData.Builder()
            .axisStepSize(35.dp)
            .steps(daysRange)
            .labelData { index ->
                formatDateForXAxis(minDate + (index * 24 * 60 * 60 * 1000), minDate)
            }
            .backgroundColor(Color.Transparent)
            .build()

        return Pair(points, xAxisData)
    }

    private fun formatDateForXAxis(date: Long, minDate: Long): String {
        return when (val daysSinceMinDate = ((date - minDate) / (24 * 60 * 60 * 1000)).toInt()) {
            0 -> "T"
            else -> "${daysSinceMinDate}D"
        }
    }
}


class StatsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatsViewModel::class.java)) {
            val dao = ExpenseDataBase.getDatabase(context = context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return StatsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}