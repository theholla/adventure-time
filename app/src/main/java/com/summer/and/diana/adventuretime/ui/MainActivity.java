package com.summer.and.diana.adventuretime.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.summer.and.diana.adventuretime.R;
import com.summer.and.diana.adventuretime.models.User;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private User mUser;
    private Button mMyGearButton;
    private Button mCheckedOutButton;
    private Button mBrowseGearButton;

    private TextView mWelcomeUserText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getApplicationContext().getSharedPreferences("adventuretime", Context.MODE_PRIVATE);
        mWelcomeUserText = (TextView) findViewById(R.id.welcomeUserText);
        mMyGearButton = (Button) findViewById(R.id.myGearButton);
        mCheckedOutButton = (Button) findViewById(R.id.checkedOutButton);
        mBrowseGearButton = (Button) findViewById(R.id.browseGearButton);

        mMyGearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyGearActivity.class);
                startActivity(intent);
            }
        });

        mCheckedOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckedOutActivity.class);
                startActivity(intent);
            }
        });

        mBrowseGearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BrowseGearActivity.class);
                startActivity(intent);
            }
        });

        if (!isLoggedIn()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private boolean isLoggedIn() {
        String username = mPreferences.getString("username", null);
        if (username == null) {
            return false;
        } else {
            setUser(username);
            return true;
        }
    }

    private void setUser(String username) {
        User user = User.find(username);
        if (user != null) {
            mUser = user;
            mWelcomeUserText.setText(user.getUsername());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_logout) {
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

}


/**
 * Delete a gear item
 * Display all items
 * Show only available gear items in Browse Gear Page
 s**/