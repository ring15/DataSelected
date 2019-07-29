package com.founq.sdk.dataselected;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView mDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDateText = findViewById(R.id.tv_date);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                Calendar calendar = Calendar.getInstance();
                Intent intent = new Intent(this, DataSelectedDialog.class);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                startActivityForResult(intent, 0x01);
                break;
            case R.id.btn_time_picker:
                Calendar calendar1 = Calendar.getInstance();
                new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                            }
                        }, calendar1.get(Calendar.HOUR_OF_DAY),
                        calendar1.get(Calendar.MINUTE),
                        false).show();
                break;
            case R.id.btn_date_picker:
                Calendar calendar2 = Calendar.getInstance();
                new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                            }
                        },
                        calendar2.get(Calendar.YEAR),
                        calendar2.get(Calendar.MONTH),
                        calendar2.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0x01) {
                String date = data.getStringExtra("date");
                mDateText.setText(date);
            }
        }
    }
}
