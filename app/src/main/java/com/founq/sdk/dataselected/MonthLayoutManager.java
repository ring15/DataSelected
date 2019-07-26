package com.founq.sdk.dataselected;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ring on 2019/7/26.
 */
public class MonthLayoutManager extends RecyclerView.LayoutManager {

    private int startPosition = 0;

    private int mItemCount;
    private int height;
    private int offSet = 0;
    private int num = 0;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.getItemCount() == 0 || state.isPreLayout())
            return;
        mItemCount = getItemCount();
        height = getVerticalSpace() / 7;
        layoutChild(recycler, 0);
    }

    private void layoutChild(RecyclerView.Recycler recycler, int dy) {
        if (getItemCount() == 0) return;

        offSet += dy;
        if (offSet > 0 || offSet < -11 * getVerticalSpace()){
            offSet = offSet - dy;
            return;
        }

        int m = Math.abs(offSet) / height - num;
        if (m > 0){
            for (int i = 0; i < m; i++){
                if (startPosition % 43 == 0){
                    startPosition = startPosition + 1;
                }else {
                    startPosition = startPosition +7;
                }
            }
        }else if (m<0){
            for (int i = 0 ; i < Math.abs(m); i++){
                if ((startPosition - 1) % 43 == 0){
                    startPosition = startPosition - 1;
                }else {
                    startPosition = startPosition -7;
                }
            }
        }
        num = Math.abs(offSet) / height;

        List<Integer> tops = new ArrayList<>();
        List<Integer> lefts = new ArrayList<>();

        int titleNum = 0;
        for (int i = 0; i < mItemCount; i++) {
            int top;
            int left;
            if (i % 43 == 0) {
                titleNum++;
                int line = i / 43;
                top = line * getVerticalSpace() +offSet;
                left = 0;
            } else {
                int count = (i - titleNum) % 7;
                int line = (i - titleNum) / 7 + titleNum;
                left = count * getHorizontalSpace() / 7;
                top = line * height +offSet;
            }
//            if (top <= -height) {
//                startPosition = i + 7;
//            }
//            if (top >= getVerticalSpace() + height) {
//                break;
//            }
            tops.add(top);
            lefts.add(left);
        }

        int childCount = getChildCount();
        for (int i = childCount -1; i >= 0; i--) {
            View childView = getChildAt(i);
            int position = getPosition(childView);
            if (position > startPosition + 50 || position < startPosition - 7) {
                removeAndRecycleView(childView, recycler);
            }
        }

        detachAndScrapAttachedViews(recycler);

        for (int i = startPosition; i < startPosition + 50 && i < tops.size(); i++) {
            View childView = recycler.getViewForPosition(i);
            addView(childView);
            int mItemType = getItemViewType(childView);
            if (mItemType == MonthSelectAdapter.TYPE_TITLE) {
                Log.i("test", tops.get(i) + "");
                layoutDecoratedWithMargins(childView, 0, tops.get(i), getHorizontalSpace(), tops.get(i) + height);
            } else {
                layoutDecoratedWithMargins(childView, lefts.get(i), tops.get(i),
                        lefts.get(i) + getHorizontalSpace() / 7, tops.get(i) + height);
            }
        }

    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        layoutChild(recycler, -dy);
        return -dy;
    }

    /**
     * 获取RecyclerView的显示高度
     */
    public int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }


    /**
     * 获取RecyclerView的显示宽度
     */
    public int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

}
