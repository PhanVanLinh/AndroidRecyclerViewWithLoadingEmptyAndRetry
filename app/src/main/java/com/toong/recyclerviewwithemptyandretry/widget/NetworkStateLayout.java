package com.toong.recyclerviewwithemptyandretry.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.toong.recyclerviewwithemptyandretry.R;
import com.toong.recyclerviewwithemptyandretry.base.NetworkState;

public class NetworkStateLayout extends RelativeLayout {
    @Nullable
    private View loadingView;
    @Nullable
    private View emptyView;
    @Nullable
    private View failView;
    @Nullable
    private View successView;
    @Nullable
    private View completeView;

    public NetworkStateLayout(Context context) {
        this(context, null);
    }

    public NetworkStateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetworkStateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
        switch (layoutParams.networkState) {
            case LayoutParams.STATE_LOADING:
                loadingView = child;
                return;
            case LayoutParams.STATE_EMPTY:
                emptyView = child;
                return;
            case LayoutParams.STATE_FAILED:
                failView = child;
                return;
            case LayoutParams.STATE_SUCCESS:
                loadingView = child;
                return;
            case LayoutParams.STATE_COMPLETE:
                completeView = child;
        }
    }

    private void showLoadingView() {
        showView(loadingView);
    }

    private void showFailView() {
        showView(failView);
    }

    private void showEmptyView() {
        showView(emptyView);
    }

    private void showSuccessView() {
        showView(successView);
    }

    private void showCompleteView() {
        showView(completeView);
    }

    private void showView(View view) {
        if (loadingView != null && loadingView != view) loadingView.setVisibility(View.GONE);
        if (emptyView != null && emptyView != view) emptyView.setVisibility(View.GONE);
        if (failView != null && failView != view) failView.setVisibility(View.GONE);
        if (successView != null && successView != view) successView.setVisibility(View.GONE);
        if (completeView != null && completeView != view) completeView.setVisibility(View.GONE);
        if (view != null) view.setVisibility(View.VISIBLE);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new NetworkStateLayout.LayoutParams(getContext(), attrs);
    }

    public void setNetworkState(NetworkState networkState) {
        if (networkState instanceof NetworkState.Loading) {
            showLoadingView();
        } else if (networkState instanceof NetworkState.Failed) {
            showFailView();
        } else if (networkState instanceof NetworkState.Empty) {
            showEmptyView();
        } else if (networkState instanceof NetworkState.Success) {
            showSuccessView();
        } else {
            showCompleteView();
        }
    }

    class LayoutParams extends RelativeLayout.LayoutParams {
        static final int STATE_LOADING = 1;
        static final int STATE_EMPTY = 2;
        static final int STATE_FAILED = 3;
        static final int STATE_SUCCESS = 4;
        static final int STATE_COMPLETE = 5;

        private int networkState;

        LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray ta = c.obtainStyledAttributes(attrs, R.styleable.NetworkStateLayout_Layout);
            networkState = ta.getInt(R.styleable.NetworkStateLayout_Layout_layout_network_state, 0);
            ta.recycle();
        }
    }
}
