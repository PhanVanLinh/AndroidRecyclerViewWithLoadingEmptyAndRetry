package com.toong.recyclerviewwithemptyandretry;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.toong.recyclerviewwithemptyandretry.adapter.MyRecyclerViewAdapter;
import com.toong.recyclerviewwithemptyandretry.base.BaseRecyclerViewAdapter;
import com.toong.recyclerviewwithemptyandretry.model.Item;
import com.toong.recyclerviewwithemptyandretry.widget.RecyclerViewEmptyRetryGroup;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements BaseRecyclerViewAdapter.ItemClickListener {
    private RecyclerViewEmptyRetryGroup mRecyclerViewEmptyRetryGroup;
    private MyRecyclerViewAdapter adapter;
    private List<Item> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerViewEmptyRetryGroup =
                (RecyclerViewEmptyRetryGroup) findViewById(R.id.recyclerViewEmptyRetryGroup);

        RecyclerView recyclerView = mRecyclerViewEmptyRetryGroup.getRecyclerView();

        data = new ArrayList<>();

        // set up the RecyclerView
        LinearLayoutManager l = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(l);
        adapter = new MyRecyclerViewAdapter(this, this);

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

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

        mRecyclerViewEmptyRetryGroup.setOnRetryClick(new RecyclerViewEmptyRetryGroup.OnRetryClick() {
            @Override
            public void onRetry() {
                loadDataSuccess();
            }
        });
    }

    private void loadDataEmpty() {
        adapter.clear();
        mRecyclerViewEmptyRetryGroup.loading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerViewEmptyRetryGroup.empty();
                adapter.notifyDataSetChanged();
            }
        }, 500);
    }

    private void loadDataFailed() {
        adapter.clear();
        mRecyclerViewEmptyRetryGroup.loading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerViewEmptyRetryGroup.retry();
                adapter.notifyDataSetChanged();
            }
        }, 500);
    }

    private void loadDataSuccess() {
        data.clear();

        mRecyclerViewEmptyRetryGroup.loading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                data.add(new Item("0", "b"));
                data.add(new Item("1", "b"));
                data.add(new Item("2", "b"));
                data.add(new Item("3", "b"));
                data.add(new Item("4", "b"));
                data.add(new Item("5", "b"));
                data.add(new Item("6", "b"));
                data.add(new Item("7", "b"));
                data.add(new Item("8", "b"));
                data.add(new Item("9", "b"));
                data.add(new Item("10", "b"));

                adapter.set(data);
                mRecyclerViewEmptyRetryGroup.success();
                adapter.notifyDataSetChanged();
            }
        }, 500);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
