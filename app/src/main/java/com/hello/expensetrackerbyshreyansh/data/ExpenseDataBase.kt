package com.hello.expensetrackerbyshreyansh.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hello.expensetrackerbyshreyansh.data.dao.ExpenseDao
import com.hello.expensetrackerbyshreyansh.data.model.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class ExpenseDataBase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        const val DATABASE_NAME = "expense_database"

        @JvmStatic
        fun getDatabase(context: Context): ExpenseDataBase {
            return Room.databaseBuilder(
                context,
                ExpenseDataBase::class.java,
                DATABASE_NAME
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    initBasicData(context)
                }

                private fun initBasicData(context: Context) {
//                    CoroutineScope(Dispatchers.IO).launch {
//                        val dao = getDatabase(context).expenseDao()
//
//                        dao.insertExpense(
//                            ExpenseEntity(
//                                id = 1,
//                                title = "Salary",
//                                amount = 1000.0,
//                                System.currentTimeMillis(),
//                                type = "Income",
//                                category = "Upwork"
//                            )
//                        )
//
//                        dao.insertExpense(
//                            ExpenseEntity(
//                                id = 2,
//                                title = "Rent",
//                                amount = 500.0,
//                                System.currentTimeMillis(),
//                                type = "Expense",
//                                category = "Github"
//                            )
//                        )
//
//                        dao.insertExpense(
//                            ExpenseEntity(
//                                id = 3,
//                                title = "Grocery",
//                                amount = 200.0,
//                                System.currentTimeMillis(),
//                                type = "Expense",
//                                category = "Expense"
//                            )
//                        )
//
//                        dao.insertExpense(
//                            ExpenseEntity(
//                                id = 4,
//                                title = "Salary",
//                                amount = 1000.0,
//                                System.currentTimeMillis(),
//                                type = "Income",
//                                category = "Income"
//                            )
//                        )
//
//                        dao.insertExpense(
//                            ExpenseEntity(
//                                id = 5,
//                                title = "Rent",
//                                amount = 500.0,
//                                System.currentTimeMillis(),
//                                type = "Expense",
//                                category = "Upwork"
//                            )
//                        )
//
//                        dao.insertExpense(
//                            ExpenseEntity(
//                                id = 6,
//                                title = "Grocery",
//                                amount = 200.0,
//                                System.currentTimeMillis(),
//                                type = "Expense",
//                                category = "Expense"
//                            )
//                        )
//
//                        dao.insertExpense(
//                            ExpenseEntity(
//                                id = 6,
//                                title = "Grocery",
//                                amount = 200.0,
//                                System.currentTimeMillis(),
//                                type = "Expense",
//                                category = "Expense"
//                            )
//                        )
//
//                        dao.insertExpense(
//                            ExpenseEntity(
//                                id = 6,
//                                title = "Grocery",
//                                amount = 200.0,
//                                System.currentTimeMillis(),
//                                type = "Expense",
//                                category = "Expense"
//                            )
//                        )
//                    }
                }
            }).build()
        }
    }
}