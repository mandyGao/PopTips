
package com.mindpin.superpoptips;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class PopTipRelativeLayout extends RelativeLayout {

    public PopTipRelativeLayout(final Context context) {
        super(context);
    }

    public PopTipRelativeLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public PopTipRelativeLayout(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    public void show(final View view) {
        addView(view);
    }
}
