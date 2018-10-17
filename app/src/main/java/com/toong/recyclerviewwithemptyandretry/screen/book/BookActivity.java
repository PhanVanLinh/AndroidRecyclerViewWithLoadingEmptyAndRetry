package com.toong.recyclerviewwithemptyandretry.screen.book;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.toong.recyclerviewwithemptyandretry.R;
import com.toong.recyclerviewwithemptyandretry.base.NetworkState;
import com.toong.recyclerviewwithemptyandretry.model.UserItem;
import com.toong.recyclerviewwithemptyandretry.screen.user.adapter.UserAdapter;
import com.toong.recyclerviewwithemptyandretry.widget.NetworkStateLayout;
import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    private UserAdapter adapter;
    private List<UserItem> data = new ArrayList<>();
    private NetworkStateLayout networkStateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();
        handleEvents();
        getSupportActionBar().setTitle("Books");
    }

    private void initViews() {
        RecyclerView rvBooks = findViewById(R.id.recycler_view_book);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvBooks.setLayoutManager(l);
        adapter = new UserAdapter();
        rvBooks.setAdapter(adapter);
        rvBooks.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        networkStateLayout = findViewById(R.id.layout_network_state);
        networkStateLayout.findViewById(R.id.button_retry)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fakeLoadDataSuccess();
                    }
                });
    }

    private void handleEvents() {
        findViewById(R.id.test_load_data_empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fakeLoadDataEmpty();
            }
        });

        findViewById(R.id.test_load_data_failed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fakeLoadDataFailed();
            }
        });

        findViewById(R.id.test_load_data_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fakeLoadDataSuccess();
            }
        });
    }

    private void fakeLoadDataEmpty() {
        adapter.clear();
        updateNetworkState(new NetworkState.Loading());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateNetworkState(new NetworkState.Empty());
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    private void fakeLoadDataFailed() {
        adapter.clear();
        updateNetworkState(new NetworkState.Loading());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateNetworkState(new NetworkState.Failed("some error happened"));
            }
        }, 1000);
    }

    private void fakeLoadDataSuccess() {
        data.clear();
        updateNetworkState(new NetworkState.Loading());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    data.add(new UserItem("Book " + i));
                }
                adapter.set(data);
                updateNetworkState(new NetworkState.Success());
            }
        }, 1000);
    }

    private void updateNetworkState(NetworkState networkState) {
        networkStateLayout.setNetworkState(networkState);
        if (networkState instanceof NetworkState.Failed) {
            ((TextView) findViewById(R.id.text_error_message)).setText(
                    ((NetworkState.Failed) networkState).getErrorMessage());
        }
    }
}
