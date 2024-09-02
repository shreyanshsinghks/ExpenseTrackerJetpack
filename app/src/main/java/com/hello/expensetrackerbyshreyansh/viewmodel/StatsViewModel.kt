package com.hello.expensetrackerbyshreyansh.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hello.expensetrackerbyshreyansh.R
import com.hello.expensetrackerbyshreyansh.data.ExpenseDataBase
import com.hello.expensetrackerbyshreyansh.data.dao.ExpenseDao
import com.hello.expensetrackerbyshreyansh.data.model.ExpenseEntity
import java.lang.IllegalArgumentException
import com.github.mikephil.charting.data.Entry
import com.hello.expensetrackerbyshreyansh.Utils
import com.hello.expensetrackerbyshreyansh.data.model.ExpenseSummary

class StatsViewModel(dao: ExpenseDao) : ViewModel() {
    val entries = dao.getAllExpensesByDate()
    fun getEntriesForChart(entries: List<ExpenseSummary>): List<Entry>{
        val list = mutableListOf<Entry>()
        for(entry in entries){
            val formattedDate = Utils.getMilliFromDate(entry.date)
            list.add(Entry(formattedDate.toFloat(), entry.totalAmount.toFloat()))
        }
        return list
    }
}


class StatsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val dao = ExpenseDataBase.getDatabase(context = context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}