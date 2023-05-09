package com.qi.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val db by lazy { Room.databaseBuilder(applicationContext, AppDatabase::class.java, "test.db").build() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        findViewById<RecyclerView>(R.id.rv_function).apply {
            adapter = MyFunctionAdapter(getFunctions())
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getFunctions() = listOf(
        MyFunctionAdapter.MyFunction("拿到所有User") {
            launch {
                val users = withContext(Dispatchers.IO) {
                    db.userDao().getAll()
                }
                Log.d(TAG, "users: $users")
            }
        },
        MyFunctionAdapter.MyFunction("插入") {
            launch {
                withContext(Dispatchers.IO) {
                    db.userDao().insertAll(User("qi", "huang"))
                }
            }
        },
        MyFunctionAdapter.MyFunction("删除全部user") {
            launch {
                withContext(Dispatchers.IO) {
                    db.userDao().deleteAll()
                }
            }
        },
    )
}