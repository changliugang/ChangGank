package com.movie.chang.changmovie.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.movie.chang.changmovie.R;


/**
 * Created by Administrator on 2016/9/13 20:04
 * mail：changliugang@sina.com
 */
public class SimpleItemDivider extends RecyclerView.ItemDecoration {


    private static final String TAG = "SimpleItemDivider";

    private Paint mPaint;
    private Drawable mDivider;
    private int mDividerColor;
    private int mDividerWidth = 1;//分割线宽度(默认是1)
    private int mOrientation = LinearLayoutManager.VERTICAL;//分割线的方向(默认是垂直方向的)

    public SimpleItemDivider(Context mContext, int mOrientation) {
        this.mOrientation = mOrientation;
        if (mOrientation != LinearLayoutManager.HORIZONTAL && mOrientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("请输入正确的参数");
        }

        this.mOrientation = mOrientation;
        initPaint(mContext);
    }

    public SimpleItemDivider(Context mContext, int mOrientation, int dividerWidth) {
        this.mOrientation = mOrientation;
        if (mOrientation != LinearLayoutManager.HORIZONTAL && mOrientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("请输入正确的参数");
        }

        this.mOrientation = mOrientation;
        this.mDividerWidth = dividerWidth;
        initPaint(mContext);
    }

    public SimpleItemDivider(Context mContext, int mOrientation, int dividerWidth, int dividerColor) {
        this.mOrientation = mOrientation;
        if (mOrientation != LinearLayoutManager.HORIZONTAL && mOrientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("请输入正确的参数");
        }

        this.mOrientation = mOrientation;
        this.mDividerWidth = dividerWidth;
        initPaint(dividerColor);
    }

    private void initPaint(Context mContext) {
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);//抗抖动
        mPaint.setStyle(Paint.Style.FILL);//填充
        //分割线默认颜色为灰色
        mPaint.setColor(mContext.getResources().getColor(R.color.gray_line));
    }

    private void initPaint(int color) {
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);//抗抖动
        mPaint.setStyle(Paint.Style.FILL);//填充
        //分割线默认颜色为灰色
        mPaint.setColor(color);
    }

    /**
     * 设置分割线颜色
     *
     * @param colorId
     */
    public void setDividerColor(int colorId) {
        mPaint.setColor(colorId);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizontal(c, parent);
        } else {
            drawVertical(c, parent);
        }
    }

    /**
     * 绘制纵向 item 分割线
     *
     * @param canvas
     * @param parent
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerWidth;
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }

    /**
     * 绘制横向 item 分割线
     *
     * @param canvas
     * @param parent
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
//        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom() ;
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mDividerWidth;
            final int bottom = child.getBottom();
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }

    /**
     * 设置分割线的 size
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, mDividerWidth);
        } else {
            outRect.set(0, 0, mDividerWidth, 0);
        }
    }
}
