package com.example.taskmanagementapp.Util

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.taskmanagementapp.Model.ToDoModel

class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context,
    DATABASE_NAME, null, 1) {

    private var db: SQLiteDatabase? = null

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT, TASK TEXT, STATUS INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertTask(model: ToDoModel) {
        db = writableDatabase
        val values = ContentValues()
        values.put(COL_2, model.task)
        values.put(COL_3, 0)
        db!!.insert(TABLE_NAME, null, values)
    }

    fun updateTask(id: Int, task: String) {
        db = writableDatabase
        val values = ContentValues()
        values.put(COL_2, task)
        db!!.update(TABLE_NAME, values, "ID=?", arrayOf(id.toString()))
    }

    fun updateStatus(id: Int, status: Int) {
        db = writableDatabase
        val values = ContentValues()
        values.put(COL_3, status)
        db!!.update(TABLE_NAME, values, "ID=?", arrayOf(id.toString()))
    }

    fun deleteTask(id: Int) {
        db = writableDatabase
        db!!.delete(TABLE_NAME, "ID=?", arrayOf(id.toString()))
    }

    fun getAllTasks(): List<ToDoModel> {
        db = writableDatabase
        var cursor: Cursor? = null
        val modelList: MutableList<ToDoModel> = ArrayList()

        db!!.beginTransaction()
        try {
            cursor = db!!.query(TABLE_NAME, null, null, null, null, null, null)
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        val task = ToDoModel()
                        task.id = cursor.getInt(cursor.getColumnIndex(COL_1))
                        task.task = cursor.getString(cursor.getColumnIndex(COL_2))
                        task.status = cursor.getInt(cursor.getColumnIndex(COL_3))
                        modelList.add(task)
                    } while (cursor.moveToNext())
                }
            }
        } finally {
            db!!.endTransaction()
            cursor!!.close()
        }
        return modelList
    }

    companion object {
        private const val DATABASE_NAME = "TODO_DATABASE"
        private const val TABLE_NAME = "TODO_TABLE"
        private const val COL_1 = "ID"
        private const val COL_2 = "TASK"
        private const val COL_3 = "STATUS"
    }
}
