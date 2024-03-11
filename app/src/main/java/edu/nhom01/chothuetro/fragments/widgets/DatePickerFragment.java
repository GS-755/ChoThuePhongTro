package edu.nhom01.chothuetro.fragments.widgets;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.fragment.app.DialogFragment;
import java.util.Date;

public class DatePickerFragment
        extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private Date date;

    private void init() {
        this.date = new Date();
    }

    public Date getDate() { return this.date; }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.init();
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        this.date = new Date(year - 1900, month, day);
    }
}
