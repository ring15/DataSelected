package com.founq.sdk.dataselected;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by ring on 2019/7/26.
 */
public class MonthSelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_DAY = 1;

    private String[] week = {"日", "一", "二", "三", "四", "五", "六"};
    private Context mContext;
    private int mYear = 0;
    private int mMonth;
    private int mDay;

    public void setYear(int year) {
        mYear = year;
    }

    public void setMonth(int month) {
        mMonth = month;
    }

    public void setDay(int day) {
        mDay = day;
    }

    public MonthSelectAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        int type = TYPE_TITLE;
        if (position % 43 == 0) {
            type = TYPE_TITLE;
        } else {
            type = TYPE_DAY;
        }
        return type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_month, parent, false);
        holder = new MyTitleHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int i = position / 43;
        if (position % 43 == 0) {
            int month = position / 43 + 1;
            ((MyTitleHolder) holder).mTitle.setText(String.format(mContext.getString(R.string.month_day), mYear, month));
        } else if((position - 1) % 43 == 0){
            int dayOfWeek = (position -1 - i) % 7;
            ((MyTitleHolder) holder).mTitle.setText(week[dayOfWeek]);
        } else if((position - 2) % 43 == 0){
            int dayOfWeek = (position -1 - i) % 7;
            ((MyTitleHolder) holder).mTitle.setText(week[dayOfWeek]);
        } else if((position - 3) % 43 == 0){
            int dayOfWeek = (position -1 - i) % 7;
            ((MyTitleHolder) holder).mTitle.setText(week[dayOfWeek]);
        } else if((position - 4) % 43 == 0){
            int dayOfWeek = (position -1 - i) % 7;
            ((MyTitleHolder) holder).mTitle.setText(week[dayOfWeek]);
        } else if((position - 5) % 43 == 0){
            int dayOfWeek = (position -1 - i) % 7;
            ((MyTitleHolder) holder).mTitle.setText(week[dayOfWeek]);
        } else if((position - 6) % 43 == 0){
            int dayOfWeek = (position -1 - i) % 7;
            ((MyTitleHolder) holder).mTitle.setText(week[dayOfWeek]);
        } else if((position - 7) % 43 == 0){
            int dayOfWeek = (position -1 - i) % 7;
            ((MyTitleHolder) holder).mTitle.setText(week[dayOfWeek]);
        }else {
            ((MyTitleHolder) holder).mTitle.setText((position -7) % 36 + "");
        }
    }

    @Override
    public int getItemCount() {
        return 12 * 43;
    }

    public class MyTitleHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;

        public MyTitleHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView) itemView;
        }
    }

    public class MyDayHolder extends RecyclerView.ViewHolder {
        public MyDayHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
