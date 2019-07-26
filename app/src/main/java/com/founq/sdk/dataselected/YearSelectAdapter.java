package com.founq.sdk.dataselected;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ring on 2019/7/25.
 */
public class YearSelectAdapter extends RecyclerView.Adapter<YearSelectAdapter.MyHolder> {

    public static final int MIN_YEAR = 1900;
    public static final int MAX_YEAR = 2100;

    private Context mContext;
    private int mYear;
    private int selectedPosition;

    public YearSelectAdapter(Context context) {
        mContext = context;
    }

    public void setYear(int year){
        mYear = year;
        selectedPosition = mYear - MIN_YEAR;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_year, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        final int year = MIN_YEAR + position;
        holder.mYearText.setText( year+"");
        holder.mYearText.setSelected(false);
        holder.mYearText.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        if (position == selectedPosition){
            holder.mYearText.setSelected(true);
            holder.mYearText.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
        }
        holder.mYearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = position;
                ((DataSelectedDialog)mContext).showYear(year);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return MAX_YEAR - MIN_YEAR;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private LinearLayout mYearLayout;
        private TextView mYearText;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mYearLayout = itemView.findViewById(R.id.layout_year);
            mYearText = itemView.findViewById(R.id.tv_year_text);
        }
    }
}
