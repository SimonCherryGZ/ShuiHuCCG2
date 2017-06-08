package com.simoncherry.shuihuccg2.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.simoncherry.shuihuccg2.R;


/**
 * Created by YoKeyword on 16/6/3.
 */
public class BottomBarTab extends FrameLayout {
    private ImageView mIcon;
    private Context mContext;
    private int mActiveIcon;
    private int mInactiveIcon;
    private int mTabPosition = -1;

    public BottomBarTab(Context context, @DrawableRes int activeIcon, int inactiveIcon) {
        this(context, null, activeIcon, inactiveIcon);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int activeIcon, int inactiveIcon) {
        this(context, attrs, 0, activeIcon, inactiveIcon);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int activeIcon, int inactiveIcon) {
        super(context, attrs, defStyleAttr);
        init(context, activeIcon, inactiveIcon);
    }

    private void init(Context context, int activeIcon, int inactiveIcon) {
        mContext = context;
        mActiveIcon = activeIcon;
        mInactiveIcon = inactiveIcon;

        TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typedArray.recycle();

        mIcon = new ImageView(context);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources().getDisplayMetrics());
        LayoutParams params = new LayoutParams(size, size);
        params.gravity = Gravity.CENTER;
        mIcon.setImageResource(inactiveIcon);
        mIcon.setLayoutParams(params);
        //mIcon.setColorFilter(ContextCompat.getColor(context, R.color.tab_unselect));
        addView(mIcon);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            //mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimary));
            mIcon.setImageResource(mActiveIcon);
        } else {
            //mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.tab_unselect));
            mIcon.setImageResource(mInactiveIcon);
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }
}
