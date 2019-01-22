package com.example.android.sofraa.helper;

import android.annotation.SuppressLint;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sofraa.R;

import java.util.ArrayList;


public class ToolbarTitle {

    @SuppressLint("ResourceAsColor")
    public static void centerToolbarTitle(final Toolbar toolbar) {
        final CharSequence title = toolbar.getTitle();
        final ArrayList<View> outViews = new ArrayList<>(1);
        toolbar.findViewsWithText(outViews, title, View.FIND_VIEWS_WITH_TEXT);
        if (!outViews.isEmpty()) {
            final TextView titleView = (TextView) outViews.get(0);
            titleView.setGravity(Gravity.CENTER_HORIZONTAL);
            titleView.setGravity(Gravity.CENTER_VERTICAL);
            titleView.setTextColor(R.color.black);
            final Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) titleView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.setMargins(0,0,130,0);
            toolbar.requestLayout();
        }
    }


}
