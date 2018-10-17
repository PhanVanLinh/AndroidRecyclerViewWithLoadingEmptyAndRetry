package com.toong.recyclerviewwithemptyandretry.screen.user;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.toong.recyclerviewwithemptyandretry.R;
import com.toong.recyclerviewwithemptyandretry.base.NetworkState;
import com.toong.recyclerviewwithemptyandretry.model.UserItem;
import com.toong.recyclerviewwithemptyandretry.screen.user.adapter.UserAdapter;
import com.toong.recyclerviewwithemptyandretry.widget.EndlessRecyclerViewScrollListener;
import com.toong.recyclerviewwithemptyandretry.widget.NetworkStateLayout;
import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private UserAdapter adapter;
    private List<UserItem> data = new ArrayList<>();
    private NetworkStateLayout networkStateLayout;
    private EndlessRecyclerViewScrollListener listener;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Users");
        setContentView(R.layout.activity_user);
        initViews();
        handleEvents();
    }

    private void initViews() {
        networkStateLayout = findViewById(R.id.layout_network_state);

        RecyclerView rvUser = findViewById(R.id.recycler_view_user);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvUser.setLayoutManager(layoutManager);
        adapter = new UserAdapter();
        rvUser.setAdapter(adapter);
        rvUser.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                page++;
                fakeLoadMoreDataSuccess(page);
            }
        };
        rvUser.addOnScrollListener(listener);

        networkStateLayout.findViewById(R.id.button_retry)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fakeInitialLoadDataSuccess();
                    }
                });
    }

    private void handleEvents() {
        findViewById(R.id.test_load_data_empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fakeInitialLoadDataEmpty();
            }
        });

        findViewById(R.id.test_load_data_failed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fakeInitialLoadDataFailed();
            }
        });

        findViewById(R.id.test_load_data_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fakeInitialLoadDataSuccess();
            }
        });
    }

    private void fakeInitialLoadDataEmpty() {
        adapter.clear();
        updateNetworkState(new NetworkState.Loading());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateNetworkState(new NetworkState.Empty());
            }
        }, 1000);
    }

    private void fakeInitialLoadDataFailed() {
        adapter.clear();
        updateNetworkState(new NetworkState.Loading());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateNetworkState(new NetworkState.Failed("some error happened"));
            }
        }, 1000);
    }

    private void fakeInitialLoadDataSuccess() {
        data.clear();
        updateNetworkState(new NetworkState.Loading());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    data.add(new UserItem("" + i));
                }
                adapter.set(data);
                updateNetworkState(new NetworkState.Success());
            }
        }, 1000);
    }

    private void fakeLoadMoreDataSuccess(int page) {
        updateNetworkState(new NetworkState.Loading());
        if (page <= 3) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    int start = adapter.getItemCount();
                    List<UserItem> newData = new ArrayList<>();
                    for (int i = start; i < start + 10; i++) {
                        newData.add(new UserItem("" + i));
                    }
                    adapter.add(newData);
                    updateNetworkState(new NetworkState.Success());
                }
            }, 1000);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateNetworkState(new NetworkState.Complete());
                }
            }, 1000);
        }
    }

    private boolean isInitialLoad() {
        return page == 1;
    }

    private void updateNetworkState(NetworkState networkState) {
        listener.setNetworkState(networkState);
        if (isInitialLoad()) {
            networkStateLayout.setNetworkState(networkState);
        } else {
            adapter.setNetworkState(networkState);
        }
    }
}
