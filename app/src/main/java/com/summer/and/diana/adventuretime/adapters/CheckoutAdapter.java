package com.summer.and.diana.adventuretime.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.summer.and.diana.adventuretime.R;
import com.summer.and.diana.adventuretime.models.Gear;
import com.summer.and.diana.adventuretime.ui.MainActivity;

import java.util.ArrayList;

/**
 * Created by Summer and Diana on 10/29/15.
 */
public class CheckoutAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Gear> mGearList;
    private int mLayout;

    public CheckoutAdapter(Context context, ArrayList<Gear> gearList, int layout) {
        mContext = context;
        mGearList = gearList;
        mLayout = layout;
    }

    @Override
    public int getCount() {
        return mGearList.size();
    }

    @Override
    public Object getItem(int position) {
        return mGearList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mLayout, null);
            holder = new ViewHolder();
            holder.itemLabel = (TextView) convertView.findViewById(R.id.itemLabel);
            holder.descriptionLabel = (TextView) convertView.findViewById(R.id.descriptionLabel);
            holder.lenderLabel = (TextView) convertView.findViewById(R.id.lenderLabel);
            holder.methodButton = (Button) convertView.findViewById(R.id.methodButton);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Gear gear = mGearList.get(position);

        holder.itemLabel.setText(gear.getItem());
        holder.descriptionLabel.setText(gear.getDescription());
        holder.lenderLabel.setText("Provided by: " + gear.getLender());

        holder.methodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gear.returnItem();
                Toast toast = Toast.makeText(mContext, "Item Returned!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView itemLabel, descriptionLabel, lenderLabel;
        Button methodButton;
    }
}

