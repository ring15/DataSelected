package com.founq.sdk.dataselected;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewAnimator;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ring on 2019/7/25.
 */
public class DataSelectedDialog extends Activity {

    private String[] week = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    private TextView mDayOfWeekText;
    private TextView mDayText;
    private TextView mMonthText;
    private TextView mYearText;
    private ViewAnimator mViewAnimator;
    private Button mCancleBtn;
    private Button mSureBtn;

    private int mYear;
    private int mMonth;
    private int mDay;

    private RecyclerView mYearSelect;
    private RecyclerView mDaySelect;
    private YearSelectAdapter mYearSelectAdapter;
    private MonthSelectAdapter mMonthSelectAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_data_selected);
        setFinishOnTouchOutside(false);
        mYear = getIntent().getIntExtra("year", 2019);
        mMonth = getIntent().getIntExtra("month", 7);
        mDay = getIntent().getIntExtra("day", 25);
        findView();
        setDataText();
        init();
    }

    private void setDataText() {
        mDayText.setText(mDay + "");
        mMonthText.setText(mMonth + "");
        mYearText.setText(mYear + "");

        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = DateUtils.stringToDate(mYear + "-" + mMonth + "-" + mDay);
            calendar.setTime(date);
            int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            mDayOfWeekText.setText(week[intWeek]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void init() {
        mYearSelect = new RecyclerView(this);
        mYearSelectAdapter = new YearSelectAdapter(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mYearSelect.setAdapter(mYearSelectAdapter);
        mYearSelect.setLayoutManager(manager);

        mDaySelect = new RecyclerView(this);
        mDaySelect.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mMonthSelectAdapter = new MonthSelectAdapter(this, mYear, mMonth, mDay);
        MonthLayoutManager monthLayoutManager = new MonthLayoutManager();
        mDaySelect.setAdapter(mMonthSelectAdapter);
        mDaySelect.setLayoutManager(monthLayoutManager);

        mViewAnimator.addView(mDaySelect);
        mViewAnimator.addView(mYearSelect);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        mViewAnimator.setInAnimation(animation);
        Animation animation2 = new AlphaAnimation(1.0f, 0.0f);
        animation2.setDuration(1000);
        mViewAnimator.setOutAnimation(animation2);


        mViewAnimator.setDisplayedChild(0);

        mYearSelectAdapter.setYear(mYear);
        mYearSelectAdapter.notifyDataSetChanged();
        scrollTo(mYear);
    }

    private void findView() {
        mDayOfWeekText = findViewById(R.id.tv_day_of_week);
        mDayText = findViewById(R.id.tv_day);
        mMonthText = findViewById(R.id.tv_month);
        mYearText = findViewById(R.id.tv_year);
        mViewAnimator = findViewById(R.id.view_animator);
        mCancleBtn = findViewById(R.id.btn_cancel);
        mSureBtn = findViewById(R.id.btn_ok);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_day:
            case R.id.tv_month:
                changeToDay();
                break;
            case R.id.tv_year:
                changeToYear();
                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_ok:
                Intent intent = new Intent();
                intent.putExtra("date", mYear + "-" + mMonth + "-" + mDay);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    private void changeToYear(){
        mDayText.setTextColor(getResources().getColor(R.color.colorAWhite));
        mMonthText.setTextColor(getResources().getColor(R.color.colorAWhite));
        mYearText.setTextColor(getResources().getColor(R.color.colorWhite));
        mViewAnimator.setDisplayedChild(1);
    }

    private void changeToDay(){
        mDayText.setTextColor(getResources().getColor(R.color.colorWhite));
        mMonthText.setTextColor(getResources().getColor(R.color.colorWhite));
        mYearText.setTextColor(getResources().getColor(R.color.colorAWhite));
        mViewAnimator.setDisplayedChild(0);
    }

    public void showYear(int year) {
        mYear = year;
        scrollTo(mYear);
        setDataText();
        mMonthSelectAdapter.setYear(mYear);
        mMonthSelectAdapter.setMonth(mMonth);
        mMonthSelectAdapter.setDay(mDay);
        mMonthSelectAdapter.notifyDataSetChanged();
        changeToDay();
    }

    public void showDay(int month, int day) {
        mMonth = month;
        mDay = day;
        setDataText();
    }

    public void scrollTo(int year) {
        if (year > YearSelectAdapter.MIN_YEAR && year < YearSelectAdapter.MAX_YEAR) {
            mYearSelect.scrollToPosition(year - YearSelectAdapter.MIN_YEAR - 1);
        } else if (year == YearSelectAdapter.MIN_YEAR) {
            mYearSelect.scrollToPosition(year - YearSelectAdapter.MIN_YEAR);
        } else if (year == YearSelectAdapter.MAX_YEAR) {
            mYearSelect.scrollToPosition(year - YearSelectAdapter.MIN_YEAR - 2);
        }
    }
}
