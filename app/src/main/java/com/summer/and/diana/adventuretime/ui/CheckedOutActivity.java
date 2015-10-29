package com.summer.and.diana.adventuretime.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.summer.and.diana.adventuretime.R;
import com.summer.and.diana.adventuretime.adapters.GearAdapter;
import com.summer.and.diana.adventuretime.models.Gear;

import java.util.ArrayList;

public class CheckedOutActivity extends ListActivity {
    private SharedPreferences mPreferences;
    private ArrayList<Gear> mGearList;
    private GearAdapter mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_out);

        mPreferences = getApplicationContext().getSharedPreferences("adventuretime", Context.MODE_PRIVATE);
        String username = mPreferences.getString("username", null);
        mGearList = (ArrayList) Gear.allCheckedOut(username);
        mAdapter = new GearAdapter(this, mGearList);
        mListView = getListView();
        setListAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checked_out, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}