package com.toong.recyclerviewwithemptyandretry.screen.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.toong.recyclerviewwithemptyandretry.R;
import com.toong.recyclerviewwithemptyandretry.screen.book.BookActivity;
import com.toong.recyclerviewwithemptyandretry.screen.contact.ContactActivity;
import com.toong.recyclerviewwithemptyandretry.screen.user.UserActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.button_simple_no_loadmore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BookActivity.class);
            }
        });

        findViewById(R.id.btn_1type_loadmore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(UserActivity.class);
            }
        });

        findViewById(R.id.btn_2type_loadmore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ContactActivity.class);
            }
        });
    }

    private void startActivity(Class<? extends Activity> activity) {
        startActivity(new Intent(this, activity));
    }
}
