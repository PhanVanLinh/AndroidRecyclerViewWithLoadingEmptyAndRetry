package com.toong.recyclerviewwithemptyandretry;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.toong.recyclerviewwithemptyandretry.adapter.UserAdapter;
import com.toong.recyclerviewwithemptyandretry.model.UserItem;
import com.toong.recyclerviewwithemptyandretry.widget.NetworkStateLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserAdapter adapter;
    private List<UserItem> data = new ArrayList<>();
    private NetworkStateLayout networkStateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        handleEvents();
    }

    private void initViews() {
        networkStateLayout = findViewById(R.id.layout_network_state);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager l = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(l);
        adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        networkStateLayout.getFailView().findViewById(R.id.button_retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataSuccess();
            }
        });
    }

    private void handleEvents() {
        findViewById(R.id.test_load_data_empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataEmpty();
            }
        });

        findViewById(R.id.test_load_data_failed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataFailed();
            }
        });

        findViewById(R.id.test_load_data_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataSuccess();
            }
        });
    }

    private void loadDataEmpty() {
        adapter.clear();
        networkStateLayout.showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                networkStateLayout.showEmptyView();
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    private void loadDataFailed() {
        adapter.clear();
        networkStateLayout.showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                networkStateLayout.showFailView();
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    private void loadDataSuccess() {
        data.clear();
        networkStateLayout.showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    data.add(new UserItem("" + i));
                }
                adapter.set(data);
                networkStateLayout.showSuccessView();
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }
}
