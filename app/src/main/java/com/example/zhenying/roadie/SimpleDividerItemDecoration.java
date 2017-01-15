package com.example.zhenying.roadie;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/**
 * Created by Zhenying on 1/14/17.
 */

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration{
    private Drawable mDivider;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SimpleDividerItemDecoration(Resources resources) {
        mDivider = resources.getDrawable(R.drawable.divider,null);
    }
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
