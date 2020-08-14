package com.elmanko.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            view -> clickDatePicker(view)
            //Toast.makeText(this,"Button works",Toast.LENGTH_LONG).show()
        }

    }

    fun clickDatePicker(view: View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
            view,selectedYear, selectedMonth,
            selectedDayOfMonth -> //Toast.makeText(this,"The chosen year is $selectedYear, the month is ${selectedMonth+1} and day is $selectedDayOfMonth",Toast.LENGTH_LONG).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            result1.setText(selectedDate)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate!!.time / 60000 // !! forcing even id theDate = null
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMin = currentDate!!.time / 60000
            val diffInMin = currentDateToMin - selectedDateInMinutes
            val diffinDays = diffInMin /(60*24)

            //Toast.makeText(this," $currentDate",Toast.LENGTH_LONG).show()
            //result2.setText(diffInMin.toString())
            result2.setText(diffinDays.toString())


        },
                year,
                month,
                day)

        dpd.datePicker.setMaxDate(Date().time - 86400000 )
        dpd.show()
    }

}


