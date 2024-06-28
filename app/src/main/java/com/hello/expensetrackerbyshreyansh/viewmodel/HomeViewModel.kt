package com.hello.expensetrackerbyshreyansh.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hello.expensetrackerbyshreyansh.R
import com.hello.expensetrackerbyshreyansh.data.ExpenseDataBase
import com.hello.expensetrackerbyshreyansh.data.dao.ExpenseDao
import com.hello.expensetrackerbyshreyansh.data.model.ExpenseEntity
import java.lang.IllegalArgumentException

class HomeViewModel(dao: ExpenseDao) : ViewModel() {
    val expenses = dao.getAllExpense()
    fun getBalance(list: List<ExpenseEntity>): String {
        var total = 0.0
        list.forEach {
            if (it.type == "Income") {
                total += it.amount
            } else {
                total -= it.amount
            }
        }
        return "$total"
    }

    fun getTotalExpense(list: List<ExpenseEntity>): String {
        var total = 0.0
        list.forEach {
            if (it.type == "Expense") {
                total += it.amount
            }
        }
        return "$total"
    }

    fun getTotalIncome(list: List<ExpenseEntity>): String {
        var total = 0.0
        list.forEach {
            if (it.type == "Income") {
                total += it.amount
            }
        }
        return "$total"
    }

    fun getItemIcon(item: ExpenseEntity): Int {
        if (item.category == "Github") {
            return R.drawable.github_sign
        } else if (item.category == "Facebook") {
            return R.drawable.facebook_icon
        } else if (item.category == "Upwork") {
            return R.drawable.upwork
        } else {
            return R.drawable.facebook_icon
        }
    }
}

class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val dao = ExpenseDataBase.getDatabase(context = context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}