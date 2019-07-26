package com.founq.sdk.dataselected;

import android.app.Activity;
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

/**
 * Created by ring on 2019/7/25.
 */
public class DataSelectedDialog extends Activity {

    private TextView mDayOfWeekText;
    private TextView mDayText;
    private TextView mMonthText;
    private TextView mYearText;
    private ViewAnimator mViewAnimator;
    private Button mCancleBtn;
    private Button mSureBtn;

    private int year;
    private int month;
    private int day;

    private RecyclerView mYearSelect;
    private RecyclerView mDaySelect;
    private YearSelectAdapter mYearSelectAdapter;
    private MonthSelectAdapter mMonthSelectAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_data_selected);
        setFinishOnTouchOutside(false);
        findView();
        init();
    }

    private void init() {
        year = getIntent().getIntExtra("year", 2019);
        month = getIntent().getIntExtra("month", 7);
        day = getIntent().getIntExtra("day", 25);

        mDayText.setText(day + "");
        mMonthText.setText(month + "");
        mYearText.setText(year + "");

        mYearSelect = new RecyclerView(this);
        mYearSelectAdapter = new YearSelectAdapter(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mYearSelect.setAdapter(mYearSelectAdapter);
        mYearSelect.setLayoutManager(manager);

        mDaySelect = new RecyclerView(this);
        mDaySelect.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mMonthSelectAdapter = new MonthSelectAdapter(this);
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

        mMonthSelectAdapter.setYear(2019);

        mYearSelectAdapter.setYear(year);
        mYearSelectAdapter.notifyDataSetChanged();
        scrollTo(year);
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
                break;
            case R.id.tv_year:
                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_ok:
                finish();
                break;
        }
    }

    public void showYear(int year) {
        mYearText.setText(year + "");
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
