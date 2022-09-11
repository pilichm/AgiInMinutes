package pl.pilichm.agiinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSelectDateButton.setOnClickListener{ view ->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, {
                _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            tvSelectedDate.text = selectedDate

            val theDate = sdf.parse(selectedDate)
            val selectedDateInDays = theDate!!.time / 216_000_000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToDays = currentDate!!.time / 216_000_000
            val differenceInDays = currentDateToDays - selectedDateInDays
            tvSelectedDateInDays.text = differenceInDays.toString()
        }, year, month, dayOfMonth)

        /**
         * Restrict user from using future dates.
         */
        dpd.datePicker.maxDate = Date().time - 86_400_000
        dpd.show()
    }
}