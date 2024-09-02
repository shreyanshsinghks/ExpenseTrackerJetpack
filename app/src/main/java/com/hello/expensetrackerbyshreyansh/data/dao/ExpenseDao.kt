package com.hello.expensetrackerbyshreyansh.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hello.expensetrackerbyshreyansh.data.model.ExpenseEntity
import com.hello.expensetrackerbyshreyansh.data.model.ExpenseSummary
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense_table ORDER BY id DESC")
    fun getAllExpense(): Flow<List<ExpenseEntity>>

    @Query("SELECT type, date, SUM(amount) as totalAmount FROM expense_table where type = :type GROUP BY type, date ORDER BY date DESC")
    fun getAllExpensesByDate(type: String = "Expense"): Flow<List<ExpenseSummary>>

    @Insert
    suspend fun insertExpense(expenseEntity: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)

    @Update
    suspend fun updateExpense(expenseEntity: ExpenseEntity)
}