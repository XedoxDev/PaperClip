package com.xedox.paperclip.tools;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.xedox.paperclip.App;
import com.xedox.paperclip.R;
import java.util.ArrayList;
import java.util.List;

public class XDocView extends ScrollView {

    private List<TextView> pages;
    private String xdoc;

    public XDocView(Context context) {
        super(context);
        init();
    }

    public XDocView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XDocView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        pages = new ArrayList<>();
        setBackgroundColor(getContext().getColor(R.color.background));

        LinearLayout contentLayout = new LinearLayout(getContext());
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        addView(contentLayout);
    }

    public String getXDoc() {
        return this.xdoc;
    }

    public void loadXDocument(String xdoc) {
        this.xdoc = xdoc;
    }

    public void initXDocument() {
        if (xdoc == null) {
            return;
        }
        for (String key : XDoc.parse(xdoc).keySet()) {
            LinearLayout contentLayout = (LinearLayout) getChildAt(0);
            contentLayout.addView(new PageTextView(getContext(), XDoc.parse(xdoc).get(key)), 0);
            contentLayout.addView(new PageView(getContext(), key), 0);
        }
    }

    public static class PageView extends TextView {
        public PageView(Context context, String text) {
            super(context);
            init();
            setText(text);
        }

        public PageView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public PageView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        private void init() {
            setTypeface(getTypeface(), Typeface.BOLD);
            setTextSize(App.toDP(64));
            setTextColor(getContext().getColor(R.color.text));
            setBackgroundColor(Color.TRANSPARENT);
        }
    }

    public static class PageTextView extends TextView {
        public PageTextView(Context context, String text) {
            super(context);
            init();
            setText(text);
        }

        public PageTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public PageTextView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        private void init() {
            setTextSize(App.toDP(32));
            setTextColor(getContext().getColor(R.color.text));
            setBackgroundColor(Color.TRANSPARENT);
        }
    }
}