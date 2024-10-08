package com.sangmoki.d_day

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // 시작일 종료일 객체 생성
        val startBtn = findViewById<Button>(R.id.startBtn)
        val endBtn = findViewById<Button>(R.id.endBtn)

        val dayText = findViewById<TextView>(R.id.dayText)

        // 시작일 종료일 객체 생성
        var startDate = ""
        var endDate = ""

        val calendar_start = Calendar.getInstance()
        val calendar_end = Calendar.getInstance()

        // 시작일 버튼 클릭 이벤트
        startBtn.setOnClickListener {
            
            val today = GregorianCalendar()
            val year = today.get(Calendar.YEAR)
            val month = today.get(Calendar.MONTH)
            val day = today.get(Calendar.DATE)

            val dalog = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//                    startDate = "${year}년 ${month + 1}월 ${dayOfMonth}일"
                    startDate = year.toString() + (month + 1).toString() + dayOfMonth.toString()

                    calendar_start.set(year, month + 1, dayOfMonth)
                }
            }, year, month, day)

            dalog.show()
        }
        
        // 종료일 버튼 클릭 이벤트
        endBtn.setOnClickListener {
            val today = GregorianCalendar()
            val year = today.get(Calendar.YEAR)
            val month = today.get(Calendar.MONTH)
            val day = today.get(Calendar.DATE)

            val dalog = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//                    endDate = "${year}년 ${month + 1}월 ${dayOfMonth}일"
                    endDate = year.toString() + (month + 1).toString() + dayOfMonth.toString()

                    calendar_end.set(year, month + 1, dayOfMonth)

                    calendar_start.set(year, month + 1, dayOfMonth)
                    val finalDT = TimeUnit.MILLISECONDS.toDays(calendar_end.timeInMillis - calendar_start.timeInMillis)

                    dayText.setText("우리 만난지 " + (finalDT + 1) + "일 째")

                }
            }, year, month, day)

            dalog.show()
        }
    }
}