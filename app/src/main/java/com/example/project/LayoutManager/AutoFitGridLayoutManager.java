package com.example.project.LayoutManager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AutoFitGridLayoutManager extends GridLayoutManager {
    private int columnWidth;
    private boolean columnWidthChanged;

    public AutoFitGridLayoutManager(Context context, int columnWidth) {
        super(context, 1);
        setColumnWidth(convertDpToPixel(columnWidth, context));
    }

    private int convertDpToPixel(int dp, Context context) {
//        Toast.makeText(context, "DPI: "+ context.getResources().getDisplayMetrics().densityDpi, Toast.LENGTH_LONG).show();
        return dp * (int) Math.ceil((double) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public void setColumnWidth(int newColumnWidth) {
        if (newColumnWidth > 0 && newColumnWidth != columnWidth) {
            columnWidth = newColumnWidth;
            columnWidthChanged = true;
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (columnWidthChanged && columnWidth > 0) {
            int totalSpace;
            if (getOrientation() == VERTICAL) {
                totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
            } else {
                totalSpace = getHeight() - getPaddingTop() - getPaddingBottom();
            }
            int spanCount = Math.max(1, totalSpace / columnWidth);
            setSpanCount(spanCount);
            columnWidthChanged = false;
        }
        super.onLayoutChildren(recycler, state);
    }
}
