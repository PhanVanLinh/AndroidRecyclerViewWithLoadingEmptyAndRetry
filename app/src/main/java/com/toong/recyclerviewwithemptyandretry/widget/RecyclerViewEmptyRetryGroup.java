package com.toong.recyclerviewwithemptyandretry.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.toong.recyclerviewwithemptyandretry.R;

/**
 * Created by PhanVanLinh on 14/07/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class RecyclerViewEmptyRetryGroup extends RelativeLayout {
    private RecyclerView mRecyclerView;
    private View mEmptyView;
    private View mRetryView;
    private ProgressBar mProgressBar;
    private OnRetryClick mOnRetryClick;

    public RecyclerViewEmptyRetryGroup(Context context) {
        this(context, null);
    }

    public RecyclerViewEmptyRetryGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerViewEmptyRetryGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        if (child.getId() == R.id.recyclerView) {
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            return;
        }
        if (child.getId() == R.id.layout_empty) {
            mEmptyView = findViewById(R.id.layout_empty);
            return;
        }
        if (child.getId() == R.id.layout_retry) {
            mRetryView = findViewById(R.id.layout_retry);
            mRetryView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRetryView.setVisibility(View.GONE);
                    mOnRetryClick.onRetry();
                }
            });
            return;
        }
        if (child.getId() == R.id.progress_bar) {
            mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        }
    }

    public void loading() {
        mRetryView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void empty() {
        mEmptyView.setVisibility(View.VISIBLE);
        mRetryView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
    }

    public void retry() {
        mRetryView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
    }

    public void success() {
        mRetryView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setOnRetryClick(OnRetryClick onRetryClick) {
        mOnRetryClick = onRetryClick;
    }

    public interface OnRetryClick {
        void onRetry();
    }
}
