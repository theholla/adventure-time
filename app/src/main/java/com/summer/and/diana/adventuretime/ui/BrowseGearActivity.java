package com.summer.and.diana.adventuretime.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.summer.and.diana.adventuretime.R;
import com.summer.and.diana.adventuretime.adapters.GearAdapter;
import com.summer.and.diana.adventuretime.models.Gear;

import java.io.Serializable;
import java.util.ArrayList;

public class BrowseGearActivity extends ListActivity implements Serializable {
    private SharedPreferences mPreferences;
    private ArrayList<Gear> mGearList;
    private GearAdapter mAdapter;
    private ListView mListView;
    private String username;
    private TextView mNoItemsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_gear);
        mPreferences = getApplicationContext().getSharedPreferences("adventuretime", Context.MODE_PRIVATE);
        username = mPreferences.getString("username", null);
        mGearList = (ArrayList) Gear.all(username);
        mAdapter = new GearAdapter(this, mGearList, R.layout.gear_list_item);
        mListView = getListView();
        mNoItemsTextView = (TextView) findViewById(R.id.noItemsTextView);
        setListAdapter(mAdapter);

        mNoItemsTextView.setVisibility(View.INVISIBLE);

        if (mGearList.size() == 0) {
            mNoItemsTextView.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.INVISIBLE);
        }

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Gear gear = mGearList.get(position);
                Gear gearToBorrow = Gear.find(gear);
                gearToBorrow.borrow(username);
                mGearList.remove(gear);
                mAdapter.notifyDataSetChanged();
                Toast toast = Toast.makeText(BrowseGearActivity.this, "Item Borrowed!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse_gear, menu);
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
