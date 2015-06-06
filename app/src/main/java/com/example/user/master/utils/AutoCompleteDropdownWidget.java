package com.example.user.master.utils;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * Created by Bgdn on 6/4/2015.
 */
public class AutoCompleteDropdownWidget extends AutoCompleteTextView {

    public AutoCompleteDropdownWidget(Context context) {
        super(context);
    }

    public AutoCompleteDropdownWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCompleteDropdownWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if(focused){
            this.performFiltering(getText(), 0);
        }
    }


}
